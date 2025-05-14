package com.example.ohjelmistotuotanto.data;

import com.example.ohjelmistotuotanto.entities.Varaus;
import com.example.ohjelmistotuotanto.utils.DbConnect;
import com.example.ohjelmistotuotanto.utils.Utilities;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VarausData {
    DbConnect dbConnect = new DbConnect();

    public void lisaaVaraus(Varaus varaus) {
        String sql = "INSERT INTO varaus (asiakas_id, mokki_id, lasku_id, aloituspaiva, lopetuspaiva, luotu, paivitetty) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnect.yhdista();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, varaus.getAsiakas_id());
            stmt.setInt(2, varaus.getMokki_id());
            stmt.setInt(3, varaus.getLasku_id());
            stmt.setDate(4, Date.valueOf(varaus.getAloituspaiva()));
            stmt.setDate(5, Date.valueOf(varaus.getLopetuspaiva()));
            stmt.setTimestamp(6, Timestamp.valueOf(varaus.getLuotu()));
            stmt.setTimestamp(7, Timestamp.valueOf(varaus.getPaivitetty()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Varausta ei voitu luoda.");
            e.printStackTrace();
        }
    }

    public List<Varaus> haeVaraukset() {
        List<Varaus> varaukset = new ArrayList<>();
        String sql = "SELECT * FROM varaus";

        try (Connection conn = dbConnect.yhdista();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                varaukset.add(new Varaus(
                        rs.getInt("id"),
                        rs.getInt("asiakas_id"),
                        rs.getInt("mokki_id"),
                        rs.getInt("lasku_id"),
                        rs.getDate("aloituspaiva").toLocalDate(),
                        rs.getDate("lopetuspaiva").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return varaukset;
    }

    public void poistaVaraus(int varausId) {
        String sql = "DELETE FROM varaus WHERE id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, varausId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void paivitaVaraus(Varaus varaus) {
        String sql = "UPDATE varaus SET aloituspaiva = ?, lopetuspaiva = ?, paivitetty = ?, asiakas_id = ?, mokki_id = ?, lasku_id = ? WHERE id = ?";

        try (PreparedStatement stmt = dbConnect.yhdista().prepareStatement(sql)) {

            stmt.setDate(1,Date.valueOf(varaus.getAloituspaiva()));
            stmt.setDate(2,Date.valueOf(varaus.getLopetuspaiva()));
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(4, varaus.getAsiakas_id());
            stmt.setInt(5, varaus.getMokki_id());
            stmt.setInt(6, varaus.getLasku_id());
            stmt.setInt(7, varaus.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virhe tietokantaa päivittäessä.");
            e.printStackTrace();
        }
    }

}
