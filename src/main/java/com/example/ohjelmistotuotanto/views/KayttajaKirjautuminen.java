package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.utils.DbConnect;
import com.example.ohjelmistotuotanto.utils.Utilities;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class KayttajaKirjautuminen {

    DbConnect dbConnect = new DbConnect();

    public boolean naytaKirjautumisIkkuna(Window owner) {

        Stage stage = new Stage();
        stage.setTitle("Kirjaudu sisään");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);

        Label userLabel = new Label("Käyttäjätunnus: (tietokannan nimi)");
        TextField userField = new TextField();
        Label passLabel = new Label("Salasana: (tietokannan salasana)");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Kirjaudu");

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            String oikeaNimi = dbConnect.getDB_NAME();
            String oikeaSalasana = dbConnect.getDB_PASSWORD();

            if (username.equals(oikeaNimi) && password.equals(oikeaSalasana)) {
                stage.close();
                new KayttajaIkkuna().show();
            } else {
                Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virheellinen käyttäjätunnus tai salasana. Syötä tietokannan nimi ja salasana.");
            }
        });

        VBox vbox = new VBox(5, userLabel, userField, passLabel, passField, loginButton);
        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(userField, new Insets(10,0,0,0));
        VBox.setMargin(passField, new Insets(10,0,0,0));
        VBox.setMargin(loginButton, new Insets(10,0,0,0));

        Scene scene = new Scene(vbox, 500, 450);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        userField.setMaxWidth(200);
        userField.setMinWidth(200);
        passField.setMaxWidth(200);
        passField.setMinWidth(200);
        userField.getStyleClass().add("textfield-modern");
        passField.getStyleClass().add("textfield-modern");
        userLabel.getStyleClass().add("label-modern");
        passLabel.getStyleClass().add("label-modern");
        loginButton.getStyleClass().add("button-11");

        stage.setScene(scene);
        stage.showAndWait();

        return true;
    }
}


