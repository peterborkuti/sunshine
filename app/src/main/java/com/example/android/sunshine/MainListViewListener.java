package com.example.android.sunshine;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Borkuti Peter on 12/20/2015.
 */
public class MainListViewListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("BP", "selected item:" + position + "," + id);
    }
}
