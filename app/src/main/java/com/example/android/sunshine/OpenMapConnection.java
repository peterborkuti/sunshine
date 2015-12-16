package com.example.android.sunshine;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Borkuti Peter on 12/14/2015.
 */
public class OpenMapConnection {
    public static final String TAG = "BP";

    public void start(Activity activity, String url) {
        ConnectivityManager connMgr = (ConnectivityManager)
                activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d(TAG,"start GetOpenMap");
            new GetOpenMapData().execute(ConnectionUtil.getUrl(url));
        } else {
            //textView.setText("No network connection available.");
            Log.e(TAG, "No network connection available.");
        }

    }

    private class GetOpenMapData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            Log.d("BP", "url: " + urls[0]);

            // params comes from the execute() call: params[0] is the url.
            try {
                return ConnectionUtil.getResponse(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Log.d("BP", "response:" + result);
            //textView.setText(result);
        }
    }
}



