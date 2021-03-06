package com.example.daviderondana.myapplication.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.daviderondana.myapplication.Adapter.LibriAdapter;
import com.example.daviderondana.myapplication.Model.Catalogo;
import com.example.daviderondana.myapplication.R;
import com.example.daviderondana.myapplication.StaticData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CatalogoActivity extends AppCompatActivity {
    private Activity activity;
    private StaticData staticData;
    private Context context;

    //siamo nel CATALOGO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        context = getApplicationContext();
        activity = this;
        staticData = StaticData.getInstance();
        setTitle("Catalogo");

        //freccia del ritorno alla home nella toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //volley libreria gestita da google per fare richieste http da android
        //instanzio coda di richieste http
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        //mi costruisco l'URL
        String url = "http://" + staticData.getIp() + ":8080/WebApplication1/biblioteca";

        //creo la richiesta
        StringRequest postRequest = new StringRequest(
                Request.Method.POST, //primo paremetro tipo richiesta
                url, // secondo parametro url
                new Response.Listener<String>() { //se va a buon fine
                    @Override
                    public void onResponse(String response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            Catalogo catalogo = mapper.readValue(response, Catalogo.class);

                            LibriAdapter libriAdapter = new LibriAdapter(catalogo.getLibri(), activity, staticData.isLogged(), getApplicationContext());

                            ListView listView = (ListView) findViewById(R.id.lista_libri);
                            listView.setAdapter(libriAdapter);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() { //se c'è errore
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toast("Error retrieving catalog ");
                        Log.d("ERROR", "Error retrieving catalog " + error.getMessage() + "\n" + error.getCause() + "\n" + error.getStackTrace());
                    }
                }
        ) {
            //inserire i parametri nella richiesta
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "catalogo"); //specifico action
                return params;
            }
        };

        queue.add(postRequest);
    }


    //metodo per visualizzare i messaggi a schermo come popup
    public void toast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            Intent intent;

            if (staticData.isLogged()) {
                intent = new Intent(context, BenvenutoCliente.class);
            } else {
                intent = new Intent(context, MainActivity.class);
            }

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
