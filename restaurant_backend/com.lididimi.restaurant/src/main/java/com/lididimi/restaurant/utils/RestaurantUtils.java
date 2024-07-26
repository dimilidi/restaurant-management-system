package com.lididimi.restaurant.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RestaurantUtils {

    private RestaurantUtils() {}

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>(responseMessage, httpStatus);
    }

    public static String getUUID() {
        Date date = new Date();
        long time = date.getTime();

        return "BILL-" + time;
    }

    public static JSONArray getJSONArrayFromString(String data) throws JSONException {
        JSONArray jsonArray = new JSONArray(data);
        return jsonArray;
    }

    public static Map<String, Object> getMapFromJson(String data)  {
        if(data != null) {
            return new Gson().fromJson(data, new TypeToken<Map<String, Object>>(){
            }.getType());
        } else {
            return new HashMap<>();
        }
    }

    public static Boolean isFileExist(String path) {
        log.info("Inside isFileExist {}", path);
        try {
            File file = new File(path);
            return file != null && file.exists() ? Boolean.TRUE : Boolean.FALSE;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
