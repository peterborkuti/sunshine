package com.example.android.sunshine;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Borkuti Peter on 12/19/2015.
 */
public class TSunshine {
    public long getLongDate() {
        return date;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    private Long date;
    private Double maxTemp;
    private Double minTemp;
    private String icon;
    private String description;
    private final JSONObject json;

    private TSunshine() throws JSONException {
        json = null;
    }


    public TSunshine(@NonNull final JSONObject jDay) throws JSONException {
        this.json = jDay;
        JSONObject jTemp = (JSONObject)jDay.get("temp");
        maxTemp = jTemp.getDouble("max");
        minTemp = jTemp.getDouble("min");
        JSONObject jWeather = jDay.getJSONArray("weather").getJSONObject(0);
        description = jWeather.getString("description");
        icon = jWeather.getString("icon");

        date = jDay.getLong("dt");
     }

    @Override
    public String toString() {
        return "TSunshine{" +
                "date=" + date +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
