package com.example;

import com.example.util.TruyVanMau;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.*;

public class MultiEndpointHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new RootHandler());
        server.createContext("/hello", new HelloHandler());
        server.createContext("/time", new TimeHandler());
        server.createContext("/echo", new EchoHandler());
        server.createContext("/products", new ProductsHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("Server đang chạy tại http://localhost:8080/");
    }

    static void sendResponse(HttpExchange exchange, String response) throws IOException {
        byte[] bytes = response.getBytes("UTF-8");
        exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(200, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    static class RootHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            sendResponse(exchange, "Trang chủ - Server Java");
        }
    }

    static class HelloHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            sendResponse(exchange, "Xin chào!");
        }
    }

    static class TimeHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            LocalDateTime now = LocalDateTime.now();
            sendResponse(exchange, "Thời gian hiện tại: " + now.toString());
        }
    }

    static class EchoHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery(); // number=5
            Map<String, String> params = queryToMap(query);

            String response;
            if (params.containsKey("number")) {
                try {
                    int number = Integer.parseInt(params.get("number"));
                    int result = number + 1;
                    response = "Kết quả: " + number + " + 1 = " + result;
                } catch (NumberFormatException e) {
                    response = "Lỗi: Giá trị không phải số nguyên.";
                }
            } else {
                response = "Vui lòng cung cấp tham số 'number'. Ví dụ: /echo?number=5";
            }

            sendResponse(exchange, response);
        }

        private Map<String, String> queryToMap(String query) {
            Map<String, String> result = new HashMap<>();
            if (query == null)
                return result;
            for (String param : query.split("&")) {
                String[] parts = param.split("=");
                if (parts.length == 2) {
                    result.put(parts[0], parts[1]);
                }
            }
            return result;
        }
    }

    static class ProductsHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String productKetqua = TruyVanMau.getAllProductsJson();
            sendResponse(exchange, productKetqua);
        }
    }

}
