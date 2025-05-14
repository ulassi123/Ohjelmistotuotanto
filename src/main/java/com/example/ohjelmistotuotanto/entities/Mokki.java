package com.example.ohjelmistotuotanto.entities;

import java.time.LocalDateTime;

/**
 * Luokka mökkeihin liittyville tiedoille ja niiden käsittelylle SQL:ssä
 */
public class Mokki {
    private int id;
    private String osoite;
    private boolean tila; // varattu vai ei
    private int huoneet;
    private int koko; // m²
    private Float hintaPerYo;
    private LocalDateTime luotu;
    private LocalDateTime paivitetty;

    public Mokki(int id, String osoite, boolean tila, int huoneet, int koko, Float hintaPerYo,
                 LocalDateTime luotu, LocalDateTime paivitetty) {
        this.id = id;
        this.osoite = osoite;
        this.tila = tila;
        this.huoneet = huoneet;
        this.koko = koko;
        this.hintaPerYo = hintaPerYo;
        this.luotu = luotu;
        this.paivitetty = paivitetty;
    }

    public Mokki(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public boolean isTila() {
        return tila;
    }

    public void setTila(boolean tila) {
        this.tila = tila;
    }

    public int getHuoneet() {
        return huoneet;
    }

    public void setHuoneet(int huoneet) {
        this.huoneet = huoneet;
    }

    public int getKoko() {
        return koko;
    }

    public void setKoko(int koko) {
        this.koko = koko;
    }

    public Float getHintaPerYo() {
        return hintaPerYo;
    }

    public void setHintaPerYo(Float hintaPerYo) {
        this.hintaPerYo = hintaPerYo;
    }

    public LocalDateTime getLuotu() {
        return luotu;
    }

    public void setLuotu(LocalDateTime luotu) {
        this.luotu = luotu;
    }

    public LocalDateTime getPaivitetty() {
        return paivitetty;
    }

    public void setPaivitetty(LocalDateTime paivitetty) {
        this.paivitetty = paivitetty;
    }
}


