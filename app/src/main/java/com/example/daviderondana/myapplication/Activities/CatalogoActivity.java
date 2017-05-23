package com.example.daviderondana.myapplication.Activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.daviderondana.myapplication.Adapter.LibriAdapter;
import com.example.daviderondana.myapplication.Model.Catalogo;
import com.example.daviderondana.myapplication.R;

public class CatalogoActivity extends AppCompatActivity {
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        activity = this;

        Catalogo catalogo = new Catalogo();

        LibriAdapter libriAdapter = new LibriAdapter(catalogo.getLibri(), activity);

        ListView listView = (ListView) findViewById(R.id.lista_libri);
        listView.setAdapter(libriAdapter);
    }
}
