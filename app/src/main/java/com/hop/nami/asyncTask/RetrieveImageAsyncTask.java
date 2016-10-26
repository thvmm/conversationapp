
package com.hop.nami.asyncTask;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.hop.nami.adapter.ChatAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by Guilherme Moraes on 26/10/2016.
 */

public class RetrieveImageAsyncTask extends AsyncTask {

    private ChatAdapter chatAdapter;
    private Map<String, Object> context;
    private List<String> features;
    private String service;
    private String endpoint;
    private JSONObject response;

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
            response = new JSONObject(String.valueOf(stringBuilder));
            URL imageUrl = new URL(response.get("imageUrl").toString());

            InputStream is = (InputStream) imageUrl.getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            //TODO terminar de implementar método e apresentar imagem
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
