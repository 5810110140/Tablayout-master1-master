package com.codingdemos.tablayout;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class ControlFragmentV2 extends Fragment {

    Button WaterOn,WaterOff;
    Button CoolingOn,CoolingOff;
    Button HeatingOn,HeatingOff;
    TextView StateWater,Statecooling,Stateheating;
    private DatabaseReference refWater,refCooling,refHeating;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View rootview = inflater.inflate(R.layout.fragment_controlv2,container,false);

        WaterOff = rootview.findViewById(R.id.Btn_WaterOFF);
        WaterOn = rootview.findViewById(R.id.Btn_waterON);
        refWater = FirebaseDatabase.getInstance().getReference("waterpump");
        StateWater = rootview.findViewById(R.id.state_waterpump);

        CoolingOff = rootview.findViewById(R.id.btn_CoolingOFF);
        CoolingOn = rootview.findViewById(R.id.btn_CoolingON);
        refCooling = FirebaseDatabase.getInstance().getReference("coolingfan");
        Statecooling = rootview.findViewById(R.id.state_cooling);

        HeatingOff = rootview.findViewById(R.id.btn_HeatingOFF);
        HeatingOn = rootview.findViewById(R.id.btn_HeatingON);
        refHeating = FirebaseDatabase.getInstance().getReference("heating");
        Stateheating = rootview.findViewById(R.id.state_heating);

        //save switch state
      /*  SharedPreferences sharedPreferenceswater_off = getContext().getSharedPreferences("savewater off",MODE_PRIVATE);
        SharedPreferences sharedPreferenceswater_on = getContext().getSharedPreferences("savewater_on",MODE_PRIVATE);
        SharedPreferences sharedPreferencescooling_off = getContext().getSharedPreferences("savecooling_off",MODE_PRIVATE);
        SharedPreferences sharedPreferencescooling_on = getContext().getSharedPreferences("savecooling_on",MODE_PRIVATE);
        SharedPreferences sharedPreferencesheatinh_off = getContext().getSharedPreferences("saveheating_off",MODE_PRIVATE);
        SharedPreferences sharedPreferencesheatinh_on = getContext().getSharedPreferences("saveheating_on",MODE_PRIVATE);*/


        WaterOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refWater.child("waterstatus");
                DatabaseReference text =refWater.child("statustext");
                state.setValue("0");
                text.setValue("ปิด");

            }
        });

        WaterOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refWater.child("waterstatus");
                DatabaseReference text = refWater.child("statustext");
                state.setValue("1");
                text.setValue("เปิด");

            }
        });

        CoolingOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refCooling.child("coolingstatus");
                DatabaseReference text = refCooling.child("statustext");
                state.setValue("0");
                text.setValue("ปิด");
            }
        });

        CoolingOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refCooling.child("coolingstatus");
                DatabaseReference text = refCooling.child("statustext");
                state.setValue("1");
                text.setValue("เปิด");
            }
        });

        HeatingOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refHeating.child("heatingstatus");
                DatabaseReference text = refHeating.child("statustext");
                state.setValue("0");
                text.setValue("ปิด");
            }
        });

        HeatingOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refHeating.child("heatingstatus");
                DatabaseReference text = refHeating.child("statustext");
                state.setValue("1");
                text.setValue("เปิด");
            }
        });



        refWater.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Map map = (Map)dataSnapshot.getValue();
                String statetext = String.valueOf(map.get("statustext"));
                StateWater.setText(statetext);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        refCooling.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String statetext = String.valueOf(map.get("statustext"));
                Statecooling.setText(statetext);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refHeating.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String statetext = String.valueOf(map.get("statustext"));
                Stateheating.setText(statetext);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return rootview;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return  true;
    }




}