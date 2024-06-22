/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sijamal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import sijamal.database.*;

/**
 *
 * @author louis
 */
public class PeminjamanModel {

    public PeminjamanModel() {

    }
    
    public boolean cekKetersediaanAlat(String idAlat, int jumlah) {
        String sql = "SELECT jumlah_alat FROM alat WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idAlat);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int jumlahTersedia = rs.getInt("jumlah_alat");
                    return jumlahTersedia >= jumlah;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean ajukanPeminjaman(Peminjaman peminjaman, String username){
        String idKategori = getIdKategoriByName(peminjaman.getNamaKategori());
        if (idKategori == null) {
            System.out.println("Kategori tidak ditemukan");
            return false;
        }
        
        String idAlat= getIdAlatByName(peminjaman.getNamaAlat());
        if (idAlat == null) {
            System.out.println("Alat tidak ditemukan");
            return false;
        }
        
        String sql = "INSERT INTO peminjaman (peminjam, id_kategori, id_alat, tanggal, waktu, jumlah, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, idKategori);
            pstmt.setString(3, idAlat);
            String sqlDate = peminjaman.getTanggalPinjam().toString(); // Convert LocalDate to String
            pstmt.setString(4, sqlDate);
            String sqlTime = peminjaman.getWaktuPinjam().toString(); // Convert LocalTime to String
            pstmt.setString(5, sqlTime);
            pstmt.setInt(6, peminjaman.getJumlah());
            pstmt.setString(7, "Diajukan");
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String getIdKategoriByName(String namaKategori) {
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
    
    public String getIdAlatByName(String namaAlat) {
        String sql = "SELECT id FROM alat WHERE nama_alat = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, namaAlat);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }  
    
    public int getJumlahAlatTersedia(String idAlat) {
    String sql = "SELECT jumlah_tersedia FROM alat WHERE id_alat = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, idAlat);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("jumlah_tersedia");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Return -1 jika terjadi kesalahan atau alat tidak ditemukan
}
    public List<Peminjaman> getAllPeminjaman() {
        List<Peminjaman> peminjamans = new ArrayList<>();
        String sql = "SELECT * FROM peminjaman";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Peminjaman p = new Peminjaman();
                p.setNimPeminjam(rs.getString("peminjam"));
                p.setNamaKategori(getNamaKategoriById(rs.getString("id_kategori")));
                p.setNamaAlat(getNamaAlatById(rs.getString("id_alat")));
                String tanggalString = rs.getString("tanggal");
                LocalDate tanggalPinjam = LocalDate.parse(tanggalString, DateTimeFormatter.ISO_LOCAL_DATE);
                p.setTanggalPinjam(tanggalPinjam);
                String waktuString = rs.getString("waktu");
                LocalTime waktuPinjam = LocalTime.parse(waktuString, DateTimeFormatter.ofPattern("HH:mm"));
                p.setWaktuPinjam(waktuPinjam);
                p.setJumlah(rs.getInt("jumlah"));
                p.setStatus(rs.getString("status"));
                peminjamans.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peminjamans;
    }

    public String getNamaKategoriById(String idKategori) {
        String sql = "SELECT nama_kategori FROM kategori WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idKategori);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nama_kategori");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getNamaAlatById(String idAlat) {
        String sql = "SELECT nama_alat FROM alat WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idAlat);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nama_alat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }  
    
    public List<Peminjaman> getAllPeminjamanByUsername(String username) {
        List<Peminjaman> peminjamans = new ArrayList<>();
        String sql = "SELECT * FROM peminjaman WHERE peminjam = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Peminjaman p = new Peminjaman();
                p.setNamaKategori(getNamaKategoriById(rs.getString("id_kategori")));
                p.setNamaAlat(getNamaAlatById(rs.getString("id_alat")));
                String tanggalString = rs.getString("tanggal");
                LocalDate tanggalPinjam = LocalDate.parse(tanggalString, DateTimeFormatter.ISO_LOCAL_DATE);
                p.setTanggalPinjam(tanggalPinjam);
                String waktuString = rs.getString("waktu");
                LocalTime waktuPinjam = LocalTime.parse(waktuString, DateTimeFormatter.ofPattern("HH:mm"));
                p.setWaktuPinjam(waktuPinjam);
                p.setJumlah(rs.getInt("jumlah"));
                p.setStatus(rs.getString("status"));
                peminjamans.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peminjamans;
    }

    public void updatePeminjamanStatus(Peminjaman peminjaman) {
        String idAlat = getIdAlatByName(peminjaman.getNamaAlat());
        if (idAlat == null) {
            System.out.println("Alat tidak ditemukan");
            return;
        }

        String sql = "UPDATE peminjaman SET status = ? WHERE id_alat = ?";
        try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, peminjaman.getStatus());
            pstmt.setString(2, idAlat);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
