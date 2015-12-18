package com.example.android.sunshine;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Borkuti Peter on 12/16/2015.
 */
public class ConnectionUtil {
    public final static String TAG = "BP";
    public final static int READ_TIMEOUT = 10000; // ms
    public final static int CONN_TIMEOUT = 15000; // ms
    public final static int RESPONSE_BUFFER_LEN = 100; // ms


    // Reads an InputStream and converts it to a String.
    private static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        StringBuilder output = new StringBuilder();
        Reader reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        int i = 0;
        int got = reader.read(buffer, 0, len);
        while (got != -1) {
            output.append(buffer, 0, got);
            Log.d(TAG, "got:" + new String(buffer, 0, got));
            i += got;
            got = reader.read(buffer, 0, len);
        };

        return output.toString();
    }

    public static String getResponse(Uri uri) throws IOException {
        String contentAsString = "";

        InputStream is = null;

        try {
            Log.d("BP", "getResponse:" + uri.toString());
            URL url = new URL(uri.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONN_TIMEOUT);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(TAG, "The response code is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            contentAsString = readIt(is, RESPONSE_BUFFER_LEN);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return contentAsString;
    }
}
