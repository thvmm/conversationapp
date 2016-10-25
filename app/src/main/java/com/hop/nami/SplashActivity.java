package com.hop.nami;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Tiago on 25/10/2016.
 */

public class SplashActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
        finish();
    }
}
