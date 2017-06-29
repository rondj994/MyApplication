package com.example.daviderondana.myapplication.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daviderondana.myapplication.Adapter.LibriAdapter;
import com.example.daviderondana.myapplication.Adapter.PrestitiAdapter;
import com.example.daviderondana.myapplication.Model.Catalogo;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        staticData = StaticData.getInstance();
        activity = this;

        setContentView(R.layout.activity_prestiti);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

                            PrestitiAdapter prestitiAdapter = new PrestitiAdapter(prestiti.getPrestiti(), activity, staticData.isLogged());

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

}
