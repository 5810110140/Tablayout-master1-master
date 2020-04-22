package com.codingdemos.tablayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

import lecho.lib.hellocharts.model.PointValue;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment{

private WebView webView;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootview = inflater.inflate(R.layout.fragment_data,container,false);


       webView = (WebView) rootview.findViewById(R.id.webview);
       webView.setWebViewClient(new WebViewClient());
       webView.loadUrl("https://fireboard.xyz/show--M-6fHtc6317bdxQMMy5.html?fbclid=IwAR0ddOkSP8bySy5LRwbHvrZYJQoTH5xsHVw8IZWMLqdRwlgJmBjmDw1lkYI");

       WebSettings webSettings = webView.getSettings();
       webSettings.setJavaScriptEnabled(true);


        return rootview;
    }








    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }
}
