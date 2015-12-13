package com.example.android.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
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

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
}
