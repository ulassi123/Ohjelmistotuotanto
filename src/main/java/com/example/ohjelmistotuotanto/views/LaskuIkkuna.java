package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.controllers.LaskuController;
import com.example.ohjelmistotuotanto.entities.Lasku;
import com.example.ohjelmistotuotanto.utils.IkkunanVaihto;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;

import static com.example.ohjelmistotuotanto.utils.Utilities.APP_KORKEUS;
import static com.example.ohjelmistotuotanto.utils.Utilities.APP_LEVEYS;

public class LaskuIkkuna {
    private final TableView<Lasku> laskuTiedotTableView = new TableView<>();
    Button poistaLaskuPainike = new Button("Poista lasku ja varaus");
    Button takaisinLasku = new Button("Takaisin");
    Button tulostaPdfButton = new Button("Luo PDF-tiedosto");
    boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();

    public void show(){
        Scene kehys = luoKehys();
        kehys.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        new LaskuController(this);
        IkkunanVaihto.vaihdaKehys(kehys);
    }

    private Scene luoKehys(){
        BorderPane laskuIkkuna = new BorderPane();
        laskuIkkuna.setPadding(new Insets(20,20,20,20));

        TableColumn<Lasku, Integer> laskuIdCol = new TableColumn<>("Lasku ID");
        laskuIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Lasku, Boolean> tilaCol = new TableColumn<>("Maksettu?");
        tilaCol.setCellValueFactory(new PropertyValueFactory<>("tila"));
        TableColumn<Lasku, Float> hintaCol = new TableColumn<>("Hinta");
        hintaCol.setCellValueFactory(new PropertyValueFactory<>("hinta"));
        TableColumn<Lasku, String> laskutusTapaCol = new TableColumn<>("Laskutustapa");
        laskutusTapaCol.setCellValueFactory(new PropertyValueFactory<>("laskutustapa"));
        TableColumn<Lasku, LocalDateTime> erapaivaCol = new TableColumn<>("Eräpäivä");
        erapaivaCol.setCellValueFactory(new PropertyValueFactory<>("erapaiva"));

        laskuTiedotTableView.getColumns().addAll(laskuIdCol, tilaCol, hintaCol, laskutusTapaCol, erapaivaCol);
        laskuTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        takaisinLasku.setId("custom-button");
        poistaLaskuPainike.setId("delete-button");
        tulostaPdfButton.setId("custom-button");

        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(takaisinLasku, tulostaPdfButton, poistaLaskuPainike);
        HBox.setMargin(takaisinLasku, new Insets(0,360,0,0));

        BorderPane.setAlignment(hbox, Pos.CENTER_LEFT);
        laskuIkkuna.setBottom(hbox);

        hbox.setAlignment(Pos.CENTER);
        if(isAdmin){
            laskuIkkuna.setBottom(null);
            laskuIkkuna.setBottom(hbox);
            laskuIkkuna.setCenter(laskuTiedotTableView);
        }else if(!isAdmin){
            laskuIkkuna.setBottom(null);
            laskuIkkuna.setBottom(takaisinLasku);
            laskuIkkuna.setCenter(laskuTiedotTableView);
        }

        return new Scene(laskuIkkuna,APP_LEVEYS,APP_KORKEUS);
    }

    public Button getTulostaPdfButton() {
        return tulostaPdfButton;
    }

    public void setTulostaPdfButton(Button tulostaPdfButton) {
        this.tulostaPdfButton = tulostaPdfButton;
    }

    public TableView<Lasku> getLaskuTiedotTableView() {
        return laskuTiedotTableView;
    }

    public Button getPoistaLaskuPainike() {
        return poistaLaskuPainike;
    }

    public void setPoistaLaskuPainike(Button poistaLaskuPainike) {
        this.poistaLaskuPainike = poistaLaskuPainike;
    }

    public Button getTakaisinLasku() {
        return takaisinLasku;
    }

    public void setTakaisinLasku(Button takaisinLasku) {
        this.takaisinLasku = takaisinLasku;
    }
}
