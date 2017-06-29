package com.example.daviderondana.myapplication.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daviderondana.myapplication.Model.Libro;
import com.example.daviderondana.myapplication.R;

import java.util.List;

public class LibriAdapter extends BaseAdapter {
    private List<Libro> libri;
    private Activity activity;
    private Boolean logged;

    public LibriAdapter(List<Libro> libri, Activity activity, Boolean logged) {
        this.libri = libri;
        this.activity = activity;
        this.logged = logged;
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

        Libro libro = libri.get(i);

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

        //ciclo in cui assegno le immagini al catalogo per ciascun libro

        if (libro.getTitolo().equals("Inferno")) {
            imageView.setBackgroundResource(R.drawable.inferno);

            }else if (libro.getTitolo().equals("Alchimista")) {
                imageView.setBackgroundResource(R.drawable.alchimista);
            }else if (libro.getTitolo().equals("Harry Potter e la Pietra Filosofale")){
                imageView.setBackgroundResource(R.drawable.harrypotter);
            }else if (libro.getTitolo().equals("Il Monte Analogo")){
                imageView.setBackgroundResource(R.drawable.monteanalogo);
            }else if (libro.getTitolo().equals("Il piccolo principe")){
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
}
