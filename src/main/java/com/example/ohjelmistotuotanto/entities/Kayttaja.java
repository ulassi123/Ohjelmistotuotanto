package com.example.ohjelmistotuotanto.entities;

public class Kayttaja {
    private int id;
    private String kayttaja_nimi;
    private String salasana;
    private boolean isAdmin;

    public Kayttaja(String kayttaja_nimi, String salasana, boolean isAdmin) {
        this.kayttaja_nimi = kayttaja_nimi;
        this.salasana = salasana;
        this.isAdmin = isAdmin;
    }

    public Kayttaja(int id, String kayttaja_nimi, String salasana, boolean isAdmin) {
        this.id = id;
        this.kayttaja_nimi = kayttaja_nimi;
        this.salasana = salasana;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKayttaja_nimi() {
        return kayttaja_nimi;
    }

    public void setKayttajaNimi(String kayttajaNimi) {
        this.kayttaja_nimi = kayttajaNimi;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

