package com.example.android.sunshine;

import android.net.Uri;
import android.util.Log;


/**
 * Created by Borkuti Peter on 12/17/2015.
 */
public class OpenWeatherAPIURL {
    public static final String AUTHORITY = "api.openweathermap.org";
    public static final String PATH = "/data/2.5/forecast/daily";
    public static final String API_KEY = "ae15f9ec3f753942f40372a33f314476";

    public static Uri getURL(String postalCode, String country) {
        Uri.Builder uriBuilder = new Uri.Builder();
        //order does matter
        uriBuilder.scheme("http");
        // If the path is not null and doesn't start with a '/', and if you specify a scheme
        // and/or authority, the builder will prepend the given path with a '/'.
        uriBuilder.authority(AUTHORITY);
        uriBuilder.path(PATH);
        uriBuilder.appendQueryParameter("zip", postalCode + "," + country);
        uriBuilder.appendQueryParameter("mode", "json");
        uriBuilder.appendQueryParameter("units", "metric");
        uriBuilder.appendQueryParameter("cnt", "7");
        uriBuilder.appendQueryParameter("APPID", API_KEY);

        Uri uri = uriBuilder.build();

        Log.d("BP", "uri:" + uri.toString());

        return uri;
    }
}
