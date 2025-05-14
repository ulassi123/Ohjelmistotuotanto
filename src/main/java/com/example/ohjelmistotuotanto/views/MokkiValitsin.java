package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.data.MokkiData;
import com.example.ohjelmistotuotanto.entities.Mokki;
import com.example.ohjelmistotuotanto.utils.Utilities;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class MokkiValitsin {

    private final MokkiData mokkiData = new MokkiData();

    public Mokki naytaValintaIkkuna(Window owner) {
        List<Mokki> mokit = mokkiData.haeMokit();

        TableView<Mokki> tableView = new TableView<>();
        tableView.setItems(FXCollections.observableArrayList(mokit));

        TableColumn<Mokki, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        TableColumn<Mokki, String> osoiteCol = new TableColumn<>("Osoite");
        osoiteCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getOsoite()));

        TableColumn<Mokki, Float> hintaCol = new TableColumn<>("Hinta/yö");
        hintaCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getHintaPerYo()));


        TableColumn<Mokki, Boolean> tilaCol1 = new TableColumn<>("Varattu?");
        tilaCol1.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isTila()));
        tilaCol1.setCellFactory(CheckBoxTableCell.forTableColumn(tilaCol1));

        tableView.getColumns().addAll(idCol, osoiteCol, hintaCol, tilaCol1);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idCol.setMinWidth(50);
        idCol.setMaxWidth(50);
        idCol.setResizable(false);

        Button valitseBtn = new Button("Valitse");
        valitseBtn.setDefaultButton(true);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        stage.setTitle("Valitse mökki");

        final Mokki[] valittuMokki = {null};

        valitseBtn.setOnAction(e -> {
            Mokki valinta = tableView.getSelectionModel().getSelectedItem();

            if (valinta != null && !valinta.isTila()) {
                valittuMokki[0] = valinta;
                stage.close();
            } else {
                Utilities.showAlertDialog(Alert.AlertType.ERROR, "Mökki on varattu! Valitse jokin toinen mökki tai muuta mökin tilaa.");
            }
        });

        VBox vbox = new VBox(10, tableView, valitseBtn);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 500,400);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        valitseBtn.setId("custom-button");

        stage.setScene(scene);
        stage.showAndWait();

        return valittuMokki[0];
    }
}