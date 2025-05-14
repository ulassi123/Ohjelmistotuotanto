package com.example.ohjelmistotuotanto.controllers;

import com.example.ohjelmistotuotanto.data.KayttajaData;
import com.example.ohjelmistotuotanto.entities.Kayttaja;
import com.example.ohjelmistotuotanto.views.AlkuIkkuna;
import com.example.ohjelmistotuotanto.views.KayttajaIkkuna;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class KayttajaController {
    KayttajaIkkuna kayttajaIkkuna;
    private final ObservableList<Kayttaja> kayttajaLista = FXCollections.observableArrayList();

    private KayttajaData kayttajaData = new KayttajaData();
    public KayttajaController(KayttajaIkkuna kayttajaIkkuna) {
        this.kayttajaIkkuna = kayttajaIkkuna;
        intialize();
    }

    private void intialize(){
        lataaKaytajat();
        muokkaaKayttajaa();
        kayttajaIkkuna.getKayttajaTiedotTableView().setItems(kayttajaLista);

        kayttajaIkkuna.getKayttajaTakaisin().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new AlkuIkkuna().show();
            }
        });
        kayttajaIkkuna.getLisaaKayttajaPainike().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lisaaKayttaja();
            }
        });
        kayttajaIkkuna.getPoistaKayttajaPainike().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Haluatko varmasti poistaa käyttäjän?");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK){
                    poistaKayttaja();
                }
            }
        });
    }
    private void muokkaaKayttajaa() {
        TableView<Kayttaja> tableView = kayttajaIkkuna.getKayttajaTiedotTableView();

        tableView.getColumns().forEach(column -> {
            if (column.getText().equals("Nimi")) {
                TableColumn<Kayttaja, String> col = (TableColumn<Kayttaja, String>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                col.setOnEditCommit(e -> {
                    Kayttaja kayttaja = e.getRowValue();
                    kayttaja.setKayttajaNimi(e.getNewValue());
                    tableView.refresh();
                    kayttajaData.paivitaKayttaja(kayttaja);
                });
            }else if (column.getText().equals("Salasana")) {
                TableColumn<Kayttaja, String> col = (TableColumn<Kayttaja, String>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                col.setOnEditCommit(e -> {
                    Kayttaja kayttaja = e.getRowValue();
                    kayttaja.setSalasana(e.getNewValue());
                    tableView.refresh();
                    kayttajaData.paivitaKayttaja(kayttaja);
                });
            }else if (column.getText().equals("Admin?")) {
                TableColumn<Kayttaja, Boolean> col = (TableColumn<Kayttaja, Boolean>) column;
                col.setCellFactory(CheckBoxTableCell.forTableColumn(col));
                col.setCellValueFactory(cellData -> {
                    Kayttaja kayttaja = cellData.getValue();
                    SimpleBooleanProperty property = new SimpleBooleanProperty(kayttaja.isAdmin());
                    property.addListener((obs, oldVal, newVal) -> {
                        kayttaja.setAdmin(newVal);
                        tableView.refresh();
                        kayttajaData.paivitaKayttaja(kayttaja);
                    });
                    return property;
                });
            }
        });
    }
    private void lisaaKayttaja() {
        Kayttaja uusi = new Kayttaja(
                0,
                kayttajaIkkuna.getKayttajanimiTextField().getText(),
                kayttajaIkkuna.getSalasanaTextField().getText(),
                true
        );
        kayttajaIkkuna.getKayttajanimiTextField().clear();
        kayttajaIkkuna.getSalasanaTextField().clear();
        kayttajaData.lisaaKayttaja(uusi);
        lataaKaytajat();

    }
    private void poistaKayttaja() {
        Kayttaja valittu = (Kayttaja) kayttajaIkkuna.getKayttajaTiedotTableView().getSelectionModel().getSelectedItem();
        if (valittu != null) {
            kayttajaData.poistaKayttaja(valittu.getId());
            lataaKaytajat();
        }
    }
    private void lataaKaytajat() {
        kayttajaLista.setAll(kayttajaData.haeKayttajat());
    }
}
