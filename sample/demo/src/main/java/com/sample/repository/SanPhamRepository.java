package com.sample.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sample.dto.SanPhamDTO;

public class SanPhamRepository {

    public List<SanPhamDTO> getAllProducts() {
        List<SanPhamDTO> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM san_pham";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMaSanPham(rs.getString("ma_san_pham"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setDonViTinh(rs.getString("don_vi_tinh"));
                sp.setMaDanhMuc(rs.getString("ma_danh_muc"));

                sanPhamList.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }
}
