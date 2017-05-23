package com.example.daviderondana.myapplication.Model;

public class Libro {
    private String titolo;
    private String autori;
    private String editore;
    private int npezzi;

    public Libro(String titolo, String autori, String editore, int npezzi) {
        this.titolo = titolo;
        this.autori = autori;
        this.editore = editore;
        this.npezzi = npezzi;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutori() {
        return autori;
    }

    public void setAutori(String autori) {
        this.autori = autori;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public int getNpezzi() {
        return npezzi;
    }

    public void setNpezzi(int npezzi) {
        this.npezzi = npezzi;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titolo='" + titolo + '\'' +
                ", autori='" + autori + '\'' +
                ", editore='" + editore + '\'' +
                ", npezzi=" + npezzi +
                '}';
    }
}
