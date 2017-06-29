package com.example.daviderondana.myapplication.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daviderondana.myapplication.Model.Prestito;
import com.example.daviderondana.myapplication.R;

import java.util.List;

//ADAPTER serve per visualizzare una lista di elementi nella list view

public class PrestitiAdapter extends BaseAdapter {
    private List<Prestito> prestiti;
    private Activity activity;
    private Boolean logged;

    public PrestitiAdapter(List<Prestito> prestiti, Activity activity, Boolean logged) {
        this.prestiti = prestiti;
        this.activity = activity;
        this.logged = logged;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
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

        }else if (prestiti.get(i).getIdl().equals("Alchimista")) {
            copertina.setBackgroundResource(R.drawable.alchimista);
        }else if (prestiti.get(i).getIdl().equals("Harry Potter e la Pietra Filosofale")){
            copertina.setBackgroundResource(R.drawable.harrypotter);
        }else if (prestiti.get(i).getIdl().equals("Il Monte Analogo")){
            copertina.setBackgroundResource(R.drawable.monteanalogo);
        }else if (prestiti.get(i).getIdl().equals("Il piccolo principe")){
            copertina.setBackgroundResource(R.drawable.piccoloprincipe);

        } else {
            copertina.setBackgroundResource(R.mipmap.ic_launcher);
        }


        //prendo da database la data inizio prestito e la data fine e gestisco se prestito ancora attivo

        idl.setText(prestiti.get(i).getIdl());
        data_inizio.setText(prestiti.get(i).getDataini());

        if (prestiti.get(i).getDatafine() == null) {
            data_fine.setText("Non ancora restituito");
        } else {
            data_fine.setText(prestiti.get(i).getDatafine());
        }


        return view;
    }
}
