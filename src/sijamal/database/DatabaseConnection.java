/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sijamal.database;

/**
 *
 * @author Louista Thania Harahap
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.Serializable;
import java.sql.*;
import sijamal.model.Alat;

public class DatabaseConnection implements Serializable{
    private static final String URL = "jdbc:sqlite:Database/sijamal.db";
    public static DatabaseConnection instance;
    
    public DatabaseConnection() throws SQLException {
        
    }
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL);
    }
    
    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if(instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
//    public boolean authenticate(String username, String password) throws SQLException {
//        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
//        try (Connection conn = getConnection();
//            PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, username);
//            pstmt.setString(2, password);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                return rs.next();
//            }
//        }
//    }
//    
//    public boolean authenticateUser(String nim, String password) throws SQLException {
//        String sql = "SELECT * FROM user WHERE nim = ? AND password = ?";
//        try (Connection conn = getConnection();
//            PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, nim);
//            pstmt.setString(2, password);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                return rs.next();
//            }
//        }
//    }

}
