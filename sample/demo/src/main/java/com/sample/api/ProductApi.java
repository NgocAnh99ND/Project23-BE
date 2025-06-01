package com.sample.api;

import com.sample.repository.ProductRepository;
import com.sample.util.Convert;
import com.sample.util.Param;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ProductApi {

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

    static class ProductsByRangeHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // Parse query string thành danh sách key-value
            List<Param> params = Convert.parseQueryParams(query);
            String limit = null;
            String offset = null;

            for (Param p : params) {
                if ("limit".equalsIgnoreCase(p.key)) {
                    limit = p.value;
                } else if ("offset".equalsIgnoreCase(p.key)) {
                    offset = p.value;
                }
            }

            String productKetqua = ProductRepository.getProductsByRange(limit, offset);
            sendResponse(exchange, productKetqua, "application/json");
        }
    }

}
