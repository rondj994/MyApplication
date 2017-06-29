package com.example.daviderondana.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.daviderondana.myapplication.Activities.CatalogoActivity;
import com.example.daviderondana.myapplication.Activities.PrestitiActivity;
import com.example.daviderondana.myapplication.Model.Prestito;
import com.example.daviderondana.myapplication.R;
import com.example.daviderondana.myapplication.StaticData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ADAPTER serve per visualizzare una lista di elementi nella list view

public class PrestitiAdapter extends BaseAdapter {
    private List<Prestito> prestiti;
    private Activity activity;
    private Boolean logged;
    private StaticData staticData;
    private Context context;

    public PrestitiAdapter(List<Prestito> prestiti, Activity activity, Boolean logged, Context context) {
        this.prestiti = prestiti;
        this.activity = activity;
        this.logged = logged;
        this.context = context;
        staticData = StaticData.getInstance();
    }

    //quante celle devo creare?
    @Override
    public int getCount() {
        return prestiti.size();
    }

    //associa le celle alla posizione dell'elemento nella lista
    @Override
    public Object getItem(int i) {
        return prestiti.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //put the designed layout "data_item" as the layout of the row
            view = activity.getLayoutInflater().inflate(R.layout.layout_prestito, viewGroup, false);
        }

        //prototipo del layout, riempito
        ImageView copertina = (ImageView) view.findViewById(R.id.icona_libro);
        TextView idl = (TextView) view.findViewById(R.id.idu);
        TextView data_inizio = (TextView) view.findViewById(R.id.data_inizio);
        TextView data_fine = (TextView) view.findViewById(R.id.data_fine);

        Button restituisci = (Button) view.findViewById(R.id.restituisci_button);

        //controlla se sono loggeto
        if (logged) {
            restituisci.setClickable(true);
            restituisci.setAlpha(1);
        } else {
            restituisci.setClickable(false);
            restituisci.setAlpha((float) 0.2);
        }

        //ciclo in cui assegno le immagini al catalogo per ciascun libro

        if (prestiti.get(i).getIdl().equals("Inferno")) {
            copertina.setBackgroundResource(R.drawable.inferno);

        } else if (prestiti.get(i).getIdl().equals("Alchimista")) {
            copertina.setBackgroundResource(R.drawable.alchimista);
        } else if (prestiti.get(i).getIdl().equals("Harry Potter e la Pietra Filosofale")) {
            copertina.setBackgroundResource(R.drawable.harrypotter);
        } else if (prestiti.get(i).getIdl().equals("Il Monte Analogo")) {
            copertina.setBackgroundResource(R.drawable.monteanalogo);
        } else if (prestiti.get(i).getIdl().equals("Il piccolo principe")) {
            copertina.setBackgroundResource(R.drawable.piccoloprincipe);

        } else {
            copertina.setBackgroundResource(R.mipmap.ic_launcher);
        }

        //prendo da database la data inizio prestito e la data fine e gestisco se prestito ancora attivo

        idl.setText(prestiti.get(i).getIdl());
        data_inizio.setText(prestiti.get(i).getDataini());

        if (prestiti.get(i).getDatafine() == null) {
            data_fine.setText("Non ancora restituito");
            restituisci.setAlpha((float) 1);
            restituisci.setClickable(true);
        } else {
            data_fine.setText(prestiti.get(i).getDatafine());
            restituisci.setAlpha((float) 0.2);
            restituisci.setClickable(false);
        }

        restituisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestiti.get(i).getDatafine() == null) {
                    RequestQueue queue = Volley.newRequestQueue(context);

                    String url = "http://" + staticData.getIp() + ":8080/WebApplication1/biblioteca";

                    StringRequest postRequest = new StringRequest(
                            Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    toast("Restituzione avvenuta con successo");
                                    Intent i = new Intent(context, PrestitiActivity.class);
                                    i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                                    activity.startActivity(i);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    toast("Non è possibile restituire il libro");
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("action", "restituisci_libro");
                            params.put("username", staticData.getUtente().getAccount());
                            params.put("titolo", prestiti.get(i).getIdl());
                            return params;
                        }
                    };

                    queue.add(postRequest);
                }
            }
        });

        return view;
    }

    public void toast(String text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
