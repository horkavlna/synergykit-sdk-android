package com.synergykit.sampleapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.synergykit.sampleapp.addons.PicturePicker;
import com.synergykit.sampleapp.model.CloudCodeDemo;
import com.synergykit.sampleapp.widgets.CustomProgressDialog;
import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.listeners.FileDataResponseListener;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITFileData;
import com.synergykit.sdk.resources.SynergyKITObject;
import com.synergykit.sdk.resources.SynergyKITUri;

/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodeActivity extends ActionBarActivity {

    private String fileUrl;
    private TextView resultCloudCode;
    private Button buttonPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_code);
        buttonPicker = (Button) findViewById(R.id.buttonPicker);
        resultCloudCode = (TextView) findViewById(R.id.resultCloudCode);

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

        SynergyKIT.uploadBitmap(b, new FileDataResponseListener() {

            @Override
            public void errorCallback(int statusCode, SynergyKITError errorObject) {
                Toast.makeText(CloudCodeActivity.this, errorObject.toString(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }

            @Override
            public void doneCallback(int statusCode, SynergyKITFileData object) {


                SynergyKITConfig config = new SynergyKITConfig();
                config.setUri(new SynergyKITUri("https://synergykit-sample-app.api.synergykit.com/v2/functions/face-recognition"));
                config.setParallelMode(false);
                config.setType(CloudCodeDemo.class);

                CloudCodeDemo ccd = new CloudCodeDemo();
                ccd.setPath(object.getPath());

                SynergyKIT.invokeCloudCode(config,ccd,new ResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, SynergyKITObject object) {
                        pd.dismiss();
                        CloudCodeDemo ccd = (CloudCodeDemo) object;
                        resultCloudCode.setText("Age: "+ccd.getAge()+", Age range: "+ccd.getAgeRange()+", Gender: "+ccd.getGender() +", Gender confidence: "+ccd.getGenderConfidence()+", Race: "+ccd.getRace()+", Race confidence"+ccd.getRaceConfidence()+", Glass: "+ccd.getGlass()+", "+ccd.getGlassConfidence()+", Smiling: "+ccd.getSmiling());

                    }

                    @Override
                    public void errorCallback(int statusCode, SynergyKITError errorObject) {
                        pd.dismiss();
                        Toast.makeText(CloudCodeActivity.this, errorObject.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

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
            upload(logo);
        }
    }
}
