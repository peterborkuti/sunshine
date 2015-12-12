package com.example.android.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
                "Yesterday - Sunny 9/11", "Today - Sunny 3/7","Wed - Windy 5/11",
                "Thu - mmm 9/6", "Thu - mmm 9/6", "Thu - mmm 9/6"
        };

        ArrayAdapter
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
