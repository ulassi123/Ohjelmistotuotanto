package com.example.ohjelmistotuotanto.data;

import com.example.ohjelmistotuotanto.entities.Mokki;
import com.example.ohjelmistotuotanto.utils.DbConnect;
import com.example.ohjelmistotuotanto.utils.Utilities;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MokkiData {
    private final DbConnect dbConnect = new DbConnect();

    public List<Mokki> haeMokit() {
        List<Mokki> mokit = new ArrayList<>();
        String sql = "SELECT * FROM mokki";

        try (Connection conn = dbConnect.yhdista();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mokki mokki = new Mokki(
                        rs.getInt("id"),
                        rs.getString("osoite"),
                        rs.getBoolean("tila"),
                        rs.getInt("huoneet"),
                        rs.getInt("koko"),
                        rs.getFloat("hinta_per_yo"),
                        rs.getTimestamp("luotu").toLocalDateTime(),
                        rs.getTimestamp("paivitetty").toLocalDateTime()
                );
                mokit.add(mokki);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mokit;
    }

    public void lisaaMokki(Mokki mokki) {
        String sql = "INSERT INTO mokki (osoite, tila, huoneet, koko, hinta_per_yo, luotu, paivitetty) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mokki.getOsoite());
            pstmt.setBoolean(2, mokki.isTila());
            pstmt.setInt(3, mokki.getHuoneet());
            pstmt.setInt(4, mokki.getKoko());
            pstmt.setFloat(5, mokki.getHintaPerYo());
            pstmt.setTimestamp(6, Timestamp.valueOf(mokki.getLuotu()));
            pstmt.setTimestamp(7, Timestamp.valueOf(mokki.getPaivitetty()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void poistaMokki(int mokkiId) {
        String sql = "DELETE FROM mokki WHERE id = ?";
        try (Connection conn = dbConnect.yhdista();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mokkiId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Mökkiä ei voitu poistaa. Poista olemassaolevat mökin varaukset ennen poistamista.");
            e.printStackTrace();
        }
    }

    public void paivitaMokki(Mokki mokki) {
        String sql = "UPDATE mokki SET osoite = ?, tila = ?, huoneet = ?, koko = ?, hinta_per_yo = ?, " +
                "paivitetty = ? WHERE id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mokki.getOsoite());
            pstmt.setBoolean(2, mokki.isTila());
            pstmt.setInt(3, mokki.getHuoneet());
            pstmt.setInt(4, mokki.getKoko());
            pstmt.setFloat(5, mokki.getHintaPerYo());
            pstmt.setTimestamp(6, Timestamp.valueOf(mokki.getPaivitetty()));
            pstmt.setInt(7, mokki.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virhe tietokantaa päivittäessä.");
            e.printStackTrace();
        }
    }

    public Float getHintaPerYo(int mokkiId) {
        String sql = "SELECT hinta_per_yo FROM mokki WHERE id = ?";
        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mokkiId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getFloat("hinta_per_yo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    public String getOsoiteById(int mokkiId) {
        String sql = "SELECT osoite FROM mokki WHERE id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mokkiId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("osoite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setTilaTrueById(int mokkiId) {
        String sql = "UPDATE mokki SET tila = TRUE WHERE id = ?";
        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mokkiId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTilaFalseById(int mokkiId) {
        String sql = "UPDATE mokki SET tila = FALSE WHERE id = ?";
        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mokkiId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}