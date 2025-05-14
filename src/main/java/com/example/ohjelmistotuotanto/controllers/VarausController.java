package com.example.ohjelmistotuotanto.controllers;

import com.example.ohjelmistotuotanto.data.LaskuData;
import com.example.ohjelmistotuotanto.data.MokkiData;
import com.example.ohjelmistotuotanto.data.VarausData;
import com.example.ohjelmistotuotanto.entities.Asiakas;
import com.example.ohjelmistotuotanto.entities.Lasku;
import com.example.ohjelmistotuotanto.entities.Mokki;
import com.example.ohjelmistotuotanto.entities.Varaus;
import com.example.ohjelmistotuotanto.utils.SovellusTila;
import com.example.ohjelmistotuotanto.utils.Utilities;
import com.example.ohjelmistotuotanto.views.AlkuIkkuna;
import com.example.ohjelmistotuotanto.views.AsiakasValitsin;
import com.example.ohjelmistotuotanto.views.MokkiValitsin;
import com.example.ohjelmistotuotanto.views.VarausIkkuna;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class VarausController {
    private final VarausData varausData;
    private final LaskuData laskuData;
    private final VarausIkkuna varausIkkuna;
    private final MokkiData mokkiData;

    public VarausController(VarausIkkuna varausIkkuna) {
        this.varausData = new VarausData();
        this.laskuData = new LaskuData();
        this.mokkiData = new MokkiData();
        this.varausIkkuna = varausIkkuna;
        initialize();

    }
    private void initialize() {
        varausIkkuna.getValitseAsiakasBtn().setOnMouseClicked(mouseEvent -> {
            AsiakasValitsin valitsin = new AsiakasValitsin();
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Asiakas valittu = valitsin.naytaValintaIkkuna(stage);
            if (valittu != null) {
                varausIkkuna.getAsiakasIdTextField().setText(valittu.getId() + " - " + valittu.getNimi());
            }
        });

        varausIkkuna.getValitseMokkiBtn().setOnAction(e -> {
            MokkiValitsin valitsin = new MokkiValitsin();
            Mokki valittu = valitsin.naytaValintaIkkuna(((Node) e.getSource()).getScene().getWindow());

            if (valittu != null) {
                varausIkkuna.getMokkiIdTextField().setText(valittu.getId() + " - " + valittu.getOsoite());
            }
        });

        varausIkkuna.getLisaaVarausPainike().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lisaaVaraus();
            }
        });
        varausIkkuna.getPoistaVarausPainike().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Haluatko varmasti poistaa varauksen?");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK){
                    poistaVaraus();
                }

            }
        });
        varausIkkuna.getVarausTakaisin().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new AlkuIkkuna().show();
            }
        });
        muokkaaVaraus();
        paivitaVarausLista();
    }

    private void muokkaaVaraus() {
        TableView<Varaus> tableView = varausIkkuna.getVarausTiedotTableView();
        boolean isAdmin = SovellusTila.getKirjautunutKayttaja().isAdmin();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterHours = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        tableView.setEditable(isAdmin);

        tableView.getColumns().forEach(column -> {
            if (column.getText().equals("Aloituspäivä")) {
                TableColumn<Varaus, LocalDate> col = (TableColumn<Varaus, LocalDate>) column;
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
                    Varaus varaus = e.getRowValue();
                    LocalDate uusiPaiva = e.getNewValue();
                    if (uusiPaiva != null) {
                        varaus.setAloituspaiva(uusiPaiva);
                        tableView.refresh();
                        varausData.paivitaVaraus(varaus);
                        Utilities.showAlertDialog(Alert.AlertType.WARNING, "Varaukseen yhdistetty lasku ei päivity automaattisesti! Päivitä laskun tiedot manuaalisesti.");
                    }
                });
            }else if (column.getText().equals("Lopetuspäivä")) {
                TableColumn<Varaus, LocalDate> col = (TableColumn<Varaus, LocalDate>) column;
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
                            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virheellinen päivämäärä");
                            return null;
                        }
                    }
                };

                col.setCellFactory(TextFieldTableCell.forTableColumn(converter));
                col.setOnEditCommit(e -> {
                    Varaus varaus = e.getRowValue();
                    LocalDate uusiPaiva = e.getNewValue();
                    if (uusiPaiva != null) {
                        varaus.setLopetuspaiva(uusiPaiva);
                        tableView.refresh();
                        varausData.paivitaVaraus(varaus);
                    }
                });
            }else if (column.getText().equals("Päivitetty")) {
                TableColumn<Varaus, LocalDateTime> col = (TableColumn<Varaus, LocalDateTime>) column;
                col.setCellFactory(c -> new TableCell<Varaus, LocalDateTime>() {
                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(formatterHours.format(item));
                        }
                    }
                });
            }else if (column.getText().equals("Luotu")) {
                TableColumn<Varaus, LocalDateTime> col = (TableColumn<Varaus, LocalDateTime>) column;
                col.setCellFactory(c -> new TableCell<Varaus, LocalDateTime>() {
                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(formatterHours.format(item));
                        }
                    }
                });
            }
        });
    }

    private void lisaaVaraus() {
        try {
            String asiakasTeksti = varausIkkuna.getAsiakasIdTextField().getText();
            String asiakasIdStr = asiakasTeksti.split("\\s+")[0];
            int asiakasId = Integer.parseInt(asiakasIdStr);

            String mokkiTeksti = varausIkkuna.getMokkiIdTextField().getText();
            String mokkiIdStr = mokkiTeksti.split("\\s+")[0];
            int mokkiId = Integer.parseInt(mokkiIdStr);

            LocalDate aloitus = varausIkkuna.getAloituspaivaDatePicker().getValue();
            LocalDate lopetus = varausIkkuna.getLopetuspaivaDatePicker().getValue();
            String laskutustapa = varausIkkuna.getLaskutustapaComboBox().getValue();

            float hintaPerYo = mokkiData.getHintaPerYo(mokkiId);
            mokkiData.setTilaTrueById(mokkiId);
            float hinta = laskuData.laskeHinta(hintaPerYo, aloitus, lopetus);

            LocalDate erapaiva = lopetus.plusDays(14);

            Lasku uusiLasku = new Lasku(hinta, laskutustapa, erapaiva, false);
            laskuData.lisaaLasku(uusiLasku);

            int laskuId = uusiLasku.getId();

            if (laskuId == 0) {
                Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virhe laskua luodessa. Laskua ei voitu luoda.");
                return;
            }
            Varaus uusiVaraus = new Varaus(asiakasId, mokkiId, laskuId, aloitus, lopetus);

            varausData.lisaaVaraus(uusiVaraus);
            paivitaVarausLista();

            varausIkkuna.getAsiakasIdTextField().clear();
            varausIkkuna.getMokkiIdTextField().clear();
            varausIkkuna.getAloituspaivaDatePicker().setValue(null);
            varausIkkuna.getLopetuspaivaDatePicker().setValue(null);
            varausIkkuna.getLaskutustapaComboBox().setValue(null);
            varausIkkuna.getLaskutustapaComboBox().setPromptText("Valitse vaihtoehto");
        } catch (NumberFormatException ex) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Varauksen ja laskun luonti epäonnistui. Tarkista ID ja päivämäärät");
            ex.printStackTrace();
        }
    }

    private void poistaVaraus() {
        Varaus valittu = varausIkkuna.getVarausTiedotTableView().getSelectionModel().getSelectedItem();
        if (valittu != null) {
            varausData.poistaVaraus(valittu.getId());
            int mokkiId = valittu.getMokki_id();
            mokkiData.setTilaFalseById(mokkiId);
            paivitaVarausLista();
        }
    }

    private void paivitaVarausLista() {
        List<Varaus> varaukset = varausData.haeVaraukset();
        varausIkkuna.getVarausTiedotTableView().getItems().setAll(varaukset);
    }
}