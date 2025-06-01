package com.sample.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Convert {
    // Convert Object to Json
    public static String ObjToString(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonObj = gson.toJson(obj);
        return jsonObj;
    }

    // Chuyển đổi chuỗi querry thành các đối tượng chứa thông tin
    public static List<Param> parseQueryParams(String query) {
        List<Param> paramList = new ArrayList<>();

        if (query != null && !query.isEmpty()) {
            // Chuỗi query có dạng: "limit=10&offset=5"
            // Bước 1: Ta dùng split để chia chuỗi theo ký tự "&"
            String[] pairs = query.split("&");
            // Sau bước này sẽ tạo thành mảng: pair = [limit=10, offset=5]

            for (String pair : pairs) {
                // Dùng split chia tiếp mảng: pair = [limit=10, offset=5] chia theo ký tự "="
                String[] parts = pair.split("=", 2);
                // Ta được kết quả là: 
                // Cặp 1: parts[0] = limit, parts[1] = 10
                // Cặp 2: parts[0] = offset, parts[1] = 5
                
                String key = parts[0];
                String value = parts[1];
                paramList.add(new Param(key, value));
            }
        }

        return paramList;
    }

}
