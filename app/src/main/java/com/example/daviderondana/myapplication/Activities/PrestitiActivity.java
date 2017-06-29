package com.example.daviderondana.myapplication.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daviderondana.myapplication.Adapter.PrestitiAdapter;
import com.example.daviderondana.myapplication.Model.Prestiti;
import com.example.daviderondana.myapplication.R;
import com.example.daviderondana.myapplication.StaticData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PrestitiActivity extends AppCompatActivity {
    private StaticData staticData;
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        staticData = StaticData.getInstance();
        activity = this;
        context = getApplicationContext();

        setContentView(R.layout.activity_prestiti);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //freccia del ritorno alla home nella toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Prestiti");


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://" + staticData.getIp() + ":8080/WebApplication1/biblioteca";

        StringRequest postRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            Prestiti prestiti = mapper.readValue(response, Prestiti.class);

                            PrestitiAdapter prestitiAdapter = new PrestitiAdapter(prestiti.getPrestiti(), activity, staticData.isLogged(), getApplicationContext());

                            ListView listView = (ListView) findViewById(R.id.lista_prestiti);
                            listView.setAdapter(prestitiAdapter);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "Error retrieving prestiti " + error.getMessage() + "\n" + error.getCause() + "\n" + error.getStackTrace() + "\n" + error.networkResponse);
                        Log.d("ERROR", staticData.getUtente().toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "tutti_miei_prestiti");
                params.put("username", staticData.getUtente().getAccount());
                return params;
            }
        };

        queue.add(postRequest);

    }

    public void toast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            Intent intent = new Intent(context, BenvenutoCliente.class);

            // creo un bundle per passare dati tra 2 activity
            Bundle bundle = new Bundle();
            // metto l'oggetto che voglio passare dentro al bundle
            bundle.putSerializable("utente", staticData.getUtente());
            // inserisco il bundle dentro all'intent
            intent.putExtra("utente_loggato", bundle);

            activity.startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
