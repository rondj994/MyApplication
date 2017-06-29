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

/**
 * Created by france193 on 29/06/2017.
 */
public class PrestitiAdapter extends BaseAdapter {
    private List<Prestito> prestiti;
    private Activity activity;
    private Boolean logged;

    public PrestitiAdapter(List<Prestito> prestiti, Activity activity, Boolean logged) {
        this.prestiti = prestiti;
        this.activity = activity;
        this.logged = logged;
    }

    @Override
    public int getCount() {
        return prestiti.size();
    }

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

        ImageView copertina = (ImageView) view.findViewById(R.id.icona_libro);

        TextView idl = (TextView) view.findViewById(R.id.idu);
        TextView data_inizio = (TextView) view.findViewById(R.id.data_inizio);
        TextView data_fine = (TextView) view.findViewById(R.id.data_fine);

        Button restituisci = (Button) view.findViewById(R.id.restituisci_button);



        return view;
    }
}
