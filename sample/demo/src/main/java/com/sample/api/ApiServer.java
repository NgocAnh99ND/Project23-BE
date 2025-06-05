package com.sample.api;

import com.sample.api.ProductApi.AllProductsHandler;
import com.sample.api.ProductApi.DeleteProductHandler;
import com.sample.api.ProductApi.InsertProductHandler;
import com.sample.api.ProductApi.ProductsByRangeHandler;
import com.sample.api.ProductApi.RootHandler;
import com.sample.api.ProductApi.UpdateProductHandler;
import com.sample.api.UserApi.AllUsersHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new RootHandler());
        server.createContext("/products", new ProductsByRangeHandler());
        server.createContext("/all_products", new AllProductsHandler());
        server.createContext("/add_product", new InsertProductHandler());
        server.createContext("/delete_product", new DeleteProductHandler());
        server.createContext("/update_product", new UpdateProductHandler());
        server.createContext("/user", new AllUsersHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("Server đang chạy tại http://localhost:8080/");
    }
}
