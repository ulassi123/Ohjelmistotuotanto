package com.example.ohjelmistotuotanto.controllers;

import com.example.ohjelmistotuotanto.data.MokkiData;
import com.example.ohjelmistotuotanto.entities.Mokki;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import com.example.ohjelmistotuotanto.utils.Utilities;
import com.example.ohjelmistotuotanto.views.AlkuIkkuna;
import com.example.ohjelmistotuotanto.views.MokkiIkkuna;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class MokkiController {
    private MokkiIkkuna mokkiIkkuna;
    private MokkiData mokkiData = new MokkiData();
    private final ObservableList<Mokki> mokkiLista = FXCollections.observableArrayList();


    public MokkiController(MokkiIkkuna mokkiIkkuna) {
        this.mokkiIkkuna = mokkiIkkuna;
        this.mokkiData = new MokkiData();
        initialize();
    }

    private void initialize(){
        lataaMokit();
        muokkaaMokkeja();
        mokkiIkkuna.getMokkiTiedotTableView().setItems(mokkiLista);

        mokkiIkkuna.getMokkiTakaisin().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new AlkuIkkuna().show();
            }
        });
        mokkiIkkuna.getLisaaMokkiPainike().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lisaaMokki();
            }
        });

        mokkiIkkuna.getPoistaMokkiPainike().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Haluatko varmasti poistaa mökin?");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK) {
                    poistaMokki();
                }
            }
        });

    }

    private void muokkaaMokkeja(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        TableView<Mokki> tableView = mokkiIkkuna.getMokkiTiedotTableView();
        boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();
        tableView.setEditable(isAdmin);

        tableView.getColumns().forEach(column -> {
            if (column.getText().equals("Osoite")) {
                TableColumn<Mokki, String> col = (TableColumn<Mokki, String>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                col.setOnEditCommit(e -> {
                    Mokki mokki = e.getRowValue();
                    mokki.setOsoite(e.getNewValue());
                    tableView.refresh();
                    paivitaMokki(mokki);
                });
            } else if (column.getText().equals("Huoneet")) {
                TableColumn<Mokki, Integer> col = (TableColumn<Mokki, Integer>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                col.setOnEditCommit(e -> {
                    Mokki mokki = e.getRowValue();
                    mokki.setHuoneet(e.getNewValue());
                    tableView.refresh();
                    paivitaMokki(mokki);
                });
            } else if (column.getText().equals("Päivitetty")) {
                TableColumn<Mokki, LocalDateTime> col = (TableColumn<Mokki, LocalDateTime>) column;
                col.setCellFactory(c -> new TableCell<Mokki, LocalDateTime>() {
                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(formatter.format(item));
                        }
                    }
                });
            } else if (column.getText().equals("Koko (m²)")) {
                TableColumn<Mokki, Integer> col = (TableColumn<Mokki, Integer>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                col.setOnEditCommit(e -> {
                    Mokki mokki = e.getRowValue();
                    mokki.setKoko(e.getNewValue());
                    tableView.refresh();
                    paivitaMokki(mokki);
                });
            } else if (column.getText().equals("Hinta/yö")) {
                TableColumn<Mokki, Float> col = (TableColumn<Mokki, Float>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn(new Utilities.FloatStringConverter()));

                col.setOnEditCommit(e -> {
                    Mokki mokki = e.getRowValue();
                    mokki.setHintaPerYo(e.getNewValue());
                    tableView.refresh();
                    paivitaMokki(mokki);
                });
            } else if (column.getText().equals("Luotu")) {
                    TableColumn<Mokki, LocalDateTime> col = (TableColumn<Mokki, LocalDateTime>) column;
                    col.setCellFactory(c -> new TableCell<Mokki, LocalDateTime>() {
                        @Override
                        protected void updateItem(LocalDateTime item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(formatter.format(item));
                            }
                        }
                    });
            } else if (column.getText().equals("Varattu?")) {
                TableColumn<Mokki, Boolean> col = (TableColumn<Mokki, Boolean>) column;
                col.setCellFactory(CheckBoxTableCell.forTableColumn(col));
                col.setCellValueFactory(cellData -> {
                    Mokki mokki = cellData.getValue();
                    SimpleBooleanProperty property = new SimpleBooleanProperty(mokki.isTila());
                    property.addListener((obs, oldVal, newVal) -> {
                        mokki.setTila(newVal);
                        tableView.refresh();
                        paivitaMokki(mokki);
                    });
                    return property;
                });
            }
        });
    }
    private void lataaMokit() {
        mokkiLista.setAll(mokkiData.haeMokit());
    }

    private boolean validateInput(){
        if(mokkiIkkuna.getOsoiteTextField().getText().isEmpty()) return false;
        if(mokkiIkkuna.getHuoneetTextField().getText().isEmpty()) return false;
        if(mokkiIkkuna.getKokoTextField().getText().isEmpty()) return false;
        if(mokkiIkkuna.getHinta_per_yoTextField().getText().isEmpty()) return false;

        return true;
    }

    private void lisaaMokki() {
        if(!validateInput()){
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Täytä kaikki kentät!");
            return;
        }
        try{
            Mokki uusi = new Mokki(0,
                    mokkiIkkuna.getOsoiteTextField().getText(),
                    false,
                    Integer.parseInt(mokkiIkkuna.getHuoneetTextField().getText()),
                    Integer.parseInt(mokkiIkkuna.getKokoTextField().getText()),
                    Float.parseFloat(mokkiIkkuna.getHinta_per_yoTextField().getText()),
                    LocalDateTime.now(),
                    LocalDateTime.now());
                    mokkiIkkuna.getOsoiteTextField().clear();
                    mokkiIkkuna.getHuoneetTextField().clear();
                    mokkiIkkuna.getKokoTextField().clear();
                    mokkiIkkuna.getHinta_per_yoTextField().clear();
                    mokkiData.lisaaMokki(uusi);
                    lataaMokit();
                    Utilities.showAlertDialog(Alert.AlertType.INFORMATION, "Uusi mökki on luotu.");
        }catch (Exception e){
            e.printStackTrace();
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Mökkiä ei voitu luoda. Tarkista antamasi tiedot. Hinnan, huoneiden lukumäärän ja hinta/yö täytyy olla numero.");
        }

    }

    private void poistaMokki() {
        Mokki valittu = mokkiIkkuna.getMokkiTiedotTableView().getSelectionModel().getSelectedItem();
        if (valittu != null) {
            mokkiData.poistaMokki(valittu.getId());
            lataaMokit();
        }
    }

    private void paivitaMokki(Mokki mokki) {
        mokki.setPaivitetty(LocalDateTime.now());
        mokkiData.paivitaMokki(mokki);
    }
}