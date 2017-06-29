package com.example.daviderondana.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daviderondana.myapplication.Activities.BenvenutoCliente;
import com.example.daviderondana.myapplication.Activities.CatalogoActivity;
import com.example.daviderondana.myapplication.Activities.LoginActivity;
import com.example.daviderondana.myapplication.Activities.PrestitiActivity;
import com.example.daviderondana.myapplication.Model.Catalogo;
import com.example.daviderondana.myapplication.Model.Libro;
import com.example.daviderondana.myapplication.Model.Utente;
import com.example.daviderondana.myapplication.R;
import com.example.daviderondana.myapplication.StaticData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibriAdapter extends BaseAdapter {
    private List<Libro> libri;
    private Activity activity;
    private Boolean logged;
    private Context context;
    private StaticData staticData;
    private BaseAdapter baseAdapter = this;

    public LibriAdapter(List<Libro> libri, Activity activity, Boolean logged, Context context) {
        this.libri = libri;
        this.activity = activity;
        this.logged = logged;
        this.context = context;
        staticData = StaticData.getInstance();
    }

    @Override
    public int getCount() {
        return libri.size();
    }

    @Override
    public Object getItem(int i) {
        return libri.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //put the designed layout "data_item" as the layout of the row
            view = activity.getLayoutInflater().inflate(R.layout.layout_libro, viewGroup, false);
        }

        final Libro libro = libri.get(i);

        ImageView imageView = (ImageView) view.findViewById(R.id.icona); //icona e un id che ho nel layout del libro

        TextView titolo_textView = (TextView) view.findViewById(R.id.titolo_libro);
        TextView autore_textView = (TextView) view.findViewById(R.id.autore);
        TextView editore_textView = (TextView) view.findViewById(R.id.editore);
        TextView npezziTextView = (TextView) view.findViewById(R.id.npezzi);

        Button prenota = (Button) view.findViewById(R.id.prenota_button);

        //se sono loggato attivo il tasto prenota altrimenti no
        if (logged) {
            prenota.setClickable(true);
            prenota.setAlpha(1);
        } else {
            prenota.setClickable(false);
            prenota.setAlpha((float) 0.2);
        }

        if (libro.getNpezzi() == 0) {
            prenota.setClickable(false);
            prenota.setAlpha((float) 0.2);
        }

        prenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (libro.getNpezzi() > 0) {
                    RequestQueue queue = Volley.newRequestQueue(context);

                    String url = "http://" + staticData.getIp() + ":8080/WebApplication1/biblioteca";

                    StringRequest postRequest = new StringRequest(
                            Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    toast("Prestito avvenuto con successo");
                                    Intent i = new Intent(context, CatalogoActivity.class);
                                    i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    activity.startActivity(i);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    if (libro.getNpezzi() == 0) {
                                        toast("Libro non più disponibile");
                                    } else {
                                        toast("Libro già preso in prestito");
                                    }
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("action", "prestito_libro");
                            params.put("titolo", libro.getTitolo());
                            params.put("username", staticData.getUtente().getAccount());
                            return params;
                        }
                    };

                    queue.add(postRequest);
                }
            }
        });

        //ciclo in cui assegno le immagini al catalogo per ciascun libro

        if (libro.getTitolo().equals("Inferno")) {
            imageView.setBackgroundResource(R.drawable.inferno);

        } else if (libro.getTitolo().equals("Alchimista")) {
            imageView.setBackgroundResource(R.drawable.alchimista);
        } else if (libro.getTitolo().equals("Harry Potter e la Pietra Filosofale")) {
            imageView.setBackgroundResource(R.drawable.harrypotter);
        } else if (libro.getTitolo().equals("Il Monte Analogo")) {
            imageView.setBackgroundResource(R.drawable.monteanalogo);
        } else if (libro.getTitolo().equals("Il piccolo principe")) {
            imageView.setBackgroundResource(R.drawable.piccoloprincipe);

        } else {
            imageView.setBackgroundResource(R.mipmap.ic_launcher);
        }

        //setto il testo
        titolo_textView.setText(libro.getTitolo());
        autore_textView.setText(libro.getAutori());
        editore_textView.setText(libro.getEditore());
        npezziTextView.setText("numero pezzi: " + libro.getNpezzi());

        return view;
    }

    public void toast(String text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
