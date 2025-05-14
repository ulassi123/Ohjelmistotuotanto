package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.controllers.AlkuController;
import com.example.ohjelmistotuotanto.utils.IkkunanVaihto;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import static com.example.ohjelmistotuotanto.utils.Utilities.APP_KORKEUS;
import static com.example.ohjelmistotuotanto.utils.Utilities.APP_LEVEYS;

public class AlkuIkkuna {
    Button varauksetButton = new Button("Varaukset");
    Button mokitButton = new Button("Mökit");
    Button asiakkaatButton = new Button("Asiakkaat");
    Button laskutButton = new Button("Laskutukset");
    Button logoutButton = new Button("Kirjaudu ulos");
    Button kayttajaButton = new Button("Käyttäjien hallinta");
    boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();

    public void show(){
        Scene kehys = luoKehys();
        kehys.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        new AlkuController(this);
        IkkunanVaihto.vaihdaKehys(kehys);
    }

    private Scene luoKehys(){
        BorderPane alkuIkkuna = new BorderPane();
        alkuIkkuna.setPadding(new Insets(20,20,20,20));

        GridPane navigaatioNapitGrid = new GridPane();
        navigaatioNapitGrid.setHgap(10);
        navigaatioNapitGrid.setVgap(10);
        navigaatioNapitGrid.add(asiakkaatButton, 0, 0);
        navigaatioNapitGrid.add(mokitButton, 1, 0);
        navigaatioNapitGrid.add(varauksetButton, 0, 1);
        navigaatioNapitGrid.add(laskutButton, 1, 1);
        navigaatioNapitGrid.setAlignment(Pos.CENTER);

        varauksetButton.setPrefSize(300, 100);
        mokitButton.setPrefSize(300, 100);
        asiakkaatButton.setPrefSize(300, 100);
        laskutButton.setPrefSize(300, 100);

        kayttajaButton.setId("custom-button");
        alkuIkkuna.setId("custom-background");
        varauksetButton.setId("button-alkuikkuna");
        mokitButton.setId("button-alkuikkuna");
        asiakkaatButton.setId("button-alkuikkuna");
        laskutButton.setId("button-alkuikkuna");
        logoutButton.setId("custom-button");

        HBox hbox = new HBox(15);
        hbox.getChildren().addAll(logoutButton, kayttajaButton);
        alkuIkkuna.setCenter(navigaatioNapitGrid);

        hbox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(alkuIkkuna, Pos.CENTER_LEFT);
        BorderPane.setAlignment(hbox, Pos.CENTER);

        if(isAdmin){
            alkuIkkuna.setBottom(null);
            alkuIkkuna.setBottom(hbox);
            BorderPane.setAlignment(hbox, Pos.CENTER);
        }else if(!isAdmin){
            alkuIkkuna.setBottom(null);
            alkuIkkuna.setBottom(logoutButton);
            BorderPane.setAlignment(logoutButton, Pos.CENTER);
        }


        return new Scene(alkuIkkuna,APP_LEVEYS, APP_KORKEUS);
    }

    public Button getKayttajaButton() {
        return kayttajaButton;
    }

    public void setKayttajaButton(Button kayttajaButton) {
        this.kayttajaButton = kayttajaButton;
    }

    public Button getVarauksetButton() {
        return varauksetButton;
    }

    public void setVarauksetButton(Button varauksetButton) {
        this.varauksetButton = varauksetButton;
    }

    public Button getMokitButton() {
        return mokitButton;
    }

    public void setMokitButton(Button mokitButton) {
        this.mokitButton = mokitButton;
    }

    public Button getAsiakkaatButton() {
        return asiakkaatButton;
    }

    public void setAsiakkaatButton(Button asiakkaatButton) {
        this.asiakkaatButton = asiakkaatButton;
    }

    public Button getLaskutButton() {
        return laskutButton;
    }

    public void setLaskutButton(Button laskutButton) {
        this.laskutButton = laskutButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(Button logoutButton) {
        this.logoutButton = logoutButton;
    }
}
