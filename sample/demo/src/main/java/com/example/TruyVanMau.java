package com.example;

import com.example.utils.Convert;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TruyVanMau {
    private static final String URL = "jdbc:mysql://localhost:3307/project23";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getAllProducts() {
        List<Product> productList = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM product;")) {

            while (rs.next()) {
                int a = rs.getInt("product_id");
                String b = rs.getString("product_name");
                String c = rs.getString("image");
                int d = rs.getInt("price");
                int oldPrice = rs.getInt("old_price");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String ram = rs.getString("ram");
                String ssd = rs.getString("ssd");
                String gift = rs.getString("gift");
                double rating = rs.getDouble("rating");

                Product product = new Product(a, b, c, d, oldPrice, color, description, ram, ssd, gift, rating);

                productList.add(product);
            }

            return Convert.ObjToString(productList) ;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

    // public static void main(String[] args) {
    //     String products = getAllProducts();
    //     System.out.println(products);
    // }

}
