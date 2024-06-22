/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sijamal.controller;

import java.io.FileWriter;
import java.io.IOException;
import sijamal.view.*;
import java.sql.*;
import java.util.List;
import sijamal.model.*;


/**
 *
 * @author louis
 */
public class Controller {
    private MainFrame mainframe;
    private final LoginPanel loginPanel;
    private final UserModel userModel;
    private final AdminHomePanel admHomePanel;
    private final UserHomePanel userHomePanel;
    private final DaftarPanel daftarPanel;
    private final TambahAlatPanel tambahPanel;
    private final ViewAlatPanel alatPanel;
    private final ViewPeminjamanPanel pinjamPanel;
    private final AjukanPanel ajukanPanel;
    private final RiwayatPanel riwayatPanel;
    private final TinjauPeminjamanPanel tinjauPanel;
    private boolean isAdmin;
    private User currentUser;
    private final AlatModel alatModel;
    private final PeminjamanModel pinjamModel;
   
    public Controller(){
        loginPanel = new LoginPanel(this);
        mainframe = new MainFrame(this);
        userModel = new UserModel();
        alatModel = new AlatModel(); 
        pinjamModel = new PeminjamanModel();
        admHomePanel = new AdminHomePanel(this);
        userHomePanel = new UserHomePanel(this);
        daftarPanel = new DaftarPanel(this);
        tambahPanel = new TambahAlatPanel(this);
        alatPanel = new ViewAlatPanel(this);
        pinjamPanel = new ViewPeminjamanPanel(this);
        ajukanPanel = new AjukanPanel(this);
        riwayatPanel = new RiwayatPanel(this);
        tinjauPanel = new TinjauPeminjamanPanel(this);
        isAdmin = false;
        currentUser = null;
    }
    
    public void mulai(MainFrame mainframe) {
        this.mainframe = mainframe;
        mainframe.setContentPanel(new LoginPanel(this));
    }
    
    public void showLoginPanel() {
        mainframe.setContentPanel(loginPanel);
    }
    
    public void showDaftarPanel() {
        mainframe.setContentPanel(daftarPanel);
    }
    
    public void showAdminHomePanel() {
        mainframe.setContentPanel(admHomePanel);
    }
    
    public void showUserHomePanel() {
        mainframe.setContentPanel(userHomePanel);
    }
    
    public void logout() {
        isAdmin = false;
        currentUser = null;
        showLoginPanel();
    }
    
    public void showTambahAlatPanel(){
        mainframe.setContentPanel(tambahPanel);  
    }
    
    public void showViewAlatPanel(){
        alatPanel.loadAlatData();
        mainframe.setContentPanel(alatPanel);
    }
    
    public void showPeminjamanPanel(){
        pinjamPanel.displayPeminjaman();
        mainframe.setContentPanel(pinjamPanel);  
    }
    
    public void showAjukanPanel(){
        mainframe.setContentPanel(ajukanPanel);  
    }
    
    public void showRiwayatPanel(){
        riwayatPanel.populateTable();
        mainframe.setContentPanel(riwayatPanel);  
    }
    
    public void showTinjauPeminjamanPanel(Peminjaman peminjaman) {
        tinjauPanel.setData(peminjaman);
        mainframe.setContentPanel(tinjauPanel);
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    public boolean validateAdmin(String username, String password) throws SQLException{
        try{
            boolean isValid = userModel.validateAdmin(username, password);
            if (isValid) {
                isAdmin = true;
            }
            return isValid;
        } catch(SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }    
    
    public boolean validateUser(String nim, String password) throws SQLException{
        try{
            boolean isValid = userModel.validateUser(nim, password);
            if (isValid) {
                isAdmin = false;
                currentUser = userModel.getUserByNim(nim);
            }
            return isValid;
        } catch(SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public User getUserByNim(String nim) throws SQLException {
        return userModel.getUserByNim(nim);
    }
    
    public void registerUser(String nama, String nim, String nohp, String password) throws SQLException {
        User user = new User();
        user.setNama(nama);
        user.setNim(nim);
        user.setNohp(nohp);
        user.setPassword(password);
        userModel.registerUser(user);
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }
    
    public boolean addAlat(Alat alat) {
        return alatModel.addAlat(alat);
    }

    public boolean ajukanPeminjaman(Peminjaman peminjaman, String nim) {
        return pinjamModel.ajukanPeminjaman(peminjaman, currentUser.getNim());
    }
    
    public int getJumlahAlatTersedia(String idAlat) {
        return pinjamModel.getJumlahAlatTersedia(idAlat);
    }

    public String getIdAlatByName(String namaAlat) {
        return pinjamModel.getIdAlatByName(namaAlat);
    }
    
    public List<Alat> getAllAlat() {
        return alatModel.getAllAlat();
    }
    
    public List<Peminjaman> getAllPeminjaman() {
        return pinjamModel.getAllPeminjaman();
    }

    public List<Peminjaman> getAllPeminjamanByUsername(String username) {
        return pinjamModel.getAllPeminjamanByUsername(username);
    }
    
    public void peminjamanToCSV(String filePath) {
        List<Peminjaman> peminjamans = getAllPeminjaman();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Peminjam,Kategori,Nama Alat,Tanggal Peminjaman,Waktu,Jumlah,Status\n");
            for (Peminjaman p : peminjamans) {
                writer.append(p.getNimPeminjam()).append(",");
                writer.append(p.getNamaKategori()).append(",");
                writer.append(p.getNamaAlat()).append(",");
                writer.append(p.getTanggalPinjam().toString()).append(",");
                writer.append(p.getWaktuPinjam().toString()).append(",");
                writer.append(String.valueOf(p.getJumlah())).append(",");
                writer.append(p.getStatus()).append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void alatToCSV(String filePath) {
        List<Alat> alats = getAllAlat();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Kategori, ID, Nama Alat, Jumlah Alat\n");
            for (Alat a : alats) {
                writer.append(a.getNamaKategori()).append(",");
                writer.append(a.getId()).append(",");
                writer.append(a.getNamaAlat()).append(",");
                writer.append(String.valueOf(a.getJumlah())).append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void updatePeminjamanStatus(Peminjaman peminjaman) {
        pinjamModel.updatePeminjamanStatus(peminjaman);
    }
}
