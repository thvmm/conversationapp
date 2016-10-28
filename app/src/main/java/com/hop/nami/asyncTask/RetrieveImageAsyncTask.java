
package com.hop.nami.asyncTask;

import android.os.AsyncTask;

import com.hop.nami.adapter.ChatAdapter;
import com.hop.nami.entity.ImageChatMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Guilherme Moraes on 26/10/2016.
 */

public class RetrieveImageAsyncTask extends AsyncTask<String, Object, String> {

    public static final String NAMI_HOST = "https://namisearch-backend.mybluemix.net";
    public static final String SEARCH_ENDPOINT = "/api/v1/search/product";
    private ChatAdapter chatAdapter;

    public RetrieveImageAsyncTask(ChatAdapter chatAdapter) {
        this.chatAdapter = chatAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String[] params) {
        try {
            URL url = new URL(this.urlBuilder(Arrays.asList(params)));
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
            JSONObject jsonObject = new JSONObject(String.valueOf(stringBuilder));
            URL imageUrl = new URL(jsonObject.get("imageUrl").toString());

            return imageUrl.toString();

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
    protected void onPostExecute(String url) {
        super.onPostExecute(url);
        if(url != null) {
            ImageChatMessage imageChatMessage = new ImageChatMessage("user", "", String.valueOf(new Random().nextInt(1000)), false);
            imageChatMessage.setImageUrl(url);
            chatAdapter.add(imageChatMessage);
            chatAdapter.notifyDataSetChanged();
        }
    }

    private String urlBuilder(List<String> features) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(NAMI_HOST);
        stringBuilder.append(SEARCH_ENDPOINT);
        stringBuilder.append("?q=");
        for(String feature : features) {
            stringBuilder.append(feature);
            stringBuilder.append("+");
        }
        return stringBuilder.toString();
    }
}
