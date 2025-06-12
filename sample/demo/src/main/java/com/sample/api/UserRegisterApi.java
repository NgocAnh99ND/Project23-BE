package com.sample.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.sample.dto.UserRegisterDTO;
import com.sample.repository.UserRegisterRepository;
import com.sample.util.Convert;
import com.sun.net.httpserver.*;

public class UserRegisterApi {
    // Tiện ích: thêm header CORS vào response
    static void applyCORS(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
    }

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

    static class UserRegisterHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            // Xử lý CORS cho preflight request
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // Chỉ cho phép POST
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendResponse(exchange, "{\"message\":\"Method Not Allowed\"}", "application/json");
                return;
            }

            // Đọc JSON body
            StringBuilder body = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    body.append(line);
                }
            }

            // Parse JSON thành DTO
            UserRegisterDTO new_user = Convert.StringToObj(body.toString(), UserRegisterDTO.class);

            // Kiểm tra hợp lệ
            if (new_user.getUsername() == null || new_user.getPassword() == null ||
                    new_user.getUsername().trim().isEmpty() || new_user.getPassword().trim().isEmpty()) {
                sendResponse(exchange, "{\"message\":\"Thiếu thông tin đăng ký\"}", "application/json");
                return;
            }

            // Kiểm tra trùng username
            if (UserRegisterRepository.usernameExists(new_user.getUsername())) {
                sendResponse(exchange, "{\"message\":\"Tên đăng nhập đã tồn tại\"}", "application/json");
                return;
            }

            // Lưu vào DB
            boolean success = UserRegisterRepository.registerUser(new_user);
            if (success) {
                sendResponse(exchange, "{\"message\":\"Đăng ký thành công\"}", "application/json");
            } else {
                sendResponse(exchange, "{\"message\":\"Lỗi khi lưu người dùng\"}", "application/json");
            }

        }

    }
}
