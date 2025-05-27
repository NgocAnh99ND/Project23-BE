package com.sample.endpoints;

import com.sample.querry.ProductQuery;
import com.sample.utils.Convert;
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
        server.createContext("/products1to23", new ProductsFromId1To23Handler());
        server.createContext("/products24to37", new ProductsFromId24To37Handler());

        server.setExecutor(null);
        server.start();
        System.out.println("Server đang chạy tại http://localhost:8080/");
    }

    // Tiện ích: thêm header CORS vào response
    static void applyCORS(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
    }

    // Hàm gửi phản hồi với Content-Type chỉ định
    static void sendResponse(HttpExchange exchange, String response, String contentType) throws IOException {
        applyCORS(exchange);

        byte[] bytes = response.getBytes("UTF-8");

        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", contentType + "; charset=UTF-8"); // đảm bảo override đúng
        String abc = Convert.ObjToString(headers);
        System.out.println(abc);
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    static class RootHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            sendResponse(exchange, "Trang chủ - Server Java", "text/plain");
        }
    }

    static class HelloHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            sendResponse(exchange, "Xin chào!", "text/plain");
        }
    }

    static class TimeHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            LocalDateTime now = LocalDateTime.now();
            sendResponse(exchange, "Thời gian hiện tại: " + now.toString(), "text/plain");
        }
    }

    static class EchoHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

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

            sendResponse(exchange, response, "text/plain");
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

    static class ProductsFromId1To23Handler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String productKetqua = ProductQuery.getProductsFromId1To23();
            System.out.println("Nội dung JSON trả về:\n" + productKetqua);
            sendResponse(exchange, productKetqua, "application/json");
        }
    }

    static class ProductsFromId24To37Handler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String productKetqua = ProductQuery.getProductsFromId24To37();
            System.out.println("Nội dung JSON trả về:\n" + productKetqua);
            sendResponse(exchange, productKetqua, "application/json");
        }
    }

}
