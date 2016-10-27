package com.hop.nami.entity;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tiago on 27/10/2016.
 */
public class User {
    private static User currentUser = new User();
    private String pictureUrl;

    public static User getCurrentUser() {
        return currentUser;
    }

    private User() {
    }

    public static void loadUserInfo() {
        final String userId = AccessToken.getCurrentAccessToken().getUserId();
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + userId + "/picture?type=small&redirect=false",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            final JSONObject data = response.getJSONObject().getJSONObject("data");
                            final String url = data.getString("url");
                            User.currentUser.setPictureUrl(url);
                            Log.d("GET PICTURE", "onCompleted: " + url);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
