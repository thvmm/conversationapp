package com.hop.nami.asyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.hop.nami.adapter.ChatAdapter;
import com.hop.nami.entity.ChatMessage;
import com.hop.nami.entity.ImageChatMessage;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Tiago on 25/10/2016.
 */

public class WatsonConversationAsyncTask extends AsyncTask<MessageRequest, Object, MessageResponse> {

    private static final String WORKSPACE_ID = "#WORKSPACE_ID#";
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
        this.conversationService.setUsernameAndPassword("user", "pass");
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
        final List<String> responseList;
        responseList = (List<String>) response.getOutput().get("text");

        if(responseList != null && responseList.size() > 0) {
            for(int i = 0; i < responseList.size(); i++){
                final String text = responseList.get(i);
                final ChatMessage chatMessage = new ChatMessage("demo", text, String.valueOf(random.nextInt(1000)), false);
                chatAdapter.add(chatMessage);
                chatAdapter.notifyDataSetChanged();
            }
        }

        Boolean retirveInformation = (Boolean) response.getContext().get("retrieveInformation");
        if(retirveInformation!= null && retirveInformation.equals(true)){
            List<String> features = new ArrayList<>();
            String cores = response.getContext().get("cores").toString();
            String tecido = response.getContext().get("tecido").toString();
            String ocasiao = response.getContext().get("ocasiao").toString();
            String categoria = response.getContext().get("categoria").toString();

            if(!cores.equals("NOTHING")){
                features.add(cores);
            }
            if(!tecido.equals("NOTHING")){
                features.add(tecido);
            }
            if(!ocasiao.equals("NOTHING")){
                features.add(ocasiao);
            }
            if(!categoria.equals("NOTHING")){
                features.add(categoria);
            }
            RetrieveImageAsyncTask retrieveImageAsyncTask = new RetrieveImageAsyncTask(this.chatAdapter);
            retrieveImageAsyncTask.execute(features.toArray(new String[features.size()]));
        }

        //Updating the context of the conversation
        this.context.clear();
        this.context.putAll(response.getContext());
    }
}
