package com.example.ohjelmistotuotanto.entities;

import java.time.LocalDate;

/**
 * Luokka laskuihin liittyville tiedoille ja niiden käsittelylle SQL:ssä
 */
public class Lasku {
    private int id;
    private float hinta;
    private String laskutustapa;
    private LocalDate erapaiva;
    private boolean tila;

    public Lasku(int id, float hinta, String laskutustapa, LocalDate erapaiva, boolean tila) {
        this.id = id;
        this.hinta = hinta;
        this.laskutustapa = laskutustapa;
        this.erapaiva = erapaiva;
        this.tila = tila;
    }

    public Lasku(float hinta, String laskutustapa, LocalDate erapaiva, boolean tila) {
        this.id = id;
        this.hinta = hinta;
        this.laskutustapa = laskutustapa;
        this.erapaiva = erapaiva;
        this.tila = tila;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public float getHinta() {
        return hinta;
    }

    public void setHinta(float hinta) {
        this.hinta = hinta;
    }

    public String getLaskutustapa() {
        return laskutustapa;
    }

    public void setLaskutustapa(String laskutustapa) {
        this.laskutustapa = laskutustapa;
    }

    public LocalDate getErapaiva() {
        return erapaiva;
    }

    public void setErapaiva(LocalDate erapaiva) {
        this.erapaiva = erapaiva;
    }

    public boolean isTila() {
        return tila;
    }

    public void setTila(boolean tila) {
        this.tila = tila;
    }
}

