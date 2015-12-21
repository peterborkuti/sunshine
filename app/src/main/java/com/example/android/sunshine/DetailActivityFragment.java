package com.example.android.sunshine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);

        final View inflate = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView textView = (TextView)inflate.findViewById(R.id.detail_text);
        textView.setText(text);

        return inflate;
    }
}
