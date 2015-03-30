package com.synergykit.sampleapp.adapters;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.synergykit.sampleapp.R;
import com.synergykit.sampleapp.beans.SocketMessage;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 25. 3. 2015.
 */
public class MessageAdapter extends ArrayAdapter<SocketMessage> {

    /* Constructor */
    public MessageAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SocketMessage message = getItem(position);

        if(message==null)
            return convertView;

        if (convertView == null)
            if(message.getType()==1)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_socket_message_left, parent, false);
            else if (message.getType()==2)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_socket_message_center, parent, false);
            else
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_socket_message_right, parent, false);


        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        messageTextView.setText(message.getName() + ": " + message.getText());


        TextView userLetterTextView = (TextView) convertView.findViewById(R.id.userLetterTextView);

        if(message.getType()==2){
            userLetterTextView.setText("");
        }else{
            userLetterTextView.setText(String.valueOf(message.getName().charAt(0)));
        }






        return convertView;
    }
}
