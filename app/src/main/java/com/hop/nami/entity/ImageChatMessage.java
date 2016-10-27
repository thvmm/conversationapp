package com.hop.nami.entity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hop.nami.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Guilherme Moraes on 27/10/2016.
 */

public class ImageChatMessage extends ChatMessage {

    public ImageChatMessage(String user, String messageString, String id, boolean isMINE) {
        super(user, messageString, id, isMINE);
    }

    @Override
    public View createView(Activity activity) {
        LayoutInflater inflater = null;
        View view = null;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (this.isMine()) {
            view = inflater.inflate(R.layout.chat_bubble_image_user, null);
        } else {
            view = inflater.inflate(R.layout.chatbubble_image, null);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView5);
        Picasso.with(activity).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        //imageView.setImageDrawable(drawable);
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

        return view;
    }
}
