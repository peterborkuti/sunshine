package com.example.android.sunshine;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String TAG = "BP";
    LayoutInflater inflater;
    ArrayAdapter<String> arrAdapter;
    List<TSunshine> daysData = new ArrayList<TSunshine>();

    public MainActivityFragment() {
        setHasOptionsMenu (true);
    }

    private String getZip() {
        String zip = PreferenceManager
                .getDefaultSharedPreferences(getContext())
                .getString(getString(R.string.pref_location_key),
                        getString(R.string.pref_location_default));
        return zip;
    }

    private void refresh() {
        Log.d(TAG, "Refresh menu selected");

        OpenMapConnection omConn = new OpenMapConnection();
        Log.d("BP", "start connection");
        Uri url = OpenWeatherAPIURL.getURL(getZip(), "hu");
        //"http://api.openweathermap.org/data/2.5/forecast/city?id=524901"
        omConn.start(getActivity(), arrAdapter, daysData, url);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflater = inflater;

        final List<String> weekForecast = new ArrayList<String>();

        arrAdapter =
                new ArrayAdapter(getActivity(),R.layout.list_item_forecast,
                        R.id.list_item_forecast_textview, weekForecast);

        //Need root view for finding ListView
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.listview_forecast);

        listView.setAdapter(arrAdapter);
        listView.setOnItemClickListener(new MainListViewListener(daysData));

        refresh();

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
            refresh();
            return true;
        }

        if (id == R.id.action_viewlocation)
        {
            viewLocation();
            return true;
        }

        Log.d(TAG, "Other menu selected");
        return super.onOptionsItemSelected(item);
    }

    private void viewLocation() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + getZip() +", Hungary");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        }
        else {
            Toast toast = Toast.makeText(getContext(), "Google Maps can not be started.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
