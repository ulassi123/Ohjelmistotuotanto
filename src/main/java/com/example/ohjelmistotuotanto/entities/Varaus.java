package com.example.ohjelmistotuotanto.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Luokka varauksiin liittyville tiedoille ja niiden käsittelylle SQL:ssä
 */
public class Varaus {
    private int id;
    private int asiakas_id;
    private int mokki_id;
    private int lasku_id;
    private LocalDate aloituspaiva;
    private LocalDate lopetuspaiva;
    private LocalDateTime luotu;
    private LocalDateTime paivitetty;

    public Varaus(int id, int asiakas_id, int mokki_id, int lasku_id, LocalDate aloituspaiva, LocalDate lopetuspaiva) {
        this.id = id;
        this.asiakas_id = asiakas_id;
        this.mokki_id = mokki_id;
        this.lasku_id = lasku_id;
        this.aloituspaiva = aloituspaiva;
        this.lopetuspaiva = lopetuspaiva;
        this.luotu = LocalDateTime.now();
        this.paivitetty = LocalDateTime.now();
    }

    public Varaus(int asiakas_id, int mokki_id, int lasku_id, LocalDate aloituspaiva, LocalDate lopetuspaiva) {
        this.asiakas_id = asiakas_id;
        this.mokki_id = mokki_id;
        this.lasku_id = lasku_id;
        this.aloituspaiva = aloituspaiva;
        this.lopetuspaiva = lopetuspaiva;
        this.luotu = LocalDateTime.now();
        this.paivitetty = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getAsiakas_id() {
        return asiakas_id;
    }

    public void setAsiakas_id(int asiakas_id) {
        this.asiakas_id = asiakas_id;
    }

    public int getMokki_id() {
        return mokki_id;
    }

    public void setMokki_id(int mokki_id) {
        this.mokki_id = mokki_id;
    }

    public int getLasku_id() {
        return lasku_id;
    }

    public void setLasku_id(int lasku_id) {
        this.lasku_id = lasku_id;
    }

    public LocalDate getAloituspaiva() {
        return aloituspaiva;
    }

    public void setAloituspaiva(LocalDate aloituspaiva) {
        this.aloituspaiva = aloituspaiva;
    }

    public LocalDate getLopetuspaiva() {
        return lopetuspaiva;
    }

    public void setLopetuspaiva(LocalDate lopetuspaiva) {
        this.lopetuspaiva = lopetuspaiva;
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


