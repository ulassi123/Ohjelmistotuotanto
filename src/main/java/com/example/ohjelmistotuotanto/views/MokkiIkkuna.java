package com.example.ohjelmistotuotanto.views;

import com.example.ohjelmistotuotanto.controllers.MokkiController;
import com.example.ohjelmistotuotanto.entities.Mokki;
import com.example.ohjelmistotuotanto.utils.IkkunanVaihto;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Date;

import static com.example.ohjelmistotuotanto.utils.Utilities.APP_KORKEUS;
import static com.example.ohjelmistotuotanto.utils.Utilities.APP_LEVEYS;

public class MokkiIkkuna {
    private final TableView<Mokki> mokkiTiedotTableView = new TableView<>();
    private TextField osoiteTextField = new TextField();
    private TextField huoneetTextField = new TextField();
    private TextField kokoTextField = new TextField();
    private TextField hinta_per_yoTextField = new TextField();
    Button lisaaMokkiPainike = new Button("Lisää");
    Button poistaMokkiPainike = new Button("Poista");
    Button mokkiTakaisin = new Button("Takaisin");
    boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();

    public void show(){
        Scene kehys = luoKehys();
        kehys.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        new MokkiController(this);
        IkkunanVaihto.vaihdaKehys(kehys);
    }

    private Scene luoKehys(){
        BorderPane mokkiIkkuna = new BorderPane();
        mokkiIkkuna.setPadding(new Insets(20,20,20,20));

        VBox mokkiTietoVBox = new VBox();
        mokkiTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        mokkiTietoVBox.setSpacing(10);
        mokkiTietoVBox.getChildren().addAll(osoiteTextField, huoneetTextField, kokoTextField, hinta_per_yoTextField, lisaaMokkiPainike, poistaMokkiPainike);

        VBox.setMargin(lisaaMokkiPainike, new Insets(15,0,0,0));

        osoiteTextField.getStyleClass().add("textfield-modern-editable");
        huoneetTextField.getStyleClass().add("textfield-modern-editable");
        kokoTextField.getStyleClass().add("textfield-modern-editable");
        hinta_per_yoTextField.getStyleClass().add("textfield-modern-editable");
        lisaaMokkiPainike.setId("custom-button");
        poistaMokkiPainike.setId("delete-button");
        mokkiTakaisin.setId("custom-button");
        mokkiIkkuna.setId("custom-background");
        osoiteTextField.setPromptText("Osoite");
        huoneetTextField.setPromptText("Huoneiden määrä");
        hinta_per_yoTextField.setPromptText("Hinta yöltä");
        kokoTextField.setPromptText("Koko");

        TableColumn<Mokki, Integer> mokki_IdCol = new TableColumn<>("Mökki ID");
        mokki_IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Mokki, String> osoiteCol = new TableColumn<>("Osoite");
        osoiteCol.setCellValueFactory(new PropertyValueFactory<>("osoite"));
        TableColumn<Mokki, Boolean> tilaCol1 = new TableColumn<>("Varattu?");
        tilaCol1.setCellValueFactory(new PropertyValueFactory<>("tila"));
        TableColumn<Mokki, Integer> huoneetCol = new TableColumn<>("Huoneet");
        huoneetCol.setCellValueFactory(new PropertyValueFactory<>("huoneet"));
        TableColumn<Mokki, Integer> kokoCol = new TableColumn<>("Koko (m²)");
        kokoCol.setCellValueFactory(new PropertyValueFactory<>("koko"));
        TableColumn<Mokki, Float> hinta_per_yoCol = new TableColumn<>("Hinta/yö");
        hinta_per_yoCol.setCellValueFactory(new PropertyValueFactory<>("hintaPerYo"));
        kokoCol.setCellValueFactory(new PropertyValueFactory<>("koko"));
        TableColumn<Mokki, Date> luotuCol = new TableColumn<>("Luotu");
        luotuCol.setCellValueFactory(new PropertyValueFactory<>("luotu"));
        TableColumn<Mokki, Date> paivitettyCol = new TableColumn<>("Päivitetty");
        paivitettyCol.setCellValueFactory(new PropertyValueFactory<>("paivitetty"));

        mokkiTiedotTableView.getColumns().addAll(mokki_IdCol, osoiteCol, tilaCol1, huoneetCol, kokoCol, hinta_per_yoCol, luotuCol, paivitettyCol);

        osoiteTextField.setMaxWidth(180);
        osoiteTextField.setMinWidth(180);
        huoneetTextField.setMaxWidth(180);
        huoneetTextField.setMinWidth(180);
        kokoTextField.setMaxWidth(180);
        kokoTextField.setMinWidth(180);
        hinta_per_yoTextField.setMaxWidth(180);
        hinta_per_yoTextField.setMinWidth(180);
        lisaaMokkiPainike.setMaxWidth(180);
        lisaaMokkiPainike.setMinWidth(180);
        poistaMokkiPainike.setMaxWidth(180);
        poistaMokkiPainike.setMinWidth(180);

        mokki_IdCol.setMinWidth(100);
        mokki_IdCol.setMaxWidth(100);
        tilaCol1.setMinWidth(80);
        tilaCol1.setMaxWidth(80);
        huoneetCol.setMinWidth(80);
        huoneetCol.setMaxWidth(80);
        kokoCol.setMinWidth(80);
        kokoCol.setMaxWidth(80);
        hinta_per_yoCol.setMinWidth(80);
        hinta_per_yoCol.setMaxWidth(80);

        mokkiTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        mokkiIkkuna.setCenter(mokkiTiedotTableView);
        mokkiIkkuna.setLeft(mokkiTietoVBox);
        mokkiIkkuna.setBottom(mokkiTakaisin);

        if(isAdmin){
            mokkiIkkuna.setLeft(null);
            mokkiIkkuna.setLeft(mokkiTietoVBox);
            mokkiIkkuna.setCenter(mokkiTiedotTableView);
        }else if(!isAdmin){
            mokkiIkkuna.setLeft(null);
            mokkiIkkuna.setCenter(mokkiTiedotTableView);
        }

        return new Scene(mokkiIkkuna, APP_LEVEYS, APP_KORKEUS);
    }

