/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sijamal.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author louis
 */
public class Peminjaman {
    private String nimPeminjam;
    private LocalDate tanggalPinjam;
    private LocalTime waktuPinjam;
    private String namaKategori;
    private String namaAlat;
    private int jumlah;
    private String status;
    
    public Peminjaman(){
        
    }

    public Peminjaman(String nimPeminjam, String namaKategori, String namaAlat, LocalDate tanggalPinjam, LocalTime waktuPinjam, int jumlah) {
        this.nimPeminjam = nimPeminjam;
        this.namaKategori = namaKategori;
        this.namaAlat = namaAlat;
        this.tanggalPinjam = tanggalPinjam;
        this.waktuPinjam = waktuPinjam;
        this.jumlah = jumlah;
    }

    public String getNimPeminjam() {
        return nimPeminjam;
    }

    public void setNimPeminjam(String nimPeminjam) {
        this.nimPeminjam = nimPeminjam;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getNamaAlat() {
        return namaAlat;
    }

    public void setNamaAlat(String namaAlat) {
        this.namaAlat = namaAlat;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public LocalTime getWaktuPinjam() {
        return waktuPinjam;
    }

    public void setWaktuPinjam(LocalTime waktuPinjam) {
        this.waktuPinjam = waktuPinjam;
    }
    
    
    public int getJumlah(){
        return jumlah;
    }
    
    public void setJumlah(int jumlah){
        this.jumlah = jumlah;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
