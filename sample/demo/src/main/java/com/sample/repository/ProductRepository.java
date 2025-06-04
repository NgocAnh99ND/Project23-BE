package com.sample.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.dto.DanhMucDTO;
import com.sample.dto.ProductByCategoryDTO;
import com.sample.dto.ProductByPriceDetailDTO;
import com.sample.dto.ProductDTO;
import com.sample.util.Convert;
import com.sample.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public static String getAllProducts() {
        List<ProductDTO> productList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
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

                ProductDTO product = new ProductDTO(product_id, product_name, image, price, oldPrice, color,
                        description, ram,
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

        try (Connection conn = DatabaseConnection.getConnection();
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

        try (Connection conn = DatabaseConnection.getConnection();
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

        try (Connection conn = DatabaseConnection.getConnection();
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

                    ProductByPriceDetailDTO productByPriceAsc = new ProductByPriceDetailDTO(product_id, product_name,
                            image, price,
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

        try (Connection conn = DatabaseConnection.getConnection();
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

                    ProductByPriceDetailDTO productByPriceDesc = new ProductByPriceDetailDTO(product_id, product_name,
                            image,
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

    public static String getProductsByRange(String limit, String offset) {
        List<ProductDTO> productList = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM product " +
                "ORDER BY product_id ASC " +
                "LIMIT " + limit + " " +
                "OFFSET " + offset;

        try (Connection conn = DatabaseConnection.getConnection();
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

                ProductDTO product = new ProductDTO(product_id, product_name, image, price, oldPrice, color,
                        description, ram,
                        ssd, gift, rating);

                productList.add(product);
            }

            return Convert.ObjToString(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "khong co ket qua";
    }

    public static String insertProduct(ProductDTO product) {
        String sql = "INSERT INTO product (product_id, product_name, image, price, old_price, color, description, ram, ssd, gift, rating) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, product.getProductId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getImage());
            pstmt.setInt(4, product.getPrice());
            pstmt.setInt(5, product.getOldPrice());
            pstmt.setString(6, product.getColor());
            pstmt.setString(7, product.getDescription());
            pstmt.setString(8, product.getRam());
            pstmt.setString(9, product.getSsd());
            pstmt.setString(10, product.getGift());
            pstmt.setDouble(11, product.getRating());

            int rows = pstmt.executeUpdate();
            return rows > 0 ? "Thêm sản phẩm thành công" : "Thêm thất bại";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Lỗi SQL: " + e.getMessage();
        }
    }

    public static String updateProduct(ProductDTO product) {
        String sql = "UPDATE product SET product_name = ?, image = ?, price = ?, old_price = ?, color = ?, "
                + "description = ?, ram = ?, ssd = ?, gift = ?, rating = ? WHERE product_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getImage());
            pstmt.setInt(3, product.getPrice());
            pstmt.setInt(4, product.getOldPrice());
            pstmt.setString(5, product.getColor());
            pstmt.setString(6, product.getDescription());
            pstmt.setString(7, product.getRam());
            pstmt.setString(8, product.getSsd());
            pstmt.setString(9, product.getGift());
            pstmt.setDouble(10, product.getRating());
            pstmt.setInt(11, product.getProductId());

            int rows = pstmt.executeUpdate();
            return rows > 0 ? "Cập nhật sản phẩm thành công" : "Không tìm thấy sản phẩm để cập nhật";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Lỗi SQL: " + e.getMessage();
        }
    }

    public static String deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            int rows = pstmt.executeUpdate();
            return rows > 0 ? "Xóa sản phẩm thành công" : "Không tìm thấy sản phẩm để xóa";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Lỗi SQL: " + e.getMessage();
        }
    }

}
