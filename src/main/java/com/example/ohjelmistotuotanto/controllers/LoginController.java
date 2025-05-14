package com.example.ohjelmistotuotanto.controllers;

import com.example.ohjelmistotuotanto.data.KayttajaData;
import com.example.ohjelmistotuotanto.entities.Kayttaja;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import com.example.ohjelmistotuotanto.views.AlkuIkkuna;
import com.example.ohjelmistotuotanto.views.KirjautumisIkkuna;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class LoginController {
    private KirjautumisIkkuna kirjautumisIkkuna;
    private KayttajaData kayttajaData;

    public LoginController(KirjautumisIkkuna kirjautumisIkkuna){
        this.kirjautumisIkkuna = kirjautumisIkkuna;
        this.kayttajaData = new KayttajaData();
        intialize();
    }

    private void intialize(){
        kirjautumisIkkuna.getGuestLogin().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tarkistaGuestKirjautuminen();
            }
        });

        kirjautumisIkkuna.getLoginButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tarkistaKirjautuminen();
            }
        });
    }

    private void tarkistaKirjautuminen(){
        String kayttajaNimi = kirjautumisIkkuna.getUserField().getText();
        String salasana = kirjautumisIkkuna.getPassField().getText();

        if (kayttajaNimi.isEmpty() || salasana.isEmpty()) {
            kirjautumisIkkuna.getMessageLabel().setText("Täytä kaikki kentät");
            kirjautumisIkkuna.getMessageLabel().setVisible(true);
            return;
        }
        Kayttaja kayttaja = kayttajaData.haeKayttaja(kayttajaNimi, salasana);

        if (kayttaja != null) {
            SovellusTila.setKirjautunutKayttaja(kayttaja);
            new AlkuIkkuna().show();
        } else {
            kirjautumisIkkuna.getMessageLabel().setText("Väärä käyttäjätunnus\ntai salasana");
            kirjautumisIkkuna.getMessageLabel().setVisible(true);
        }
    }
    private void tarkistaGuestKirjautuminen() {
        String kayttajaNimi = "guest";
        String salasana = "guest";

        Kayttaja guestKayttaja = new Kayttaja(kayttajaNimi, salasana, false);

        SovellusTila.setKirjautunutKayttaja(guestKayttaja);

        new AlkuIkkuna().show();
    }
}
