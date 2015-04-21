package com.letsgood.sampleapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.letsgood.sampleapp.addons.PicturePicker;
import com.letsgood.sampleapp.model.CloudCodeDemo;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;




/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodeActivity extends ActionBarActivity {

    private String fileUrl;
    private TextView resultCloudCode;
    private Button buttonPicker;
    private EditText name;
    private ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_code);
        buttonPicker = (Button) findViewById(R.id.buttonPicker);
        resultCloudCode = (TextView) findViewById(R.id.resultCloudCode);
        image = (ImageView) findViewById(R.id.image);
        name = (EditText) findViewById(R.id.name);

        buttonPicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pickLogoFromGallery();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // send action id to manager
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void upload(Bitmap b){

        final CustomProgressDialog pd = new CustomProgressDialog(
                this, "Loading...");

        Synergykit.createFile(b, new FileResponseListener() {

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                Toast.makeText(CloudCodeActivity.this, errorObject.toString(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }

            @Override
            public void doneCallback(int statusCode, SynergykitFile object) {

                CloudCodeDemo ccd = new CloudCodeDemo("face-recognition");
                ccd.setPath(object.getPath());
                ccd.setName(name.getText().toString());


                Synergykit.invokeCloudCode(ccd,CloudCodeDemo.class,new ResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, SynergykitObject object) {
                        pd.dismiss();
                        CloudCodeDemo ccd = (CloudCodeDemo) object;
                        resultCloudCode.setText("Age: " + ccd.getAge() + ", Age range: " + ccd.getAgeRange() + ", Gender: " + ccd.getGender() + ", Gender confidence: " + ccd.getGenderConfidence() + ", Race: " + ccd.getRace() + ", Race confidence" + ccd.getRaceConfidence() + ", Glass: " + ccd.getGlass() + ", " + ccd.getGlassConfidence() + ", Smiling: " + ccd.getSmiling());
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        pd.dismiss();
                        Toast.makeText(CloudCodeActivity.this, errorObject.toString(), Toast.LENGTH_SHORT).show();

                    }
                },false);



            }
        });


    }

    private void pickLogoFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 111);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && data != null && data.getData() != null) {
            Bitmap logo;
            logo = PicturePicker.getInstance().getBitmap(data.getData(), CloudCodeActivity.this);
            image.setImageDrawable(new BitmapDrawable(getResources(),logo));
            upload(logo);
        }
    }
}
