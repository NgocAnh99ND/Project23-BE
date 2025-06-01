package com.sample.api;

import com.sample.api.ProductApi.ProductsFromId1To23Handler;
import com.sample.api.ProductApi.ProductsFromId24To37Handler;
import com.sample.api.ProductApi.RootHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new RootHandler());
        server.createContext("/products1to23", new ProductsFromId1To23Handler());
        server.createContext("/products24to37", new ProductsFromId24To37Handler());

        server.setExecutor(null);
        server.start();
        System.out.println("Server đang chạy tại http://localhost:8080/");
    }
}
