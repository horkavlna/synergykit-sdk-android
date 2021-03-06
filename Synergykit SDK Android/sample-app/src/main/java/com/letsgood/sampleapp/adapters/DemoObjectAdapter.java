package com.letsgood.sampleapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.letsgood.sampleapp.R;
import com.letsgood.sampleapp.model.DemoObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;


import java.util.ArrayList;

/**
 * Created by Marek on 1/13/15.
 */
public class DemoObjectAdapter extends ArrayAdapter<DemoObject> {

    private Context context;
    private LayoutInflater inflater;
    private int resource;
    private ArrayList<DemoObject> objects;

    public DemoObjectAdapter(Context context, int resource,
                          ArrayList<DemoObject> demoObjects) {
        super(context, resource, demoObjects);
        this.context = context;
        this.resource = resource;
        this.objects = demoObjects;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = inflater.inflate(resource, parent, false);

        SynergykitObject object = objects.get(position);

        if (object != null) {
            if (object instanceof DemoObject){

            DemoObject demo = (DemoObject)object;

            TextView text = (TextView) convertView
                    .findViewById(R.id.textView);

            text.setTag(object);

            if (demo.getText()==null)
                text.setText("");
            else
                text.setText(demo.getText());

            }
        }

        return convertView;
    }
}
