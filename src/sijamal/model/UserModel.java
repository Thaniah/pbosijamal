/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sijamal.model;
/**
 *
 * @author Louista Thania Harahap
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sijamal.database.*;

public class UserModel {
 
    public boolean validateAdmin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    public boolean validateUser(String nim, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE nim = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nim);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    public User getUserByNim(String nim) throws SQLException {
        String sql = "SELECT * FROM user WHERE nim = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nim);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setNim(rs.getString("nim"));
                    user.setNama(rs.getString("nama"));
                    user.setNohp(rs.getString("nohp"));
                    user.setPassword(rs.getString("password"));
                    return user;
                } else {
                return null;
                }
            }
        }
    }

    
    public void registerUser(User user) {
        String sql = "INSERT INTO user (nim, nama, nohp, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getNim());
            pstmt.setString(2, user.getNama());
            pstmt.setString(3, user.getNohp());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
