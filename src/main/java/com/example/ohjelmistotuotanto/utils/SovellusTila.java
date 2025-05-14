package com.example.ohjelmistotuotanto.utils;


import com.example.ohjelmistotuotanto.entities.Kayttaja;

public class SovellusTila {
    private static Kayttaja kirjautunutKayttaja;


    public static void setKirjautunutKayttaja(Kayttaja kayttaja) {
        kirjautunutKayttaja = kayttaja;
    }

    public static Kayttaja getKirjautunutKayttaja() {
        return kirjautunutKayttaja;
    }

}
