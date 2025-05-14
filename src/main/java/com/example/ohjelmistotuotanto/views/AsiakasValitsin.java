package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.data.AsiakasData;
import com.example.ohjelmistotuotanto.entities.Asiakas;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class AsiakasValitsin {

    public Asiakas naytaValintaIkkuna(Window owner) {
        Stage stage = new Stage();
        stage.setTitle("Valitse asiakas");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);

        TableView<Asiakas> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Asiakas, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        TableColumn<Asiakas, String> nimiCol = new TableColumn<>("Nimi");
        nimiCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNimi()));
        TableColumn<Asiakas, String> sahkopostiCol = new TableColumn<>("Sähköposti");
        sahkopostiCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSahkoposti()));

        tableView.getColumns().addAll(idCol, nimiCol, sahkopostiCol);

        idCol.setMinWidth(50);
        idCol.setMaxWidth(50);
        idCol.setResizable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        List<Asiakas> asiakkaat = new AsiakasData().haeAsiakkaat();
        tableView.setItems(FXCollections.observableArrayList(asiakkaat));

        Button valitseBtn = new Button("Valitse");
        final Asiakas[] valittu = {null};

        valitseBtn.setOnAction(e -> {
            valittu[0] = tableView.getSelectionModel().getSelectedItem();
            stage.close();
        });

        VBox vbox = new VBox(10, tableView, valitseBtn);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        valitseBtn.setId("custom-button");

        stage.setScene(scene);
        stage.showAndWait();

        return valittu[0];
    }
}
