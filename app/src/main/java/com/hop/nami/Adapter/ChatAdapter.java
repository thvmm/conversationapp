package com.hop.nami.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hop.nami.Entity.ChatMessage;
import com.hop.nami.R;

import java.util.ArrayList;

/**
 * Created by Tiago on 25/10/2016.
 */

public class ChatAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    ArrayList<ChatMessage> chatMessageList;

    public ChatAdapter(Activity activity, ArrayList<ChatMessage> list) {
        chatMessageList = list;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return chatMessageList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = (ChatMessage) chatMessageList.get(position);
        View view = convertView;

        if (convertView == null) {
            if (message.isMine()) {
                view = inflater.inflate(R.layout.chatbubble_user, null);
            } else {
                view = inflater.inflate(R.layout.chatbubble, null);
            }
        }

        TextView msg = (TextView) view.findViewById(R.id.message_text);
        msg.setText(message.getBody());
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.bubble_layout);
        LinearLayout parent_layout = (LinearLayout) view.findViewById(R.id.bubble_layout_parent);

        // if message is mine then align to right
        if (message.isMine()) {
            layout.setBackgroundResource(R.drawable.bubble2);
            parent_layout.setGravity(Gravity.RIGHT);
        }
        // If not mine then align to left
        else {
            layout.setBackgroundResource(R.drawable.bubble1);
            parent_layout.setGravity(Gravity.LEFT);

        }
        
        msg.setTextColor(view.getResources().getColor(R.color.textColor));

        return view;
    }

    public void add(ChatMessage object) {
        chatMessageList.add(object);
    }
}
