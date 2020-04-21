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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


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
public class DataFragment extends Fragment {

 //   private GraphView mGtaphView;

  //  private LineGraphSeries<DataPoint> mTempSeries;
  //  private LineGraphSeries<DataPoint> mHumidSeries;


 //   Button refeshs;
 //   FirebaseDatabase database;
 //   DatabaseReference reference;
 //   LineGraphSeries series;
 //   TextView tempcurr;
 //   SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootview = inflater.inflate(R.layout.fragment_data,container,false);
        mGtaphView =  rootview.findViewById(R.id.graph);
        series = new LineGraphSeries();
        mGtaphView.addSeries(series);
        refeshs = rootview.findViewById(R.id.refesh);
        database = FirebaseDatabase.getInstance();
        tempcurr = rootview.findViewById(R.id.tempcurrent);
        reference = database.getReference("logDevice01");

       // setListener();

        mGtaphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value,boolean isValueX) {

                if(isValueX){
                    return sdf.format(new Date((long) value));
                }
                else {
                    return super.formatLabel(value,isValueX);
                }
            }
        });



        return rootview;
    }*/


  /*  private void setListener(){
        refeshs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = reference.push().getKey();
                long x =new Date().getTime();
                int y = Integer.parseInt(tempcurr.getText().toString());

                PointValue pointValue = new PointValue(x,y);

                reference.child(id).setValue(pointValue);
            }
        });
    }*/

 /*   @Override
    public void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataPoint[] dp= new DataPoint[(int)dataSnapshot.getChildrenCount()];
                int index = 0;

                for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren())
                {
                    PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
                    dp[index] = new DataPoint(pointValue.getX(),pointValue.getY());
                    index++;
                }
                series.resetData((DataPointInterface[]) dp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }
}
