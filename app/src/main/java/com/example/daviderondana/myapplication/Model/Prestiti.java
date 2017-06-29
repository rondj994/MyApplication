package com.example.daviderondana.myapplication.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by france193 on 29/06/2017.
 */
public class Prestiti {
    private List<Prestito> prestiti = new ArrayList<>();
    ;

    public Prestiti() {
    }

    public Prestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    @Override
    public String toString() {
        return "Prestiti{" +
                "prestiti=" + prestiti +
                '}';
    }
}
