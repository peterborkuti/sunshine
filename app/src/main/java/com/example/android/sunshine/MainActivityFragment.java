package com.example.android.sunshine;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String TAG = "BP";
    LayoutInflater inflater;

    public MainActivityFragment() {
        setHasOptionsMenu (true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflater = inflater;
        String[] list = {
                "Yesterday - Sunny 9/11 Sárika", "Today - Sunny 3/7  Sárika","Wed - Windy 5/11  Sárika",
                "Thu - mmm 9/6", "Thu - mmm 9/6", "Thu - mmm 9/6", " Sárika",  "Sárika"
        };

        ArrayList<String> weekForecast = new ArrayList<String>(Arrays.asList(list));

        ArrayAdapter arrAdapter =
                new ArrayAdapter(getActivity(),R.layout.list_item_forecast,
                        R.id.list_item_forecast_textview, weekForecast);

        //Need root view for finding ListView
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.listview_forecast);

        listView.setAdapter(arrAdapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "inlfating forecastfragment menu");
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh)
        {
            Log.d(TAG, "Refresh menu selected");
            OpenMapConnection omConn = new OpenMapConnection();
            Log.d("BP", "start connection");
            Uri url = OpenWeatherAPIURL.getURL("1158", "hu");
            //"http://api.openweathermap.org/data/2.5/forecast/city?id=524901"
            omConn.start(getActivity(), url);

            return true;
        }

        Log.d(TAG, "Other menu selected");
        return super.onOptionsItemSelected(item);
    }
}
