package com.hop.nami.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Guilherme Moraes on 25/10/2016.
 */

public class RetriveWatsonResponse extends AsyncTask {

    private URL url;
    private HttpURLConnection httpURLConnection;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            url = new URL("https://gateway.watsonplatform.net/conversation/api/v1/workspaces/1970448a-cb3a-4859-9806-b8ec1d4cdfbb/message?version=2016-09-20");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/json");
            httpURLConnection.setRequestProperty("Authorization",
                    "Basic MmQ3YjgzZTMtOGE4YS00NGE2LThiZmEtMTk2M2MwYTk3NmI1OlZKSE9PWnZMRVF5NQ==");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream (
                    httpURLConnection.getOutputStream());
            wr.close();
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            JSONObject jsonObject = new JSONObject(response.toString());
            System.out.println("ring ring");
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


}
