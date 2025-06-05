package com.sample.api;

import com.google.gson.Gson;
import com.sample.dto.ProductDTO;
import com.sample.repository.ProductRepository;
import com.sample.util.Convert;
import com.sample.util.Param;
import com.sun.net.httpserver.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

public class ProductApi {

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

    static class AllProductsHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            String allProducts = ProductRepository.getAllProducts();
            sendResponse(exchange, allProducts, "application/json");
        }
    }

    static class InsertProductHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            // Đọc JSON từ request body
            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "utf-8"));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            // Parse JSON thành ProductDTO
            ProductDTO product = Convert.StringToObj(jsonBuilder.toString(), ProductDTO.class);

            // Gọi repository để thêm sản phẩm
            String result = ProductRepository.insertProduct(product);

            // Sử dụng Convert.ObjToString để trả response JSON
            String jsonResponse = Convert.ObjToString(result);

            sendResponse(exchange, jsonResponse, "application/json");
        }
    }

    static class UpdateProductHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                ProductApi.applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if (!"PUT".equalsIgnoreCase(exchange.getRequestMethod())
                    && !"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
                return;
            }

            ProductApi.applyCORS(exchange);

            // Đọc body từ request
            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "utf-8"));
            StringBuilder bodyBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                bodyBuilder.append(line);
            }

            // Chuyển JSON thành đối tượng ProductDTO
            Gson gson = new Gson();
            ProductDTO product = gson.fromJson(bodyBuilder.toString(), ProductDTO.class);

            // Gọi phương thức cập nhật
            String result = ProductRepository.updateProduct(product);

            // Trả kết quả
            byte[] response = result.getBytes("utf-8");
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    static class DeleteProductHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            // Xử lý CORS nếu là phương thức OPTIONS
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // Chỉ cho phép phương thức DELETE
            if (!"DELETE".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
                return;
            }

            applyCORS(exchange);

            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery(); // ví dụ: id=10

            int productId = -1;
            try {
                if (query != null && query.startsWith("id=")) {
                    productId = Integer.parseInt(query.substring(3));
                }
            } catch (NumberFormatException e) {
                sendResponse(exchange, "ID không hợp lệ", "application/json");
                return;
            }

            if (productId == -1) {
                sendResponse(exchange, "Thiếu ID sản phẩm", "application/json");
                return;
            }

            String result = ProductRepository.deleteProduct(productId);
            sendResponse(exchange, result, "application/json");
        }
    }
}
