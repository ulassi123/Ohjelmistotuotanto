package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.controllers.VarausController;
import com.example.ohjelmistotuotanto.data.AsiakasData;
import com.example.ohjelmistotuotanto.data.MokkiData;
import com.example.ohjelmistotuotanto.entities.Varaus;
import com.example.ohjelmistotuotanto.utils.IkkunanVaihto;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.ohjelmistotuotanto.utils.Utilities.APP_KORKEUS;
import static com.example.ohjelmistotuotanto.utils.Utilities.APP_LEVEYS;

public class VarausIkkuna {
    private final TableView<Varaus> varausTiedotTableView = new TableView<>();
    private TextField asiakasIdTextField = new TextField();
    private TextField mokkiIdTextField = new TextField();
    private ComboBox<String> laskutustapaComboBox = new ComboBox<>();
    private DatePicker aloituspaivaDatePicker = new DatePicker();
    private DatePicker lopetuspaivaDatePicker = new DatePicker();
    private Button lisaaVarausPainike = new Button("Luo varaus ja lasku");
    private Button poistaVarausPainike = new Button("Poista varaus");
    private Button varausTakaisin = new Button("Takaisin");
    boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();
    AsiakasData asiakasRepository = new AsiakasData();
    MokkiData mokkiData = new MokkiData();
    Button valitseAsiakasBtn = new Button("Valitse asiakas");
    Button valitseMokkiBtn = new Button("Valitse mökki");

    public void show(){
        Scene kehys = luoKehys();
        kehys.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        new VarausController(this);
        IkkunanVaihto.vaihdaKehys(kehys);
    }
    private Scene luoKehys(){
        BorderPane varausIkkuna = new BorderPane();
        varausIkkuna.setPadding(new Insets(20,20,20,20));

        VBox varausTietoVBox = new VBox();
        varausTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        varausTietoVBox.setSpacing(10);
        varausTietoVBox.getChildren().addAll(valitseAsiakasBtn, asiakasIdTextField, valitseMokkiBtn, mokkiIdTextField, laskutustapaComboBox, aloituspaivaDatePicker, lopetuspaivaDatePicker, lisaaVarausPainike, poistaVarausPainike);

        VBox.setMargin(lisaaVarausPainike, new Insets(15,0,0,0));

        lisaaVarausPainike.setId("custom-button");
        poistaVarausPainike.setId("delete-button");
        varausTakaisin.setId("custom-button");
        mokkiIdTextField.getStyleClass().add("textfield-modern");
        asiakasIdTextField.getStyleClass().add("textfield-modern");
        valitseAsiakasBtn.getStyleClass().add("button-11");
        valitseMokkiBtn.getStyleClass().add("button-11");
        varausIkkuna.setId("custom-background");

        aloituspaivaDatePicker.getStyleClass().add("datepicker-modern");
        lopetuspaivaDatePicker.getStyleClass().add("datepicker-modern");
        laskutustapaComboBox.getStyleClass().add("combobox-modern");

        asiakasIdTextField.setPromptText("Asiakkaan ID");
        mokkiIdTextField.setPromptText("Valittu mökki");
        laskutustapaComboBox.getItems().addAll(
                "E-lasku",
                "Paperilasku"
        );
        laskutustapaComboBox.setMinWidth(220);
        laskutustapaComboBox.setMaxWidth(220);
        valitseAsiakasBtn.setMinWidth(220);
        valitseAsiakasBtn.setMaxWidth(220);
        valitseMokkiBtn.setMinWidth(220);
        valitseMokkiBtn.setMaxWidth(220);
        lisaaVarausPainike.setMinWidth(220);
        lisaaVarausPainike.setMaxWidth(220);
        poistaVarausPainike.setMinWidth(220);
        poistaVarausPainike.setMaxWidth(220);
        laskutustapaComboBox.setPromptText("Valitse laskutustapa");
        aloituspaivaDatePicker.setPromptText("Aloituspäivä");
        lopetuspaivaDatePicker.setPromptText("Lopetuspäivä");

        TableColumn<Varaus, Integer> varausIdCol = new TableColumn<>("Varaus ID");
        varausIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Varaus, Integer> laskuIdCol = new TableColumn<>("Lasku ID");
        laskuIdCol.setCellValueFactory(new PropertyValueFactory<>("lasku_id"));
        TableColumn<Varaus, LocalDate> aloituspaivaCol = new TableColumn<>("Aloituspäivä");
        aloituspaivaCol.setCellValueFactory(new PropertyValueFactory<>("aloituspaiva"));
        TableColumn<Varaus, LocalDate> lopetuspaivaCol = new TableColumn<>("Lopetuspäivä");
        lopetuspaivaCol.setCellValueFactory(new PropertyValueFactory<>("lopetuspaiva"));
        TableColumn<Varaus, LocalDateTime> luotuCol = new TableColumn<>("Luotu");
        luotuCol.setCellValueFactory(new PropertyValueFactory<>("luotu"));
        TableColumn<Varaus, LocalDateTime> paivitettyCol = new TableColumn<>("Päivitetty");
        paivitettyCol.setCellValueFactory(new PropertyValueFactory<>("paivitetty"));
        TableColumn<Varaus, String> asiakasNimiCol = new TableColumn<>("Nimi");

        asiakasNimiCol.setCellValueFactory(cellData -> {
            Varaus varaus = cellData.getValue();
            String nimi = asiakasRepository.getNimiById(varaus.getAsiakas_id());
            return new SimpleStringProperty(nimi != null ? nimi : "");
        });

        TableColumn<Varaus, String> mokkiOsoiteCol = new TableColumn<>("Osoite");
        mokkiOsoiteCol.setCellValueFactory(cellData -> {
            Varaus varaus = cellData.getValue();
            String osoite = mokkiData.getOsoiteById(varaus.getMokki_id());
            return new SimpleStringProperty(osoite != null ? osoite : "");
        });

        varausTiedotTableView.getColumns().addAll(varausIdCol, laskuIdCol, asiakasNimiCol, mokkiOsoiteCol, aloituspaivaCol, lopetuspaivaCol, luotuCol, paivitettyCol);
        varausTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        asiakasIdTextField.setEditable(false);
        asiakasIdTextField.setPromptText("Valittu asiakas");

        varausIkkuna.setCenter(varausTiedotTableView);
        varausIkkuna.setLeft(varausTietoVBox);
        varausIkkuna.setBottom(varausTakaisin);

        if(isAdmin){
            varausIkkuna.setLeft(null);
            varausIkkuna.setLeft(varausTietoVBox);
            varausIkkuna.setCenter(varausTiedotTableView);
        }else if(!isAdmin){
            varausIkkuna.setLeft(null);
            varausIkkuna.setCenter(varausTiedotTableView);
        }

        return new Scene(varausIkkuna, APP_LEVEYS, APP_KORKEUS);
    }

