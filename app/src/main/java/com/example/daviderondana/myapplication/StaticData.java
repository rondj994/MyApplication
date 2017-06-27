package com.example.daviderondana.myapplication;

import com.example.daviderondana.myapplication.Model.Utente;

/**
 * Created by france193 on 27/06/2017.
 */

public class StaticData {
    private static final String ip = "192.168.0.116";

    private static final StaticData ourInstance = new StaticData();

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
