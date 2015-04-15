package com.letsgood.sampleapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.letsgood.sampleapp.R;
import com.letsgood.sampleapp.model.DemoMessage;


/**
 * Created by Letsgood.com - Pavel Stambrecht on 25. 3. 2015.
 */
public class MessageAdapter extends ArrayAdapter<DemoMessage> {

    /* Constructor */
    public MessageAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DemoMessage message = getItem(position);

        if(message==null)
            return convertView;


        if(message.getType()== DemoMessage.TYPE_MY_MSG)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_socket_message_left, parent, false);
        else if (message.getType()== DemoMessage.TYPE_STATE)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_socket_message_center, parent, false);
        else
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_socket_message_right, parent, false);


        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        messageTextView.setText(message.getName() + ": " + message.getText());


        TextView userLetterTextView = (TextView) convertView.findViewById(R.id.userLetterTextView);

        if(message.getType()==2){
            userLetterTextView.setText("");
        }else if(message.getName()!=null && message.getName().length()>1){
            userLetterTextView.setText(String.valueOf(message.getName().charAt(0)));
        }






        return convertView;
    }
}
