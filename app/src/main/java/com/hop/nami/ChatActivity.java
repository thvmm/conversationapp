package com.hop.nami;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton send;
    private EditText message;
    private ListView chat;

    private EditText msg_edittext;
    private Random random;
    public static ArrayList<ChatMessage> chatlist;
    public static ChatAdapter chatAdapter;
    ListView msgListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        setContentView(R.layout.activity_chat);

        send = (ImageButton) findViewById(R.id.send);
        message = (EditText) findViewById(R.id.message);
        chat = (ListView) findViewById(R.id.chat);

        send.setOnClickListener(sendButtonClickListner());
*/

        setContentView(R.layout.chat_layout);
        random = new Random();
        msg_edittext = (EditText) findViewById(R.id.messageEditText);
        msgListView = (ListView) findViewById(R.id.msgListView);
        ImageButton sendButton = (ImageButton) findViewById(R.id.sendMessageButton);
        sendButton.setOnClickListener(this);

        // ----Set autoscroll of listview when a new message arrives----//
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);

        chatlist = new ArrayList<ChatMessage>();
        chatAdapter = new ChatAdapter(this, chatlist);
        msgListView.setAdapter(chatAdapter);
    }

    @Override
    public void onClick(View view) {
        Log.d("sendButtonClickListner", "Button clicked!");
        if(view.getId() == R.id.sendMessageButton) {
                sendTextMessage(view);
        }
    }

    public void sendTextMessage(View v) {
        String message = msg_edittext.getEditableText().toString();
        if (!message.equalsIgnoreCase("")) {
            final ChatMessage chatMessage = new ChatMessage("demo",
                    message, "" + random.nextInt(1000), false);
            chatMessage.setMsgID();
            chatMessage.body = message;
            msg_edittext.setText("");
            chatAdapter.add(chatMessage);
            chatAdapter.notifyDataSetChanged();
        }
    }
}
