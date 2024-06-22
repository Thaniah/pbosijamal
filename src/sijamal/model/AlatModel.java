/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sijamal.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sijamal.database.*;

/**
 *
 * @author louis
 */

public class AlatModel {
    
    public AlatModel(){
        
    }

    public boolean addAlat(Alat alat) {
        String idKategori = getIdKategoriByName(alat.getNamaKategori());
        if (idKategori == null) {
            System.out.println("Kategori tidak ditemukan");
            return false;
        }

        String sql = "INSERT INTO alat (id, id_kategori, nama_alat, jumlah_alat) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alat.getId());
            pstmt.setString(2, idKategori);
            pstmt.setString(3, alat.getNamaAlat());
            pstmt.setInt(4, alat.getJumlah());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.out.println("ID alat sudah ada di database!");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    private String getIdKategoriByName(String namaKategori) {
        String sql = "SELECT id FROM kategori WHERE nama_kategori = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, namaKategori);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Alat> getAllAlat() {
        List<Alat> alatList = new ArrayList<>();
        String sql = "SELECT kategori.nama_kategori, alat.id, alat.nama_alat, alat.jumlah_alat FROM alat JOIN kategori ON alat.id_kategori = kategori.id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Alat alat = new Alat();
                alat.setNamaKategori(rs.getString("nama_kategori"));
                alat.setId(rs.getString("id"));
                alat.setNamaAlat(rs.getString("nama_alat"));
                alat.setJumlah(rs.getInt("jumlah_alat"));
                alatList.add(alat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alatList;
    }

}