package com.example.ohjelmistotuotanto.data;

import com.example.ohjelmistotuotanto.entities.Kayttaja;
import com.example.ohjelmistotuotanto.utils.DbConnect;
import com.example.ohjelmistotuotanto.utils.Utilities;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KayttajaData {
    DbConnect dbConnect = new DbConnect();

    public List<Kayttaja> haeKayttajat() {
        List<Kayttaja> kayttajat = new ArrayList<>();
        String sql = "SELECT * FROM kayttaja";

        try (Connection conn = dbConnect.yhdista();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Kayttaja kayttaja = new Kayttaja(
                        rs.getInt("id"),
                        rs.getString("kayttaja_nimi"),
                        rs.getString("salasana"),
                        rs.getBoolean("is_admin")

                );
                kayttajat.add(kayttaja);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kayttajat;
    }
    public Kayttaja haeKayttaja(String kayttajaNimi, String salasana) {
        String sql = "SELECT * FROM kayttaja WHERE kayttaja_nimi = ? AND salasana = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, kayttajaNimi);
            stmt.setString(2, salasana);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Kayttaja(
                        rs.getInt("id"),
                        rs.getString("kayttaja_nimi"),
                        rs.getString("salasana"),
                        rs.getBoolean("is_admin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void poistaKayttaja(int id) {
        String query = "DELETE FROM kayttaja WHERE id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            System.out.println("Käyttäjä poistettu.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Käyttäjän poisto epäonnistui.");
        }
    }
    public void paivitaKayttaja(Kayttaja kayttaja) {
        String query = "UPDATE kayttaja SET kayttaja_nimi = ?, salasana = ?, is_admin = ? WHERE id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, kayttaja.getKayttaja_nimi());
            stmt.setString(2, kayttaja.getSalasana());
            stmt.setBoolean(3, kayttaja.isAdmin());
            stmt.setInt(4, kayttaja.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virhe tietokantaa päivittäessä.");
            e.printStackTrace();
        }
    }

    public void lisaaKayttaja(Kayttaja kayttaja) {
        String query = "INSERT INTO kayttaja (kayttaja_nimi, salasana, is_admin) VALUES (?, ?, ?)";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, kayttaja.getKayttaja_nimi());
            stmt.setString(2, kayttaja.getSalasana());
            stmt.setBoolean(3, kayttaja.isAdmin());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Käyttäjää ei voitu luoda. Tarkista, että käyttäjänimi ei ole jo käytössä.");
        }
    }
}
