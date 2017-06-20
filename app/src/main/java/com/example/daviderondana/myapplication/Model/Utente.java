package com.example.daviderondana.myapplication.Model;

public class Utente {
    private String account;
    private String password;
    private String ruolo;

    public Utente() {
        this.account = "anonymous";
        this.password = "anonymous";
        this.ruolo = "anonymous";
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getRuolo() {
        return ruolo;
    }

    @Override
    public String toString() {
        return "Utente{" + "account=" + account + ", password=" + password + ", ruolo=" + ruolo + '}';
    }
}
