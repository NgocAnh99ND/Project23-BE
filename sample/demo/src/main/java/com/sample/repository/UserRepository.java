package com.sample.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.dto.UserDTO;
import com.sample.util.Convert;
import java.sql.Statement;
import com.sample.util.DatabaseConnection;

public class UserRepository {
    public static String getAllUsers() {
        List<UserDTO> userList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users;")) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String passWord = rs.getString("pass_word");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                UserDTO user = new UserDTO(userId, userName, passWord, phone, address);
                userList.add(user);
            }

            return Convert.ObjToString(userList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

}
