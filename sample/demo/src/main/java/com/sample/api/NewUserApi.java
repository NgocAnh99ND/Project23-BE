package com.sample.api;

import java.io.IOException;
import java.io.OutputStream;

import com.sample.repository.NewUserRepository;
import com.sun.net.httpserver.*;

public class NewUserApi {
    // Tiện ích: thêm header CORS vào response
    static void applyCORS(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
    }

    // Hàm gửi phản hồi với Content-Type chỉ định
    static void sendResponse(HttpExchange exchange, String response, String contentType) throws IOException {
        applyCORS(exchange);

        byte[] bytes = response.getBytes("UTF-8");

        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", contentType + "; charset=UTF-8"); // đảm bảo override đúng
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    static class NewUsersHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            // Xử lý CORS cho preflight request
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // Gọi đến repository để lấy dữ liệu
            String newUsers = NewUserRepository.getNewUsers();

            // Gửi response JSON
            sendResponse(exchange, newUsers, "application/json");
        }
    }
}
