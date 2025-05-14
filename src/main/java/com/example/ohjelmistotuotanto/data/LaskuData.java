package com.example.ohjelmistotuotanto.data;

import com.example.ohjelmistotuotanto.entities.Asiakas;
import com.example.ohjelmistotuotanto.entities.Lasku;
import com.example.ohjelmistotuotanto.entities.Varaus;
import com.example.ohjelmistotuotanto.utils.DbConnect;
import com.example.ohjelmistotuotanto.utils.Utilities;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LaskuData {
    DbConnect dbConnect = new DbConnect();

    public List<Lasku> haeLaskut() {
        List<Lasku> laskut = new ArrayList<>();
        String sql = "SELECT * FROM lasku";
        try (Connection conn = dbConnect.yhdista();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Lasku lasku = new Lasku(
                        rs.getInt("id"),
                        rs.getFloat("hinta"),
                        rs.getString("laskutustapa"),
                        rs.getDate("erapaiva").toLocalDate(),
                        rs.getBoolean("tila")
                );
                laskut.add(lasku);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return laskut;
    }

    public void lisaaLasku(Lasku lasku) {
        String sql = "INSERT INTO lasku (hinta, laskutustapa, erapaiva, tila) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, lasku.getHinta());
            stmt.setString(2, lasku.getLaskutustapa());
            stmt.setDate(3, Date.valueOf(lasku.getErapaiva()));
            stmt.setBoolean(4, lasku.isTila());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    lasku.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Laskua ei voitu luoda.");
            e.printStackTrace();
        }
    }

    public float laskeHinta(float hintaPerYo, LocalDate alkuPvm, LocalDate loppuPvm) {
        long daysBetween = ChronoUnit.DAYS.between(alkuPvm, loppuPvm);
        return hintaPerYo * daysBetween;
    }

    public void poistaLasku(int laskuId) {
        String sql = "DELETE FROM lasku WHERE id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, laskuId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void paivitaLasku(Lasku lasku) {
        String sql = "UPDATE lasku SET hinta = ?, laskutustapa = ?, erapaiva = ?, tila = ? WHERE id = ?";

        try (PreparedStatement stmt = dbConnect.yhdista().prepareStatement(sql)) {

            stmt.setFloat(1, lasku.getHinta());
            stmt.setString(2, lasku.getLaskutustapa());
            stmt.setDate(3, Date.valueOf(lasku.getErapaiva()));
            stmt.setBoolean(4, lasku.isTila());
            stmt.setInt(5, lasku.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Utilities.showAlertDialog(Alert.AlertType.ERROR, "Virhe tietokantaa päivittäessä.");
        }
    }

    public Asiakas haeAsiakasByLaskuId(int laskuId) {
        String sql = "SELECT a.* FROM lasku l " +
                "JOIN varaus v ON l.id = v.lasku_id " +
                "JOIN asiakas a ON v.asiakas_id = a.id " +
                "WHERE l.id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, laskuId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int asiakasId = rs.getInt("id");
                    String nimi = rs.getString("nimi");
                    String sahkoposti = rs.getString("sahkoposti");
                    String puhelinnumero = rs.getString("puhelinnumero");
                    String maa = rs.getString("maa");
                    boolean yritys = rs.getBoolean("yritys");

                    return new Asiakas(asiakasId, sahkoposti, nimi, puhelinnumero, maa, yritys);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Varaus haeVarausByLaskuId(int laskuId) {
        String sql = "SELECT v.id, v.asiakas_id, v.mokki_id, v.lasku_id, v.aloituspaiva, v.lopetuspaiva " +
                "FROM varaus v " +
                "JOIN lasku l ON v.lasku_id = l.id " +
                "WHERE l.id = ?";

        try (Connection conn = dbConnect.yhdista();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, laskuId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int asiakas_id = rs.getInt("asiakas_id");
                    int mokki_id = rs.getInt("mokki_id");
                    LocalDate aloituspaiva = rs.getDate("aloituspaiva").toLocalDate();
                    LocalDate lopetuspaiva = rs.getDate("lopetuspaiva").toLocalDate();

                    return new Varaus(id, asiakas_id, mokki_id, laskuId, aloituspaiva, lopetuspaiva);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void poistaLaskuJaVapautaMokki(int laskuId) {
        try (Connection conn = dbConnect.yhdista()) {

            String varausSql = "SELECT mokki_id FROM varaus WHERE lasku_id = ?";
            int mokkiId = -1;

            try (PreparedStatement stmt = conn.prepareStatement(varausSql)) {
                stmt.setInt(1, laskuId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    mokkiId = rs.getInt("mokki_id");
                } else {
                    Utilities.showAlertDialog(Alert.AlertType.INFORMATION, "Laskulle ei ollut varausta.");
                    return;
                }
            }

            String mokkiSql = "UPDATE mokki SET tila = FALSE WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(mokkiSql)) {
                stmt.setInt(1, mokkiId);
                stmt.executeUpdate();
            }

            String deleteLaskuSql = "DELETE FROM lasku WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteLaskuSql)) {
                stmt.setInt(1, laskuId);
                stmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
