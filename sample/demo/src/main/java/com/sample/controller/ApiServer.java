package com.sample.controller;

import com.sample.controller.api.SanPhamApi;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/sanpham", new SanPhamApi.GetAllSanPhamHandler());

        server.setExecutor(null); // Dùng thread executor mặc định
        server.start();
        System.out.println("Server đang chạy tại: http://localhost:8080/sanpham");
    }
}
