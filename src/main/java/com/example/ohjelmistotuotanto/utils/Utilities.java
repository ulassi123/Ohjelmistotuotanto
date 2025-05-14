package com.example.ohjelmistotuotanto.utils;

import javafx.scene.control.Alert;
import javafx.util.StringConverter;

public class Utilities {
    public static final int APP_LEVEYS = 1000;
    public static final int APP_KORKEUS = 700;

    public static void showAlertDialog(Alert.AlertType alertType, String message){
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static class FloatStringConverter extends StringConverter<Float> {
        @Override
        public String toString(Float object) {
            return object != null ? object.toString() : "";
        }

        @Override
        public Float fromString(String string) {
            if (string == null || string.trim().isEmpty()) {
                throw new NumberFormatException("Syöte on tyhjä");
            }

            try {
                string = string.replace(',', '.');
                return Float.parseFloat(string);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Syötä luku muodossa 123.45");
            }
        }
    }

}
