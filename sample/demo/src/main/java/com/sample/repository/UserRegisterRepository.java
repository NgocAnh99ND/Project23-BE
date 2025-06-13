package com.sample.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sample.dto.UserRegisterDTO;
import com.sample.util.DatabaseConnection;

public class UserRegisterRepository {

    // Thêm người dùng mới
    public static boolean registerUser(UserRegisterDTO userRegister) {
        String sql = "INSERT INTO new_user (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userRegister.getUsername());
            stmt.setString(2, userRegister.getPassword());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra tên đăng nhập đã tồn tại chưa
    public static boolean usernameExists(String username) {
        String sql = "SELECT id FROM new_user WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // có bản ghi là tồn tại
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    
    public static void main(String[] args) {
        // Tạo user mẫu
        UserRegisterDTO user = new UserRegisterDTO("user2", "test2");

        // Kiểm tra tồn tại
        if (usernameExists(user.getUsername())) {
            System.out.println("Tên đăng nhập đã tồn tại!");
        } else {
            boolean success = registerUser(user);
            if (success) {
                System.out.println("Đăng ký thành công!");
            } else {
                System.out.println("Đăng ký thất bại!");
            }
        }
    }
}
