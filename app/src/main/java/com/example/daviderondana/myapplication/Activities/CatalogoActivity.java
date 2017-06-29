package com.example.daviderondana.myapplication.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daviderondana.myapplication.Adapter.LibriAdapter;
import com.example.daviderondana.myapplication.Model.Catalogo;
import com.example.daviderondana.myapplication.Model.Libro;
import com.example.daviderondana.myapplication.Model.Utente;
import com.example.daviderondana.myapplication.R;
import com.example.daviderondana.myapplication.StaticData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CatalogoActivity extends AppCompatActivity {
    private Activity activity;
    private StaticData staticData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        activity = this;
        staticData = StaticData.getInstance();
        setTitle("Catalogo");

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://" + staticData.getIp() + ":8080/WebApplication1/biblioteca";

        StringRequest postRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            Catalogo catalogo = mapper.readValue(response, Catalogo.class);

                            LibriAdapter libriAdapter = new LibriAdapter(catalogo.getLibri(), activity, staticData.isLogged());

                            ListView listView = (ListView) findViewById(R.id.lista_libri);
                            listView.setAdapter(libriAdapter);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toast("Error retrieving catalog ");
                        Log.d("ERROR", "Error retrieving catalog " + error.getMessage() + "\n" + error.getCause() + "\n" + error.getStackTrace());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "catalogo");
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
