package com.hop.nami.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.hop.nami.adapter.ChatAdapter;
import com.hop.nami.entity.ChatMessage;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Tiago on 25/10/2016.
 */

public class WatsonConversationAsyncTask extends AsyncTask<MessageRequest, Object, MessageResponse> {

    public static final String WORKSPACE_ID = "1970448a-cb3a-4859-9806-b8ec1d4cdfbb";
    private final static Random random = new Random();
    private final ChatAdapter chatAdapter;
    private Map<String, Object> context;
    private ConversationService conversationService;


    public WatsonConversationAsyncTask(ChatAdapter chatAdapter, Map<String, Object> context) {
        this.chatAdapter = chatAdapter;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        this.conversationService = new ConversationService("2016-09-20");
        this.conversationService.setUsernameAndPassword("2d7b83e3-8a8a-44a6-8bfa-1963c0a976b5", "VJHOOZvLEQy5");
    }

    @Override
    protected MessageResponse doInBackground(MessageRequest[] objects) {
        if(objects != null && objects.length > 0) {
            return this.conversationService
                    .message(WORKSPACE_ID, objects[0])
                    .execute();
        }

        return null;
    }

    @Override
    protected void onPostExecute(MessageResponse response) {
        super.onPostExecute(response);
        Log.d("WATSON", "context POSTCALL " + response.getContext());
        final List<String> responseList = (List<String>) response.getOutput().get("text");

        if(responseList != null && responseList.size() > 0) {
            final String text = responseList.get(0);
            final ChatMessage chatMessage = new ChatMessage("demo", text, String.valueOf(random.nextInt(1000)), false);
            chatAdapter.add(chatMessage);
            chatAdapter.notifyDataSetChanged();
        }
        //Updating the context of the conversation
        this.context.clear();
        this.context.putAll(response.getContext());
    }
}
