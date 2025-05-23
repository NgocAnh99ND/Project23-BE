package com.example.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Convert {
    // Convert Object to Json
    public static String ObjToString(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonObj  = gson.toJson(obj);
        return jsonObj;
    }
}
