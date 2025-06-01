package com.sample.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.model.dto.DanhMucDTO;
import com.sample.model.dto.ProductDTO;
import com.sample.model.dto.ProductByCategoryDTO;
import com.sample.model.dto.ProductByPriceDetailDTO;
import com.sample.utils.Convert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String URL = "jdbc:mysql://localhost:3307/project23";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getAllProducts() {
        List<ProductDTO> productList = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM product;")) {

            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String image = rs.getString("image");
                int price = rs.getInt("price");
                int oldPrice = rs.getInt("old_price");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String ram = rs.getString("ram");
                String ssd = rs.getString("ssd");
                String gift = rs.getString("gift");
                double rating = rs.getDouble("rating");

                ProductDTO product = new ProductDTO(product_id, product_name, image, price, oldPrice, color, description, ram,
                        ssd, gift, rating);

                productList.add(product);
            }

            return Convert.ObjToString(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

    public static String getAllCategory() {
        List<DanhMucDTO> categoryList = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM danh_muc;")) {

            while (rs.next()) {
                String b = rs.getString("ma_danh_muc");
                String c = rs.getString("ten_danh_muc");

                DanhMucDTO category = new DanhMucDTO(b, c);

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

    public String getProductByCategory() {
        List<ProductByCategoryDTO> productByCategoryList = new ArrayList<>();

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT ma_san_pham, ten_san_pham FROM san_pham WHERE ma_danh_muc = ?")) {

            pstmt.setString(1, "DM02");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String maSanPham = rs.getString("ma_san_pham");
                    String tenSanPham = rs.getString("ma_san_pham");

                    ProductByCategoryDTO productByCategory = new ProductByCategoryDTO(maSanPham, tenSanPham);
                    productByCategoryList.add(productByCategory);
                }
            }

            return Convert.ObjToString(productByCategoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

    public String getProductsSortedByPriceAsc() {
        List<ProductByPriceDetailDTO> getProductsSortedByPriceAscList = new ArrayList<>();

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * " +
                                "FROM product " +
                                "ORDER BY price ASC")) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int oldPrice = rs.getInt("old_price");
                    String color = rs.getString("color");
                    String description = rs.getString("description");
                    String ram = rs.getString("ram");
                    String ssd = rs.getString("ssd");
                    String gift = rs.getString("gift");
                    double rating = rs.getDouble("rating");

                    ProductByPriceDetailDTO productByPriceAsc = new ProductByPriceDetailDTO(product_id, product_name, image, price,
                            oldPrice, color, description, ram, ssd, gift, rating);
                    getProductsSortedByPriceAscList.add(productByPriceAsc);
                }
            }

            return Convert.ObjToString(getProductsSortedByPriceAscList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

    public String getProductsSortedByPriceDesc() {
        List<ProductByPriceDetailDTO> getProductsSortedByPriceDescList = new ArrayList<>();

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * " +
                                "FROM product " +
                                "ORDER BY price DESC")) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int oldPrice = rs.getInt("old_price");
                    String color = rs.getString("color");
                    String description = rs.getString("description");
                    String ram = rs.getString("ram");
                    String ssd = rs.getString("ssd");
                    String gift = rs.getString("gift");
                    double rating = rs.getDouble("rating");

                    ProductByPriceDetailDTO productByPriceDesc = new ProductByPriceDetailDTO(product_id, product_name, image,
                            price, oldPrice, color, description, ram, ssd, gift, rating);
                    getProductsSortedByPriceDescList.add(productByPriceDesc);
                }
            }

            return Convert.ObjToString(getProductsSortedByPriceDescList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

    public static String getProductsFromId1To23() {
        List<ProductDTO> productList = new ArrayList<>();

        String sql = "SELECT * FROM product ORDER BY product_id ASC LIMIT 23 OFFSET 0";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String image = rs.getString("image");
                int price = rs.getInt("price");
                int oldPrice = rs.getInt("old_price");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String ram = rs.getString("ram");
                String ssd = rs.getString("ssd");
                String gift = rs.getString("gift");
                double rating = rs.getDouble("rating");

                ProductDTO product = new ProductDTO(product_id, product_name, image, price, oldPrice, color, description, ram,
                        ssd, gift, rating);

                productList.add(product);
            }

            return Convert.ObjToString(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

    public static String getProductsFromId24To37() {
        List<ProductDTO> productList = new ArrayList<>();

         String sql = "\"SELECT * FROM product WHERE product_id BETWEEN 24 AND 37 ORDER BY product_id ASC";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String image = rs.getString("image");
                int price = rs.getInt("price");
                int oldPrice = rs.getInt("old_price");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String ram = rs.getString("ram");
                String ssd = rs.getString("ssd");
                String gift = rs.getString("gift");
                double rating = rs.getDouble("rating");

                ProductDTO product = new ProductDTO(product_id, product_name, image, price, oldPrice, color, description, ram,
                        ssd, gift, rating);

                productList.add(product);
            }

            return Convert.ObjToString(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }
}
