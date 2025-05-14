package com.example.ohjelmistotuotanto;

import com.example.ohjelmistotuotanto.utils.IkkunanVaihto;
import com.example.ohjelmistotuotanto.views.KirjautumisIkkuna;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Luokka graafiselle käyttöliittymälle
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("lomaKylä");
        IkkunanVaihto.setMainStage(stage);
        stage.setResizable(false);
        stage.getIcons().add(new Image("parhainlogoikina.png"));
        new KirjautumisIkkuna().show();
    }
}