package com.example.daviderondana.myapplication.ClickListeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.daviderondana.myapplication.Activities.LoginActivity;

public class LoginClickListener implements View.OnClickListener {
    private Activity activity;

    public LoginClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
}
