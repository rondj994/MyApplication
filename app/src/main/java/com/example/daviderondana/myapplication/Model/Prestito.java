package com.example.daviderondana.myapplication.Model;

/**
 * @author Alessandro
 */
public class Prestito {
    private String idu;
    private String idl;
    private String dataini;
    private String datafine;

    public Prestito() {
    }

    public Prestito(String idu, String idl, String dataini, String datafine) {
        this.idu = idu;
        this.idl = idl;
        this.dataini = dataini;
        this.datafine = datafine;
    }

    public void setIdu(String idu) {
        this.idu = idu;
    }

    public void setIdl(String idl) {
        this.idl = idl;
    }

    public void setDataini(String dataini) {
        this.dataini = dataini;
    }

    public void setDatafine(String datafine) {
        this.datafine = datafine;
    }

    public String getIdu() {
        return idu;
    }

    public String getIdl() {
        return idl;
    }

    public String getDataini() {
        return dataini;
    }

    public String getDatafine() {
        return datafine;
    }

    @Override
    public String toString() {
        return "Prestito{" + "IDUtente=" + idu + ", IDLibro=" + idl + ", DataInizio=" + dataini + ", DataFine=" + datafine + '}';
    }
}
