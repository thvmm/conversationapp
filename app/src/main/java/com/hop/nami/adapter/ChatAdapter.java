package com.hop.nami.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hop.nami.entity.ChatMessage;

import java.util.ArrayList;

/**
 * Created by Tiago on 25/10/2016.
 */

public class ChatAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    ArrayList<ChatMessage> chatMessageList;
    private Activity activity;

    public ChatAdapter(Activity activity, ArrayList<ChatMessage> list) {
        chatMessageList = list;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;

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
    public synchronized View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = (ChatMessage) chatMessageList.get(position);
        return message.createView(activity);

    }

    public void add(ChatMessage object) {
        chatMessageList.add(object);
    }
}
