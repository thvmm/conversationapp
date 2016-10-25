package com.hop.nami.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

/**
 * Created by Tiago on 25/10/2016.
 */

public class SplashActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private void updateWithToken(AccessToken currentAccessToken) {
        Context from = SplashActivity.this;
        Class<?> to;
        if (currentAccessToken != null) {
            to = ChatActivity.class;
        } else {
            to = LoginActivity.class;
        }

        Intent i = new Intent(from, to);
        startActivity(i);
        finish();
    }
}
