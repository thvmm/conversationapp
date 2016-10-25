package com.hop.nami;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private final static Random random = new Random();

    private EditText msgEditText;
    private ListView msgListView;

    private static ArrayList<ChatMessage> messages;
    private static ChatAdapter chatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chat_layout);
        msgEditText = (EditText) findViewById(R.id.messageEditText);

        View.OnFocusChangeListener ofcListener = new HideKeyboardFocusChangeListener(this);
        msgEditText.setOnFocusChangeListener(ofcListener);

        msgListView = (ListView) findViewById(R.id.msgListView);
        ImageButton sendButton = (ImageButton) findViewById(R.id.sendMessageButton);
        sendButton.setOnClickListener(this);

        // ----Set autoscroll of listview when a new message arrives----//
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);

        messages = new ArrayList<ChatMessage>();
        chatAdapter = new ChatAdapter(this, messages);
        msgListView.setAdapter(chatAdapter);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE
                | ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.sendMessageButton) {
            Log.d("sendButtonClickListner", "Button clicked!");
            sendTextMessage(view);
        }
    }

    public void sendTextMessage(View v) {
        String message = msgEditText.getEditableText().toString();
        if (!message.equalsIgnoreCase("")) {
            final ChatMessage chatMessage = new ChatMessage("demo", message, String.valueOf(random.nextInt(1000)), true);
            chatMessage.setBody(message);
            msgEditText.setText("");
            chatAdapter.add(chatMessage);
            chatAdapter.notifyDataSetChanged();
        }
    }
}
