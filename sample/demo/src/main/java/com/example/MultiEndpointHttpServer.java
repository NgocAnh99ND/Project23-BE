package com.example;

import com.example.util.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MultiEndpointHttpServer {
    private static final String URL = "jdbc:mysql://localhost:3307/project23";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM product;")) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getInt("old_price"),
                        rs.getString("color"),
                        rs.getString("description"),
                        rs.getString("ram"),
                        rs.getString("ssd"),
                        rs.getString("gift"),
                        rs.getDouble("rating"));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(productList);

        System.out.println(json);
    }
}
