/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sijamal.view;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import sijamal.controller.*;
import sijamal.database.DatabaseConnection;
import sijamal.model.*;

/**
 *
 * @author louis
 */
public class AjukanPanel extends javax.swing.JPanel {
    private final Controller controller;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    /**
     * Creates new form AjukanPanel
     */
    public AjukanPanel(Controller controller) {
        this.controller = controller;
        initComponents();
        initKategoriComboBoxListener();
        loadKategoriComboBox();
    }
    
    
    
    private void initKategoriComboBoxListener() {
        kategoriComboBox.addActionListener((ActionEvent e) -> {
            updateAlatComboBox();
        });
    }

    private void updateAlatComboBox() {
        String selectedKategori = (String) kategoriComboBox.getSelectedItem();
        if (selectedKategori != null) {
            try {
                String idKategori = getIdKategoriByNama(selectedKategori);
                List<String> alatList = getAlatListByIdKategori(idKategori);
                alatComboBox.setModel(new DefaultComboBoxModel<>(alatList.toArray(new String[0])));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error retrieving data from database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private String getIdKategoriByNama(String namaKategori) throws SQLException {
        String idKategori = null;
        String query = "SELECT id FROM kategori WHERE nama_kategori = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, namaKategori);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idKategori = rs.getString("id");
                }
            }
        }
        
        return idKategori;
    }
    
    private List<String> getAlatListByIdKategori(String idKategori) throws SQLException {
        List<String> alatList = new ArrayList<>();
        String query = "SELECT nama_alat FROM alat WHERE id_kategori = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idKategori);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    alatList.add(rs.getString("nama_alat"));
                }
            }
        }
        
        return alatList;
    }
    
    private void loadKategoriComboBox() {
        try {
            List<String> kategoriList = getKategoriList();
            kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriList.toArray(new String[0])));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error retrieving data from database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private List<String> getKategoriList() throws SQLException {
        List<String> kategoriList = new ArrayList<>();
        String query = "SELECT nama_kategori FROM kategori";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                kategoriList.add(rs.getString("nama_kategori"));
            }
        }
        
        return kategoriList;
    }
    
    private boolean isValidDate(String dateStr) {
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidTime(String timeStr) {
        try {
            timeFormat.setLenient(false);
            timeFormat.parse(timeStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        judulLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        waktuLabel = new javax.swing.JLabel();
        tanggalLabel = new javax.swing.JLabel();
        alatLabel = new javax.swing.JLabel();
        kategoriLabel = new javax.swing.JLabel();
        tanggalTextField = new javax.swing.JTextField();
        waktuTextField = new javax.swing.JTextField();
        kategoriComboBox = new javax.swing.JComboBox<>();
        alatComboBox = new javax.swing.JComboBox<>();
        jumlahLabel = new javax.swing.JLabel();
        jumlahSpinner = new javax.swing.JSpinner();
        ajukanButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(27, 114, 191));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(681, 474));
        setMinimumSize(new java.awt.Dimension(681, 474));

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));

        judulLabel.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        judulLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        judulLabel.setText("Ajukan Peminjaman");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judulLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judulLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(238, 238, 238));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        waktuLabel.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        waktuLabel.setLabelFor(waktuTextField);
        waktuLabel.setText("Waktu Peminjaman");

        tanggalLabel.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        tanggalLabel.setLabelFor(tanggalTextField);
        tanggalLabel.setText("Tanggal Peminjaman");

        alatLabel.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        alatLabel.setText("Alat");

        kategoriLabel.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        kategoriLabel.setLabelFor(kategoriComboBox);
        kategoriLabel.setText("Kategori");

        tanggalTextField.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        tanggalTextField.setText("yyyy-MM-dd");
        tanggalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalTextFieldActionPerformed(evt);
            }
        });

        waktuTextField.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        waktuTextField.setText("HH:mm");
        waktuTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waktuTextFieldActionPerformed(evt);
            }
        });

        kategoriComboBox.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        kategoriComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        alatComboBox.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N

        jumlahLabel.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        jumlahLabel.setLabelFor(jumlahSpinner);
        jumlahLabel.setText("Jumlah");

        jumlahSpinner.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N

        ajukanButton.setBackground(new java.awt.Color(253, 207, 51));
        ajukanButton.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        ajukanButton.setText("Ajukan");
        ajukanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajukanButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ajukanButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(kategoriLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(alatLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tanggalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(waktuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jumlahLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tanggalTextField)
                            .addComponent(waktuTextField)
                            .addComponent(kategoriComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(alatComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jumlahSpinner))))
                .addGap(80, 80, 80))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tanggalLabel)
                            .addComponent(tanggalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(waktuLabel)
                            .addComponent(waktuTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(kategoriLabel))
                    .addComponent(kategoriComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alatLabel)
                    .addComponent(alatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumlahLabel)
                    .addComponent(jumlahSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(ajukanButton)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ajukanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajukanButtonActionPerformed
        // TODO add your handling code here:
        String tanggalStr = tanggalTextField.getText();
        String waktuStr = waktuTextField.getText();
        
        if (!isValidDate(tanggalStr) || !isValidTime(waktuStr)) {
            JOptionPane.showMessageDialog(this, "Format tanggal atau waktu tidak valid.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LocalDate tanggalPinjam = LocalDate.parse(tanggalStr);
        LocalTime waktuPinjam = LocalTime.parse(waktuStr);
        int jumlah = (int) jumlahSpinner.getValue();
        String namaKategori = (String) kategoriComboBox.getSelectedItem();
        String namaAlat = (String) alatComboBox.getSelectedItem();

        User currentUser = controller.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Pengguna tidak terautentikasi.", "Authentication Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String idAlat = controller.getIdAlatByName(namaAlat);
        int jumlahTersedia = controller.getJumlahAlatTersedia(idAlat);
    
        if (jumlahTersedia < jumlah) {
            JOptionPane.showMessageDialog(this, "Jumlah alat tidak mencukupi.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Peminjaman peminjaman = new Peminjaman(
            currentUser.getNim(), 
            namaKategori, 
            namaAlat, 
            tanggalPinjam, 
            waktuPinjam, 
            jumlah
        );

        boolean success = controller.ajukanPeminjaman(peminjaman, currentUser.getNim());
        if (success) {
            JOptionPane.showMessageDialog(this, "Peminjaman berhasil diajukan.", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Pengajuan peminjaman gagal.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ajukanButtonActionPerformed

    private void clearForm() {
        tanggalTextField.setText("");
        waktuTextField.setText("");
        jumlahSpinner.setValue(0);
        kategoriComboBox.setSelectedIndex(0);
        alatComboBox.setSelectedIndex(0);
    }
    private void tanggalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalTextFieldActionPerformed

    private void waktuTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waktuTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_waktuTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajukanButton;
    private javax.swing.JComboBox<String> alatComboBox;
    private javax.swing.JLabel alatLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel judulLabel;
    private javax.swing.JLabel jumlahLabel;
    private javax.swing.JSpinner jumlahSpinner;
    private javax.swing.JComboBox<String> kategoriComboBox;
    private javax.swing.JLabel kategoriLabel;
    private javax.swing.JLabel tanggalLabel;
    private javax.swing.JTextField tanggalTextField;
    private javax.swing.JLabel waktuLabel;
    private javax.swing.JTextField waktuTextField;
    // End of variables declaration//GEN-END:variables
}
