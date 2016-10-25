package com.hop.nami;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.hop.nami.activity.ChatActivity;

/**
 * Created by Tiago on 25/10/2016.
 */
public class HideKeyboardFocusChangeListener implements View.OnFocusChangeListener {

    private final ChatActivity activity;

    public HideKeyboardFocusChangeListener(ChatActivity chatActivity) {
        this.activity = chatActivity;
    }

    public void onFocusChange(View view, boolean hasFocus){
        Log.d("OFC: ", String.valueOf(hasFocus));
        if(view.getId() == R.id.messageEditText && !hasFocus) {

            InputMethodManager imm =  (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }
}
