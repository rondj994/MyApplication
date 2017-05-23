package com.example.daviderondana.myapplication.Model;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Libro> libri;

    public Catalogo() {
        this.libri = new ArrayList<>();

        for (int i=0; i<100; i++) {
            this.libri.add(new Libro("Libro"+i, "Autore"+i, "editore"+i, i));
        }


        Libro l1 = new Libro("Inferno", "Dan Brown", "editore1", 5);
        this.libri.add(l1);
        /*
        Libro l2 = new Libro("Inferno1", "Dan Brown", "editore1", 5);
        Libro l3 = new Libro("Inferno2", "Dan Brown", "editore1", 5);
        this.libri.add(l1);
        this.libri.add(l2);
        this.libri.add(l3);
        */
    }

    public List<Libro> getLibri() {
        return libri;
    }
}
