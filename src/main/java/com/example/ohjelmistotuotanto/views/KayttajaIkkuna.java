package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.controllers.KayttajaController;
import com.example.ohjelmistotuotanto.entities.Kayttaja;
import com.example.ohjelmistotuotanto.utils.IkkunanVaihto;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import static com.example.ohjelmistotuotanto.utils.Utilities.APP_KORKEUS;
import static com.example.ohjelmistotuotanto.utils.Utilities.APP_LEVEYS;

public class KayttajaIkkuna {
    TableView<Kayttaja> kayttajaTiedotTableView = new TableView<>();
    private TextField kayttajanimiTextField = new TextField();
    private TextField salasanaTextField = new TextField();
    private Button lisaaKayttajaPainike = new Button("Lisää");
    private Button poistaKayttajaPainike = new Button("Poista");
    private Button kayttajaTakaisin = new Button("Takaisin");


    public void show(){
        Scene kehys = luoKehys();
        kehys.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        new KayttajaController(this);
        IkkunanVaihto.vaihdaKehys(kehys);
    }

    public Scene luoKehys(){
        BorderPane kayttajaIkkuna = new BorderPane();
        kayttajaIkkuna.setPadding(new Insets(20,20,20,20));

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5,5,5,5));
        vBox.setSpacing(10);

        VBox.setMargin(lisaaKayttajaPainike, new Insets(15,0,0,0));

        kayttajanimiTextField.getStyleClass().add("textfield-modern-editable");
        salasanaTextField.getStyleClass().add("textfield-modern-editable");
        lisaaKayttajaPainike.setId("custom-button");
        poistaKayttajaPainike.setId("delete-button");
        kayttajaTakaisin.setId("custom-button");


        kayttajanimiTextField.setMaxWidth(180);
        kayttajanimiTextField.setMinWidth(180);
        salasanaTextField.setMaxWidth(180);
        salasanaTextField.setMinWidth(180);

        lisaaKayttajaPainike.setMaxWidth(180);
        lisaaKayttajaPainike.setMinWidth(180);
        poistaKayttajaPainike.setMaxWidth(180);
        poistaKayttajaPainike.setMinWidth(180);

        kayttajanimiTextField.setPromptText("Käyttäjän nimi");
        salasanaTextField.setPromptText("Käyttäjän salasana");
        vBox.getChildren().addAll(kayttajanimiTextField,salasanaTextField,lisaaKayttajaPainike,poistaKayttajaPainike);

        TableColumn<Kayttaja, Integer> kayttajaIdCol = new TableColumn<>("Käyttäjän ID");
        kayttajaIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Kayttaja, String> kayttajaNimiCol = new TableColumn<>("Nimi");
        kayttajaNimiCol.setCellValueFactory(new PropertyValueFactory<>("kayttaja_nimi"));

        TableColumn<Kayttaja, String> kayttajaSalasanaCol = new TableColumn<>("Salasana");
        kayttajaSalasanaCol.setCellValueFactory(new PropertyValueFactory<>("salasana"));

        TableColumn<Kayttaja, Boolean> kayttajaAdminCol = new TableColumn<>("Admin?");
        kayttajaAdminCol.setCellValueFactory(new PropertyValueFactory<>("admin"));

        kayttajaTiedotTableView.getColumns().addAll(kayttajaIdCol, kayttajaNimiCol, kayttajaSalasanaCol, kayttajaAdminCol);
        kayttajaTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        kayttajaTiedotTableView.setEditable(true);

        kayttajaIkkuna.setCenter(kayttajaTiedotTableView);
        kayttajaIkkuna.setLeft(vBox);
        kayttajaIkkuna.setBottom(kayttajaTakaisin);

        return new Scene(kayttajaIkkuna, APP_LEVEYS, APP_KORKEUS);
    }

    public TableView<Kayttaja> getKayttajaTiedotTableView() {
        return kayttajaTiedotTableView;
    }

    public Button getKayttajaTakaisin() {
        return kayttajaTakaisin;
    }

    public void setKayttajaTakaisin(Button kayttajaTakaisin) {
        this.kayttajaTakaisin = kayttajaTakaisin;
    }

    public TextField getKayttajanimiTextField() {
        return kayttajanimiTextField;
    }

    public void setKayttajanimiTextField(TextField kayttajanimiTextField) {
        this.kayttajanimiTextField = kayttajanimiTextField;
    }

    public TextField getSalasanaTextField() {
        return salasanaTextField;
    }

    public void setSalasanaTextField(TextField salasanaTextField) {
        this.salasanaTextField = salasanaTextField;
    }

    public Button getLisaaKayttajaPainike() {
        return lisaaKayttajaPainike;
    }

    public void setLisaaKayttajaPainike(Button lisaaKayttajaPainike) {
        this.lisaaKayttajaPainike = lisaaKayttajaPainike;
    }

    public Button getPoistaKayttajaPainike() {
        return poistaKayttajaPainike;
    }

    public void setPoistaKayttajaPainike(Button poistaKayttajaPainike) {
        this.poistaKayttajaPainike = poistaKayttajaPainike;
    }
}
