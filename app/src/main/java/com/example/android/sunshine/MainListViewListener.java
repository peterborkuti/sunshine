package com.example.android.sunshine;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Borkuti Peter on 12/20/2015.
 */
public class MainListViewListener implements AdapterView.OnItemClickListener {
    final List<TSunshine> days;
    private MainListViewListener () {
        this.days = null;
    }

    public MainListViewListener(final List<TSunshine> days) {
        this.days = days;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Context context = parent.getContext();
        CharSequence text = null;
        try {
            text = days.get(position).getJson();
        } catch (JSONException e) {
            text = e.getMessage();
        }
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Log.d("BP", "selected item:" + position + "," + id);
    }
}