    public Button getValitseMokkiBtn() {
        return valitseMokkiBtn;
    }

    public void setValitseMokkiBtn(Button valitseMokkiBtn) {
        this.valitseMokkiBtn = valitseMokkiBtn;
    }

    public Button getValitseAsiakasBtn() {
        return valitseAsiakasBtn;
    }

    public void setValitseAsiakasBtn(Button valitseAsiakasBtn) {
        this.valitseAsiakasBtn = valitseAsiakasBtn;
    }

    public TableView<Varaus> getVarausTiedotTableView() {
        return varausTiedotTableView;
    }

    public TextField getAsiakasIdTextField() {
        return asiakasIdTextField;
    }

    public void setAsiakasIdTextField(TextField asiakasIdTextField) {
        this.asiakasIdTextField = asiakasIdTextField;
    }

    public TextField getMokkiIdTextField() {
        return mokkiIdTextField;
    }

    public void setMokkiIdTextField(TextField mokkiIdTextField) {
        this.mokkiIdTextField = mokkiIdTextField;
    }

    public ComboBox<String> getLaskutustapaComboBox() {
        return laskutustapaComboBox;
    }

    public void setLaskutustapaComboBox(ComboBox<String> laskutustapaComboBox) {
        this.laskutustapaComboBox = laskutustapaComboBox;
    }

    public DatePicker getAloituspaivaDatePicker() {
        return aloituspaivaDatePicker;
    }

    public void setAloituspaivaDatePicker(DatePicker aloituspaivaDatePicker) {
        this.aloituspaivaDatePicker = aloituspaivaDatePicker;
    }

    public DatePicker getLopetuspaivaDatePicker() {
        return lopetuspaivaDatePicker;
    }

    public void setLopetuspaivaDatePicker(DatePicker lopetuspaivaDatePicker) {
        this.lopetuspaivaDatePicker = lopetuspaivaDatePicker;
    }

    public Button getLisaaVarausPainike() {
        return lisaaVarausPainike;
    }

    public void setLisaaVarausPainike(Button lisaaVarausPainike) {
        this.lisaaVarausPainike = lisaaVarausPainike;
    }

    public Button getPoistaVarausPainike() {
        return poistaVarausPainike;
    }

    public void setPoistaVarausPainike(Button poistaVarausPainike) {
        this.poistaVarausPainike = poistaVarausPainike;
    }

    public Button getVarausTakaisin() {
        return varausTakaisin;
    }

    public void setVarausTakaisin(Button varausTakaisin) {
        this.varausTakaisin = varausTakaisin;
    }
}
