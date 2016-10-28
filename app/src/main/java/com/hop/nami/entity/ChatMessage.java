package com.hop.nami.entity;

/**
 * Created by Tiago on 25/10/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hop.nami.CircleTransform;
import com.hop.nami.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class ChatMessage {

    private String body;
    private String user;
    private String msgId;
    private boolean isMine;// Did I send the message.

    public ChatMessage(String user, String messageString,
                       String id, boolean isMINE) {
        this.body = messageString;
        this.isMine = isMINE;
        this.user = user;
        this.msgId = id;
        this.setMsgID();
    }

    private void setMsgID() {
        msgId += "-" + String.format("%02d", new Random().nextInt(100));
        ;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgid) {
        this.msgId = msgid;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public View createView(Activity activity){
        LayoutInflater inflater = null;
        View view = null;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (this.isMine()) {
            view = inflater.inflate(R.layout.chatbubble_user, null);
            Picasso.with(view.getContext())
                    .load(User.getCurrentUser().getPictureUrl())
                    .resize(100,100)
                    .transform(new CircleTransform())
                    .into((ImageView) view.findViewById(R.id.imageView3));
        } else {
            view = inflater.inflate(R.layout.chatbubble, null);
        }

        TextView msg = (TextView) view.findViewById(R.id.message_text);
        msg.setText(this.getBody());
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.bubble_layout);
        LinearLayout parent_layout = (LinearLayout) view.findViewById(R.id.bubble_layout_parent);

        // if message is mine then align to right
        if (this.isMine()) {
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

}
