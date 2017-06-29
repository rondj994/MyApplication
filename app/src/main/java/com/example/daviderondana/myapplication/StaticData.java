package com.example.daviderondana.myapplication;

import com.example.daviderondana.myapplication.Model.Utente;


public class StaticData {
    //setto l'ip che utilizzo per mandarlo in Run
    //deve essere lo stesso con il progetto di NetBeans e va modificato ogni volta che cambio rete
    private static final String ip = "172.20.10.2";

    //si istanzia appena avviata l'applicazione
    private static final StaticData ourInstance = new StaticData();

    //
    public static StaticData getInstance() {
        return ourInstance;
    }

    private Boolean logged = false;

    private Utente utente;

    public Boolean isLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public static String getIp() {
        return ip;
    }
}
