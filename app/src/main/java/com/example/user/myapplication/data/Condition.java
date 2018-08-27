package com.example.user.myapplication.data;

import org.json.JSONObject;

public class Condition implements JSONPopulator {
    private int code;
    private int tempertaure;
    private  String description;

    public int getCode() {
        return code;
    }

    public int getTempertaure() {
        return tempertaure;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        tempertaure = data.optInt("temp");
        description = data.optString("text");



    }
}
