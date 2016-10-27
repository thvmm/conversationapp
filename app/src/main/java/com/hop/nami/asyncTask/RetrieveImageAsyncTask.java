
package com.hop.nami.asyncTask;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.hop.nami.adapter.ChatAdapter;
import com.hop.nami.entity.ChatMessage;
import com.hop.nami.entity.ImageChatMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Guilherme Moraes on 26/10/2016.
 */

public class RetrieveImageAsyncTask extends AsyncTask {

    private ChatAdapter chatAdapter;
    private Map<String, Object> context;
    private List<String> features;
    private String service;
    private String endpoint;
    private JSONObject jsonObject;
    private Drawable d;

    public RetrieveImageAsyncTask(ChatAdapter chatAdapter, Map<String, Object> context, List<String> features) {
        this.chatAdapter = chatAdapter;
        this.context = context;
        this.features = features;
        this.service = "https://namisearch-backend.mybluemix.net";
        this.endpoint = "/api/v1/search/product";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL(this.urlBuilder(this.features));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\r');
            }
            bufferedReader.close();
            jsonObject = new JSONObject(String.valueOf(stringBuilder));
            URL imageUrl = new URL(jsonObject.get("imageUrl").toString());
            //Está aqui a url da imagem que precisamos recuperar
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        ChatMessage imageChatMessage = new ImageChatMessage("user", "", String.valueOf(new Random().nextInt(1000)), false);
        chatAdapter.add(imageChatMessage);

        this.context.clear();
        //this.context.putAll(response.getContext())
//            chatAdapter.notifyDataSetChanged();


        /*
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
        if(retirveInformation!= null && retirveInformation){
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

            RetrieveImageAsyncTask retrieveImageAsyncTask = new RetrieveImageAsyncTask(this.chatAdapter, this.context, features);
            retrieveImageAsyncTask.execute();
        }

        //Updating the context of the conversation
        this.context.clear();
        this.context.putAll(response.getContext())
         */
    }

    private String urlBuilder(List<String> features) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(service);
        stringBuilder.append(endpoint);
        stringBuilder.append("?q=");
        for(String feature : features) {
            stringBuilder.append(feature);
            stringBuilder.append("+");
        }
        return stringBuilder.toString();
    }
}
