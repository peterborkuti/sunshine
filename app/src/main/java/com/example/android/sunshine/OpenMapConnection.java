package com.example.android.sunshine;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Borkuti Peter on 12/14/2015.
 */
public class OpenMapConnection {
    public static final String TAG = "BP";
    private ArrayAdapter<String> arrAdapter;

    public void start(Activity activity, ArrayAdapter<String> arrayAdapter, List<TSunshine> daysData, Uri url) {
        arrAdapter = arrayAdapter;
        ConnectivityManager connMgr = (ConnectivityManager)
                activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d(TAG,"start GetOpenMap");
            new GetOpenMapData(daysData).execute(url);
        } else {
            //textView.setText("No network connection available.");
            Log.e(TAG, "No network connection available.");
        }

    }

    private class GetOpenMapData extends AsyncTask<Uri, Void, List<String>> {
        List<TSunshine> daysDataTemp = new ArrayList<TSunshine>();
        final List<TSunshine> daysData;

        private GetOpenMapData() {
            daysData = null;
        }

        public GetOpenMapData(final List<TSunshine> daysData) {
            this.daysData = daysData;
        }

        @Override
        protected List<String> doInBackground(Uri... uris) {
            Log.d("BP", "url: " + uris[0]);
            List<String> strArr = new ArrayList<String>();

            // params comes from the execute() call: params[0] is the url.
            try {
                String response = ConnectionUtil.getResponse(uris[0]);


                TSunshine[] dataArr = OpenWeatherParser.getWeatherForecast(response);
                daysDataTemp.clear();
                daysDataTemp.addAll(Arrays.asList(dataArr));

                for (TSunshine data: dataArr) {
                    strArr.add(data.toString());
                }

                return strArr;
            } catch (IOException e) {
                return Arrays.asList("Unable to retrieve web page. URL may be invalid.");
            } catch (JSONException e) {
                return Arrays.asList("Unable to parse json.");
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(List<String> result) {
            arrAdapter.clear();
            arrAdapter.addAll(result);
            daysData.clear();
            daysData.addAll(daysDataTemp);
            Log.d("BP", "Adapter was set with " + result);
            //textView.setText(result);
        }
    }
}



