package com.example.ohjelmistotuotanto.utils;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class IkkunanVaihto {
    private static Stage alkuIkkuna;

    public static void setMainStage(Stage stage){
        alkuIkkuna = stage;
    }

    public static void vaihdaKehys(Scene scene){
        if(alkuIkkuna != null){
            alkuIkkuna.setScene(scene);
            alkuIkkuna.show();
        }
    }
}
