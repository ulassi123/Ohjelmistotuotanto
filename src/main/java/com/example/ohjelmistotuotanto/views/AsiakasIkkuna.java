package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.controllers.AsiakasController;
import com.example.ohjelmistotuotanto.utils.IkkunanVaihto;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import static com.example.ohjelmistotuotanto.utils.Utilities.APP_KORKEUS;
import static com.example.ohjelmistotuotanto.utils.Utilities.APP_LEVEYS;

public class AsiakasIkkuna {
    private CheckBox yritysCheckBox = new CheckBox("Yritys");
    private TextField sahkopostiTextField = new TextField();
    private TextField nimiTextField = new TextField();
    private TextField puhelinnumeroTextField = new TextField();
    private TextField maaTextField = new TextField();
    private Button lisaaAsiakasPainike = new Button("Lisää");
    private Button poistaAsiakasPainike = new Button("Poista");
    private Button asiakasTakaisin = new Button("Takaisin");
    private final TableView<AsiakasIkkuna> asiakasTiedotTableView = new TableView<>();
    boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();

    public void show(){
        Scene kehys = luoKehys();
        kehys.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        new AsiakasController(this);
        IkkunanVaihto.vaihdaKehys(kehys);
    }
    private Scene luoKehys(){
        BorderPane asiakasIkkuna = new BorderPane();
        asiakasIkkuna.setPadding(new Insets(20,20,20,20));

        VBox asiakasTietoVBox = new VBox();
        asiakasTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        asiakasTietoVBox.setSpacing(10);
        asiakasTietoVBox.getChildren().addAll(sahkopostiTextField, nimiTextField, puhelinnumeroTextField,
                maaTextField, yritysCheckBox, lisaaAsiakasPainike, poistaAsiakasPainike);

        VBox.setMargin(lisaaAsiakasPainike, new Insets(15,0,0,0));

        sahkopostiTextField.getStyleClass().add("textfield-modern-editable");
        nimiTextField.getStyleClass().add("textfield-modern-editable");
        puhelinnumeroTextField.getStyleClass().add("textfield-modern-editable");
        maaTextField.getStyleClass().add("textfield-modern-editable");
        yritysCheckBox.getStyleClass().add("checkbox-custom");
        lisaaAsiakasPainike.setId("custom-button");
        poistaAsiakasPainike.setId("delete-button");
        asiakasTakaisin.setId("custom-button");
        asiakasIkkuna.setId("custom-background");

        sahkopostiTextField.setMaxWidth(180);
        sahkopostiTextField.setMinWidth(180);
        nimiTextField.setMaxWidth(180);
        nimiTextField.setMinWidth(180);
        puhelinnumeroTextField.setMaxWidth(180);
        puhelinnumeroTextField.setMinWidth(180);
        maaTextField.setMaxWidth(180);
        maaTextField.setMinWidth(180);
        lisaaAsiakasPainike.setMaxWidth(180);
        lisaaAsiakasPainike.setMinWidth(180);
        poistaAsiakasPainike.setMaxWidth(180);
        poistaAsiakasPainike.setMinWidth(180);

        TableColumn<AsiakasIkkuna, Integer> asiakas_idCol = new TableColumn<>("Asiakas ID");
        asiakas_idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<AsiakasIkkuna, String> sahkopostiCol = new TableColumn<>("Sähköposti");
        sahkopostiCol.setCellValueFactory(new PropertyValueFactory<>("sahkoposti"));
        TableColumn<AsiakasIkkuna, String> nimiCol = new TableColumn<>("Nimi");
        nimiCol.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        TableColumn<AsiakasIkkuna, String> puhelinnumeroCol = new TableColumn<>("Puhelinnumero");
        puhelinnumeroCol.setCellValueFactory(new PropertyValueFactory<>("puhelinnumero"));
        TableColumn<AsiakasIkkuna, String> maaCol = new TableColumn<>("Maa");
        maaCol.setCellValueFactory(new PropertyValueFactory<>("maa"));
        TableColumn<AsiakasIkkuna, Boolean> yritysCol = new TableColumn<>("Yritys");
        yritysCol.setCellValueFactory(new PropertyValueFactory<>("yritys"));

        yritysCol.setMaxWidth(80);
        yritysCol.setMinWidth(80);
        asiakas_idCol.setMaxWidth(100);
        asiakas_idCol.setMinWidth(100);
        maaCol.setMaxWidth(100);
        maaCol.setMinWidth(100);

        asiakasTiedotTableView.getColumns().addAll(asiakas_idCol, nimiCol, sahkopostiCol, puhelinnumeroCol, maaCol, yritysCol);
        asiakasTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        sahkopostiTextField.setPromptText("Sähköposti");
        nimiTextField.setPromptText("Nimi");
        puhelinnumeroTextField.setPromptText("Puhelinnumero");
        maaTextField.setPromptText("Maa");

        asiakasTiedotTableView.setEditable(true);
        asiakasIkkuna.setLeft(asiakasTietoVBox);
        asiakasIkkuna.setCenter(asiakasTiedotTableView);
        asiakasIkkuna.setBottom(asiakasTakaisin);

        asiakasTiedotTableView.getStyleClass().add("table-view");

        if(isAdmin){
            asiakasIkkuna.setLeft(null);
            asiakasIkkuna.setLeft(asiakasTietoVBox);
            asiakasIkkuna.setCenter(asiakasTiedotTableView);
        }else if(!isAdmin){
            asiakasIkkuna.setLeft(null);
            asiakasIkkuna.setCenter(asiakasTiedotTableView);
        }

        return new Scene(asiakasIkkuna, APP_LEVEYS, APP_KORKEUS);
    }

    public Button getAsiakasTakaisin() {
        return asiakasTakaisin;
    }

    public void setAsiakasTakaisin(Button asiakasTakaisin) {
        this.asiakasTakaisin = asiakasTakaisin;
    }

    public TableView getAsiakasTiedotTableView() {
        return asiakasTiedotTableView;
    }

    public Button getPoistaAsiakasPainike() {
        return poistaAsiakasPainike;
    }

    public void setPoistaAsiakasPainike(Button poistaAsiakasPainike) {
        this.poistaAsiakasPainike = poistaAsiakasPainike;
    }

    public Button getLisaaAsiakasPainike() {
        return lisaaAsiakasPainike;
    }

    public void setLisaaAsiakasPainike(Button lisaaAsiakasPainike) {
        this.lisaaAsiakasPainike = lisaaAsiakasPainike;
    }

    public TextField getMaaTextField() {
        return maaTextField;
    }

    public void setMaaTextField(TextField maaTextField) {
        this.maaTextField = maaTextField;
    }

    public TextField getPuhelinnumeroTextField() {
        return puhelinnumeroTextField;
    }

    public void setPuhelinnumeroTextField(TextField puhelinnumeroTextField) {
        this.puhelinnumeroTextField = puhelinnumeroTextField;
    }

    public TextField getNimiTextField() {
        return nimiTextField;
    }

    public void setNimiTextField(TextField nimiTextField) {
        this.nimiTextField = nimiTextField;
    }

    public TextField getSahkopostiTextField() {
        return sahkopostiTextField;
    }

    public void setSahkopostiTextField(TextField sahkopostiTextField) {
        this.sahkopostiTextField = sahkopostiTextField;
    }

    public CheckBox getYritysCheckBox() {
        return yritysCheckBox;
    }

    public void setYritysCheckBox(CheckBox yritysCheckBox) {
        this.yritysCheckBox = yritysCheckBox;
    }
}
