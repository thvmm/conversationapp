package com.hop.nami;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class ChatActivity extends AppCompatActivity {

    private ImageButton send;
    private EditText message;
    private ListView chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        send = (ImageButton) findViewById(R.id.send);
        message = (EditText) findViewById(R.id.message);
        chat = (ListView) findViewById(R.id.chat);

        send.setOnClickListener(sendButtonClickListner());
    }

    private View.OnClickListener sendButtonClickListner() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sendButtonClickListner", "Button clicked!");
            }
        };
        return onClickListener;
    }


}
