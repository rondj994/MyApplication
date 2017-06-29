package com.example.daviderondana.myapplication.ClickListeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.daviderondana.myapplication.Activities.CatalogoActivity;

public class CatalogoClickListener implements View.OnClickListener {
    private Activity activity;

    public CatalogoClickListener(Activity activity) {
        this.activity = activity;
    }

    //associo il click alla activity

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(activity, CatalogoActivity.class);
        activity.startActivity(intent);
    }
}
