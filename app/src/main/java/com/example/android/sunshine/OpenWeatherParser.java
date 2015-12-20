package com.example.android.sunshine;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by Borkuti Peter on 12/18/2015.
 */
public class OpenWeatherParser {
    JSONObject parsed;

    public OpenWeatherParser(String json) throws JSONException {
        if (json == null || "".equals(json)) {
            throw new IllegalArgumentException();
        }

        parse(json);
    }


    public JSONObject parse(String json) throws JSONException {
        parsed = new JSONObject(json);

        return parsed;
    }

    /**
     * Gets the value for the key hierarchy
     *
     * @param keyHierarchy
     * @return the String value of the key or null if the key hierarchy does not exists in the
     * parsed json
     */
    @Deprecated
    public JSONObject get(String... keyHierarchy) throws JSONException {
        JSONObject obj = parsed;

        for (int i = 0; i < keyHierarchy.length; i++) {
            Log.d("BP", "parsing - get - " + i);
            obj = (JSONObject)obj.get(keyHierarchy[i]);
            Log.d("BP", "done");
        }

        return obj;
    }

    public static TSunshine getWeatherForDay(JSONObject jDay) throws JSONException {
        JSONObject jTemp = (JSONObject)jDay.get("temp");
        Double max = jTemp.getDouble("max");
        Double min = jTemp.getDouble("min");
        JSONObject jWeather = jDay.getJSONArray("weather").getJSONObject(0);
        String description = jWeather.getString("description");
        String icon = jWeather.getString("icon");

        long dt = jDay.getLong("dt");

        return new TSunshine(dt, max, min, icon, description);
    }

    public static TSunshine[] getWeatherForecastFromArray(JSONArray jsonArray) throws JSONException {
        List<TSunshine> days = new ArrayList<TSunshine>();
        for (int i = 0; i < jsonArray.length(); i++) {
            days.add(getWeatherForDay(jsonArray.getJSONObject(i)));
        }

        return days.toArray(new TSunshine[0]);
    }

    public static TSunshine[] getWeatherForecast(String weatherJsonStr)
            throws JSONException {
        JSONObject jo = new JSONObject(weatherJsonStr);
        JSONArray ja = jo.getJSONArray("list");
        return getWeatherForecastFromArray(ja);
    }


}
