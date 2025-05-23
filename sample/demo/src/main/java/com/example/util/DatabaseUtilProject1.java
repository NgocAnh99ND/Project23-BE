package com.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtilProject1 {
    private static final String URL = "jdbc:mysql://localhost:3307/project1";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getAllCategory() {
        List<DanhMuc> categoryList = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM danh_muc;")) {

            while (rs.next()) {
                String b = rs.getString("ma_danh_muc");
                String c = rs.getString("ten_danh_muc");

                DanhMuc category = new DanhMuc(b, c);

                categoryList.add(category);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String categorys = gson.toJson(categoryList);
            return categorys;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

     public static String getProductByCategory() {
        List<ProductByCategory> productByCategoryList = new ArrayList<>();

        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "SELECT ma_san_pham, ten_san_pham FROM san_pham WHERE ma_danh_muc = ?")) {

            pstmt.setString(1, "DM02");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String maSanPham = rs.getString("ma_san_pham");
                    String tenSanPham = rs.getString("ma_san_pham");

                    ProductByCategory productByCategory = new ProductByCategory(maSanPham, tenSanPham);
                    productByCategoryList.add(productByCategory);
                }
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String productByCategorys = gson.toJson(productByCategoryList);
            return productByCategorys;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

     public static void main(String[] args) {
        String categorys = getProductByCategory();
        System.out.println(categorys);
    }

}
