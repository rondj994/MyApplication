package com.example.daviderondana.myapplication.Model;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * @author france193
 */
public class Catalogo {
    private List<Libro> libri = new ArrayList<>();

    public Catalogo() {
    }

    public Catalogo(List<Libro> libri) {
        this.libri = libri;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "libri=" + libri + '}';
    }
}
