package com.sample.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sample.dto.SanPhamDTO;

public class SanPhamRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/project1";
    private static final String USER = "root";
    private static final String PASSWORD = "24tuan02tuan1995";

    public List<SanPhamDTO> getAll() {
        List<SanPhamDTO> sanPhamList = new ArrayList<>();

        String sql = "SELECT ma_san_pham, ten_san_pham, don_vi_tinh, ma_danh_muc FROM san_pham";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
