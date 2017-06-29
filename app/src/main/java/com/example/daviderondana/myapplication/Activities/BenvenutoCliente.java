package com.example.daviderondana.myapplication.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.daviderondana.myapplication.Model.Utente;
import com.example.daviderondana.myapplication.R;
import com.example.daviderondana.myapplication.StaticData;

public class BenvenutoCliente extends AppCompatActivity {
    private Context context;
    private Activity activity;
    private StaticData staticData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benvenuto_cliente);

        context = getApplicationContext();
        activity = this;
        staticData = StaticData.getInstance();

        Bundle bundle = getIntent().getBundleExtra("utente_loggato");
        Utente utente = (Utente) bundle.getSerializable("utente");

        String titolo;

        if (utente.getRuolo().equals("amministratore")) {
            titolo = "Bentornato Amministratore!";
        } else {
            titolo = "Benvenuto Cliente!";
        }

        setTitle(titolo);

        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                staticData.setLogged(false);
                activity.startActivity(intent);
            }
        });

        Button catalogoButton = (Button) findViewById(R.id.catalogo_button);
        catalogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CatalogoActivity.class);
                activity.startActivity(intent);
            }
        });

        Button mieiPrestitiButton = (Button) findViewById(R.id.miei_prestiti_button);
        mieiPrestitiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PrestitiActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!staticData.isLogged()) {
            Intent intent = new Intent(context, LoginActivity.class);
            activity.startActivity(intent);
        }
    }
}
