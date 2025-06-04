package com.sample.api;

import com.sample.api.ProductApi.AllProductsHandler;
import com.sample.api.ProductApi.ProductsByRangeHandler;
import com.sample.api.ProductApi.RootHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new RootHandler());
        server.createContext("/products", new ProductsByRangeHandler());
        server.createContext("/all_products", new AllProductsHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("Server đang chạy tại http://localhost:8080/");
    }
}
