package com.sample.controller.api;

import com.sample.controller.HttpUtils;
import com.sample.dto.SanPhamDTO;
import com.sample.repository.SanPhamRepository;
import com.sample.utils.Convert;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class SanPhamApi {

    public static class GetAllSanPhamHandler implements HttpHandler {
        private final SanPhamRepository repository = new SanPhamRepository();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                HttpUtils.applyCORS(exchange);
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // Hàm này chỉ nhận request với method: GET
            // Nếu request không phải phương thức GET thì trả về lỗi
            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
                return;
            }

            List<SanPhamDTO> products = repository.getAll();
            String products_json = Convert.ObjToString(products);

            HttpUtils.sendResponse(exchange, products_json, "application/json");
        }
    }
}
