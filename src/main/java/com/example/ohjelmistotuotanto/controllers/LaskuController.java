package com.example.ohjelmistotuotanto.controllers;

import com.example.ohjelmistotuotanto.data.LaskuData;
import com.example.ohjelmistotuotanto.entities.Lasku;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import com.example.ohjelmistotuotanto.utils.TulostaPdf;
import com.example.ohjelmistotuotanto.utils.Utilities;
import com.example.ohjelmistotuotanto.views.AlkuIkkuna;
import com.example.ohjelmistotuotanto.views.LaskuIkkuna;
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
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class LaskuController {
    private LaskuIkkuna laskuIkkuna;
    private LaskuData laskuData;

    public LaskuController(LaskuIkkuna laskuIkkuna){
        this.laskuIkkuna = laskuIkkuna;
        this.laskuData = new LaskuData();
        initialize();
    }

    private void initialize(){
        laskuIkkuna.getTakaisinLasku().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new AlkuIkkuna().show();
            }
        });

        laskuIkkuna.getTulostaPdfButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Lasku valittuLasku = laskuIkkuna.getLaskuTiedotTableView().getSelectionModel().getSelectedItem();
                if (valittuLasku != null) {
                    try {
                        TulostaPdf.tulostaLaskuPDF(valittuLasku);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virhe PDF-tiedostoa luodessa.");
                    }
                }
            }
        });

        laskuIkkuna.getPoistaLaskuPainike().setOnAction(event -> {
            Lasku valittuLasku = laskuIkkuna.getLaskuTiedotTableView().getSelectionModel().getSelectedItem();

            if (valittuLasku == null) {
                Utilities.showAlertDialog(Alert.AlertType.INFORMATION, "Valitse lasku");
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Haluatko varmasti poistaa laskun? Poistamalla laskun poistat myös varauksen.");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                int laskuId = valittuLasku.getId();
                try{
                    laskuData.poistaLaskuJaVapautaMokki(laskuId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                laskuData.poistaLasku(laskuId);
                lataaLaskut();
            }
        });

        muokkaaLaskua();
        lataaLaskut();
    }
    private void lataaLaskut() {
        List<Lasku> laskut = laskuData.haeLaskut();
        ObservableList<Lasku> laskuData = FXCollections.observableArrayList(laskut);
        laskuIkkuna.getLaskuTiedotTableView().setItems(laskuData);
    }
    private void muokkaaLaskua() {
        TableView<Lasku> tableView = laskuIkkuna.getLaskuTiedotTableView();
        boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        tableView.setEditable(isAdmin);

        tableView.getColumns().forEach(column -> {
            if (column.getText().equals("Laskutustapa")) {
                TableColumn<Lasku, String> col = (TableColumn<Lasku, String>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                col.setOnEditCommit(e -> {
                    Lasku lasku = e.getRowValue();
                    lasku.setLaskutustapa(e.getNewValue());
                    tableView.refresh();
                    laskuData.paivitaLasku(lasku);
                });
            } else if (column.getText().equals("Hinta")) {
                TableColumn<Lasku, Float> col = (TableColumn<Lasku, Float>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn(new Utilities.FloatStringConverter()));
                col.setOnEditCommit(e -> {
                    Lasku lasku = e.getRowValue();
                    lasku.setHinta(e.getNewValue());
                    tableView.refresh();
                    laskuData.paivitaLasku(lasku);
                });
            } else if (column.getText().equals("Maksettu?")) {
                TableColumn<Lasku, Boolean> col = (TableColumn<Lasku, Boolean>) column;
                col.setCellFactory(CheckBoxTableCell.forTableColumn(col));
                col.setCellValueFactory(cellData -> {
                    Lasku lasku = cellData.getValue();
                    SimpleBooleanProperty property = new SimpleBooleanProperty(lasku.isTila());
                    property.addListener((obs, oldVal, newVal) -> {
                        lasku.setTila(newVal);
                        tableView.refresh();
                        laskuData.paivitaLasku(lasku);
                    });
                    return property;
                });
            } else if (column.getText().equals("Eräpäivä")) {
                TableColumn<Lasku, LocalDate> col = (TableColumn<Lasku, LocalDate>) column;

                StringConverter<LocalDate> converter = new StringConverter<>() {
                    @Override
                    public String toString(LocalDate date) {
                        return date != null ? date.format(formatter) : "";
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        try {
                            return LocalDate.parse(string, formatter);
                        } catch (DateTimeParseException e) {
                            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virheellinen päivämäärä. Päivämäärä on muotoa dd.MM.yyyy");
                            return null;
                        }
                    }
                };

                col.setCellFactory(TextFieldTableCell.forTableColumn(converter));
                col.setOnEditCommit(e -> {
                    Lasku lasku = e.getRowValue();
                    LocalDate uusiPaiva = e.getNewValue();
                    if (uusiPaiva != null) {
                        lasku.setErapaiva(uusiPaiva);
                        tableView.refresh();
                        laskuData.paivitaLasku(lasku);
                    }
                });
            }
        });
    }

}


