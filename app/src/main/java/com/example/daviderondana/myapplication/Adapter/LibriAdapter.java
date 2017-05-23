package com.example.daviderondana.myapplication.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daviderondana.myapplication.Model.Libro;
import com.example.daviderondana.myapplication.R;

import java.util.List;

public class LibriAdapter extends BaseAdapter {
    private List<Libro> libri;
    private Activity activity;

    public LibriAdapter(List<Libro> libri, Activity activity) {
        this.libri = libri;
        this.activity = activity;
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

        ImageView imageView = (ImageView) view.findViewById(R.id.icona);

        TextView titolo_textView = (TextView) view.findViewById(R.id.titolo_libro);
        TextView autore_textView = (TextView) view.findViewById(R.id.autore);
        TextView editore_textView = (TextView) view.findViewById(R.id.editore);
        TextView npezziTextView = (TextView) view.findViewById(R.id.npezzi);

        if (libro.getTitolo().equals("Inferno")) {
            imageView.setBackgroundResource(R.drawable.inferno);
        } else {
            imageView.setBackgroundResource(R.mipmap.ic_launcher);
        }

        titolo_textView.setText(libro.getTitolo());
        autore_textView.setText(libro.getAutori());
        editore_textView.setText(libro.getEditore());
        npezziTextView.setText("numero pezzi: " + libro.getNpezzi());

        return view;
    }
}
