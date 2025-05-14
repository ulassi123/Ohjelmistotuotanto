package com.example.ohjelmistotuotanto.utils;

import com.example.ohjelmistotuotanto.data.LaskuData;
import com.example.ohjelmistotuotanto.entities.Asiakas;
import com.example.ohjelmistotuotanto.entities.Lasku;
import com.example.ohjelmistotuotanto.entities.Varaus;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TulostaPdf {

    public static void tulostaLaskuPDF(Lasku lasku) throws IOException {
        LaskuData laskuRepo = new LaskuData();
        Asiakas asiakas = laskuRepo.haeAsiakasByLaskuId(lasku.getId());
        Varaus varaus = laskuRepo.haeVarausByLaskuId(lasku.getId());

        if (asiakas == null) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "PDF-tiedostoa ei voitu luoda, koska asiakasta ei löydetty. Tarkista onko laskulle olemassa varausta." +
                    " PDF-tiedostoa ei voi luoda, jos laskulle ei ole varausta");
            return;
        }

        File pohja = new File("LaskutPDF/mallipohja.pdf");
        PDDocument document = PDDocument.load(pohja);

        PDPage page = document.getPage(0);

        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
        contentStream.setTextMatrix(Matrix.getTranslateInstance(50,520));

        contentStream.showText("Asiakas ID: " + asiakas.getId());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Nimi: " + asiakas.getNimi());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Sähköposti: " + asiakas.getSahkoposti());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Puhelinnumero: " + asiakas.getPuhelinnumero());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Maa: " + asiakas.getMaa());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Yritys: " + (asiakas.getYritys() ? "Kyllä" : "Ei"));

        contentStream.newLineAtOffset(0, -65);
        contentStream.showText("Lasku ID: " + lasku.getId());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Maksettu: " + (lasku.isTila() ? "Kyllä" : "Ei"));
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Hinta: " + lasku.getHinta() + " €");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Laskutustapa: " + lasku.getLaskutustapa());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Eräpäivä: " + lasku.getErapaiva().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        contentStream.newLineAtOffset(0, -60);
        contentStream.showText("Varaus ID: " + varaus.getId());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Aloituspäivä: " + varaus.getAloituspaiva().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Lopetuspäivä: " + varaus.getLopetuspaiva().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        contentStream.endText();
        contentStream.close();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
        File tulos = fileChooser.showSaveDialog(new Stage());

        if (tulos != null) {
            document.save(tulos);
        }
        document.close();
    }
}