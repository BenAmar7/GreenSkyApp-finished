package com.example.user.myapplication.data;

import org.json.JSONObject;

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
    temperature = data.optString("temperature");
    }


}
