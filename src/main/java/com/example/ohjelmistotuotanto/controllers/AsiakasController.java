package com.example.ohjelmistotuotanto.controllers;

import com.example.ohjelmistotuotanto.data.AsiakasData;
import com.example.ohjelmistotuotanto.entities.Asiakas;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import com.example.ohjelmistotuotanto.utils.Utilities;
import com.example.ohjelmistotuotanto.views.AlkuIkkuna;
import com.example.ohjelmistotuotanto.views.AsiakasIkkuna;
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

public class AsiakasController {
    private AsiakasIkkuna asiakasIkkuna;
    private final AsiakasData asiakasData = new AsiakasData();
    private final ObservableList<Asiakas> asiakasLista = FXCollections.observableArrayList();

    public AsiakasController(AsiakasIkkuna asiakasIkkuna) {
        this.asiakasIkkuna = asiakasIkkuna;
        initialize();
    }
    public void initialize(){
        lataaAsiakkaat();
        asiakasIkkuna.getAsiakasTiedotTableView().setItems(asiakasLista);
        muokkaaAsiakas();

        asiakasIkkuna.getAsiakasTakaisin().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new AlkuIkkuna().show();
            }
        });

        asiakasIkkuna.getLisaaAsiakasPainike().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lisaaAsiakas();
            }
        });
        asiakasIkkuna.getPoistaAsiakasPainike().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Haluatko varmasti poistaa asiakastiedot?");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK){
                    poistaAsiakas();
                }
            }
        });
    }

    private void lataaAsiakkaat() {
        asiakasLista.setAll(asiakasData.haeAsiakkaat());
    }

    private void muokkaaAsiakas() {
        TableView<Asiakas> tableView = asiakasIkkuna.getAsiakasTiedotTableView();
        boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();
        tableView.setEditable(isAdmin);

        tableView.getColumns().forEach(column -> {
            if (column.getText().equals("Sähköposti")) {
                TableColumn<Asiakas, String> col = (TableColumn<Asiakas, String>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                col.setOnEditCommit(e -> {
                    Asiakas asiakas = e.getRowValue();
                    asiakas.setSahkoposti(e.getNewValue());
                    tableView.refresh();
                    asiakasData.paivitaAsiakas(asiakas);
                });
            } else if (column.getText().equals("Nimi")) {
                TableColumn<Asiakas, String> col = (TableColumn<Asiakas, String>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                col.setOnEditCommit(e -> {
                    Asiakas asiakas = e.getRowValue();
                    asiakas.setNimi(e.getNewValue());
                    tableView.refresh();
                    asiakasData.paivitaAsiakas(asiakas);
                });
            } else if (column.getText().equals("Puhelinnumero")) {
                TableColumn<Asiakas, String> col = (TableColumn<Asiakas, String>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                col.setOnEditCommit(e -> {
                    Asiakas asiakas = e.getRowValue();
                    asiakas.setPuhelinnumero(e.getNewValue());
                    tableView.refresh();
                    asiakasData.paivitaAsiakas(asiakas);
                });
            } else if (column.getText().equals("Maa")) {
                TableColumn<Asiakas, String> col = (TableColumn<Asiakas, String>) column;
                col.setCellFactory(TextFieldTableCell.forTableColumn());
                col.setOnEditCommit(e -> {
                    Asiakas asiakas = e.getRowValue();
                    asiakas.setMaa(e.getNewValue());
                    tableView.refresh();
                    asiakasData.paivitaAsiakas(asiakas);
                });
            } else if (column.getText().equals("Yritys")) {
                TableColumn<Asiakas, Boolean> col = (TableColumn<Asiakas, Boolean>) column;
                col.setCellFactory(CheckBoxTableCell.forTableColumn(col));
                col.setCellValueFactory(cellData -> {
                    Asiakas asiakas = cellData.getValue();
                    SimpleBooleanProperty property = new SimpleBooleanProperty(asiakas.getYritys());
                    property.addListener((obs, oldVal, newVal) -> {
                        asiakas.setYritys(newVal);
                        tableView.refresh();
                        asiakasData.paivitaAsiakas(asiakas);
                    });
                    return property;
                });
            }
        });
    }

    private boolean validateInput(){
        if(asiakasIkkuna.getSahkopostiTextField().getText().isEmpty()) return false;
        if(asiakasIkkuna.getPuhelinnumeroTextField().getText().isEmpty()) return false;
        if(asiakasIkkuna.getNimiTextField().getText().isEmpty()) return false;
        if(asiakasIkkuna.getMaaTextField().getText().isEmpty()) return false;

        return true;
    }
    private void lisaaAsiakas() {
        if(!validateInput()){
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Täytä kaikki kentät!");
            return;
        }

        Asiakas uusi = new Asiakas(
                0,
                asiakasIkkuna.getSahkopostiTextField().getText(),
                asiakasIkkuna.getNimiTextField().getText(),
                asiakasIkkuna.getPuhelinnumeroTextField().getText(),
                asiakasIkkuna.getMaaTextField().getText(),
                asiakasIkkuna.getYritysCheckBox().isSelected()
        );
        asiakasIkkuna.getSahkopostiTextField().clear();
        asiakasIkkuna.getNimiTextField().clear();
        asiakasIkkuna.getPuhelinnumeroTextField().clear();
        asiakasIkkuna.getMaaTextField().clear();
        asiakasIkkuna.getYritysCheckBox().setSelected(false);
        asiakasData.lisaaAsiakas(uusi);
        lataaAsiakkaat();
        Utilities.showAlertDialog(Alert.AlertType.INFORMATION, "Uusi asiakas on luotu!");
    }

    private void poistaAsiakas() {
        Asiakas valittu = (Asiakas) asiakasIkkuna.getAsiakasTiedotTableView().getSelectionModel().getSelectedItem();
        if (valittu != null) {
            asiakasData.poistaAsiakas(valittu.getId());
            lataaAsiakkaat();
        }
    }
}
