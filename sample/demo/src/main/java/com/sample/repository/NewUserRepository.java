package com.sample.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sample.dto.NewUserDTO;
import com.sample.util.Convert;
import com.sample.util.DatabaseConnection;

public class NewUserRepository {
    public static String getNewUsers() {
        List<NewUserDTO> userList = new ArrayList<>();
        String sql = "SELECT * FROM new_user";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("username");
                String passWord = rs.getString("password");
                Timestamp createdAt = rs.getTimestamp("created_at");

                NewUserDTO newUser = new NewUserDTO(id, userName, passWord, createdAt);
                userList.add(newUser);
            }

            return Convert.ObjToString(userList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

    // public static void main(String[] args) {
    //     getNewUsers();
    //     System.out.println( getNewUsers());
    // }
}