    public TableView<Mokki> getMokkiTiedotTableView() {
        return mokkiTiedotTableView;
    }

    public TextField getOsoiteTextField() {
        return osoiteTextField;
    }

    public void setOsoiteTextField(TextField osoiteTextField) {
        this.osoiteTextField = osoiteTextField;
    }

    public TextField getHuoneetTextField() {
        return huoneetTextField;
    }

    public void setHuoneetTextField(TextField huoneetTextField) {
        this.huoneetTextField = huoneetTextField;
    }

    public TextField getKokoTextField() {
        return kokoTextField;
    }

    public void setKokoTextField(TextField kokoTextField) {
        this.kokoTextField = kokoTextField;
    }

    public TextField getHinta_per_yoTextField() {
        return hinta_per_yoTextField;
    }

    public void setHinta_per_yoTextField(TextField hinta_per_yoTextField) {
        this.hinta_per_yoTextField = hinta_per_yoTextField;
    }

    public Button getLisaaMokkiPainike() {
        return lisaaMokkiPainike;
    }

    public void setLisaaMokkiPainike(Button lisaaMokkiPainike) {
        this.lisaaMokkiPainike = lisaaMokkiPainike;
    }

    public Button getPoistaMokkiPainike() {
        return poistaMokkiPainike;
    }

    public void setPoistaMokkiPainike(Button poistaMokkiPainike) {
        this.poistaMokkiPainike = poistaMokkiPainike;
    }

    public Button getMokkiTakaisin() {
        return mokkiTakaisin;
    }

    public void setMokkiTakaisin(Button mokkiTakaisin) {
        this.mokkiTakaisin = mokkiTakaisin;
    }
}
