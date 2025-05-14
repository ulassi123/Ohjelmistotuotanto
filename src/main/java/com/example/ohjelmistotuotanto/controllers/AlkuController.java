package com.example.ohjelmistotuotanto.controllers;

import com.example.ohjelmistotuotanto.utils.SovellusTila;
import com.example.ohjelmistotuotanto.views.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Optional;

public class AlkuController {
    private AlkuIkkuna alkuIkkuna;

    public AlkuController(AlkuIkkuna alkuIkkuna) {
        this.alkuIkkuna = alkuIkkuna;
        initialize();
    }

    private void initialize() {
        alkuIkkuna.getKayttajaButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                KayttajaKirjautuminen kirjautuminen = new KayttajaKirjautuminen();
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                kirjautuminen.naytaKirjautumisIkkuna(stage);
            }
        });
        alkuIkkuna.getLogoutButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();
                if(isAdmin){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Haluatko varmasti kirjautua ulos?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.isPresent() && result.get() == ButtonType.OK) {
                        new KirjautumisIkkuna().show();
                    }
                }else {
                    new KirjautumisIkkuna().show();
                }
            }
        });

        alkuIkkuna.getAsiakkaatButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new AsiakasIkkuna().show();
            }
        });
        alkuIkkuna.getLaskutButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new LaskuIkkuna().show();
            }
        });
        alkuIkkuna.getMokitButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new MokkiIkkuna().show();
            }
        });
        alkuIkkuna.getVarauksetButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new VarausIkkuna().show();
            }
        });
    }
}