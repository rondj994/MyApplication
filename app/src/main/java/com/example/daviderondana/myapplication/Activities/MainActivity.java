package com.example.daviderondana.myapplication.Activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.daviderondana.myapplication.ClickListeners.CatalogoClickListener;
import com.example.daviderondana.myapplication.ClickListeners.LoginClickListener;
import com.example.daviderondana.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        // identifico il bottone dal layout
        Button loginButton = (Button) findViewById(R.id.login_button);

        // creo un click listener
        LoginClickListener loginClickListener = new LoginClickListener(activity);

        // associo il click listener al bottone
        loginButton.setOnClickListener(loginClickListener);

        // identifico il bottone dal layout
        Button catalogoButton = (Button) findViewById(R.id.catalogo_button);

        // creo un click listener
        CatalogoClickListener catalogoClickListener = new CatalogoClickListener(activity);

        // associo il click listener al bottone
        catalogoButton.setOnClickListener(catalogoClickListener);
    }
}
