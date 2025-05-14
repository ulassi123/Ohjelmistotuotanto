package com.example.ohjelmistotuotanto.data;

import com.example.ohjelmistotuotanto.entities.Asiakas;
import com.example.ohjelmistotuotanto.utils.DbConnect;
import com.example.ohjelmistotuotanto.utils.Utilities;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsiakasData {
    DbConnect dbConnect = new DbConnect();

    public List<Asiakas> haeAsiakkaat() {
        List<Asiakas> asiakkaat = new ArrayList<>();
        String sql = "SELECT * FROM asiakas";

        try (Connection conn = dbConnect.yhdista();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Asiakas asiakas = new Asiakas(
                        rs.getInt("id"),
                        rs.getString("sahkoposti"),
                        rs.getString("nimi"),
                        rs.getString("puhelinnumero"),
                        rs.getString("maa"),
                        rs.getBoolean("yritys")
                );
                asiakkaat.add(asiakas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asiakkaat;
    }
    public void lisaaAsiakas(Asiakas asiakas) {
        String sql = "INSERT INTO asiakas (sahkoposti, nimi, puhelinnumero, maa, yritys) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, asiakas.getSahkoposti());
            pstmt.setString(2, asiakas.getNimi());
            pstmt.setString(3, asiakas.getPuhelinnumero());
            pstmt.setString(4, asiakas.getMaa());
            pstmt.setBoolean(5, asiakas.getYritys());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Uutta asiakasta ei voitu luoda. Tarkista antamasi tiedot.");
            e.printStackTrace();
        }
    }

    public void poistaAsiakas(int asiakasId) {
        String sql = "DELETE FROM asiakas WHERE id = ?";
        try (Connection conn = dbConnect.yhdista();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, asiakasId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Asiakasta ei voitu poistaa. Asiakkaan varaukset täytyy poistaa ennen kuin voi poistaa asiakkaan.");
            e.printStackTrace();
        }
    }

    public void paivitaAsiakas(Asiakas asiakas) {
        String sql = "UPDATE asiakas SET sahkoposti = ?, nimi = ?, puhelinnumero = ?, maa = ?, yritys = ? WHERE id = ?";

        try (PreparedStatement stmt = dbConnect.yhdista().prepareStatement(sql)) {

            stmt.setString(1, asiakas.getSahkoposti());
            stmt.setString(2, asiakas.getNimi());
            stmt.setString(3, asiakas.getPuhelinnumero());
            stmt.setString(4, asiakas.getMaa());
            stmt.setBoolean(5, asiakas.getYritys());
            stmt.setInt(6, asiakas.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virhe tietokantaa päivittäessä.");
        }
    }

    public String getNimiById(int asiakasId) {
        String sql = "SELECT nimi FROM asiakas WHERE id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, asiakasId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nimi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
