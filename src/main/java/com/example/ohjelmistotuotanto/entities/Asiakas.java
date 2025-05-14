package com.example.ohjelmistotuotanto.entities;

/**
 * Luokka asiakkaisiin liittyville tiedoille ja niiden käsittelylle SQL:ssä
 */
public class Asiakas {
    private int id;
    private String sahkoposti;
    private String nimi;
    private String puhelinnumero;
    private String maa;
    private Boolean yritys;

    public Asiakas(int id, String sahkoposti, String nimi, String puhelinnumero, String maa, boolean yritys) {
        this.id = id;
        this.sahkoposti = sahkoposti;
        this.nimi = nimi;
        this.puhelinnumero = puhelinnumero;
        this.maa = maa;
        this.yritys = yritys;
    }

    public int getId() {
        return id;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        this.maa = maa;
    }

    public Boolean getYritys() {
        return yritys;
    }

    public void setYritys(Boolean yritys) {
        this.yritys = yritys;
    }
}