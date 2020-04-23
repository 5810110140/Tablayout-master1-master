package com.codingdemos.tablayout;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;



import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {

    private TextView temperaturevalue,Tleveltemp;
    private TextView humidityvalue,Tlevelhumid;
    private TextView lightvalue,Tlevellight;
    private DatabaseReference tempRef;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_status, container, false);


        humidityvalue = rootView.findViewById(R.id.datahumidity);
        Tlevelhumid = rootView.findViewById(R.id.textlevelhumiditiy);

         temperaturevalue= rootView.findViewById(R.id.datatemperature);
         Tleveltemp = rootView.findViewById(R.id.textleveltemp);

         lightvalue = rootView.findViewById(R.id.datalight);
         Tlevellight = rootView.findViewById(R.id.textlevellight);

        //get value from firebase อ่านค่าจากไฟล์ Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        tempRef = database.getReference();


        tempRef.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //รับค่าจาก Fire base
                Map map = (Map)dataSnapshot.getValue();
                Map map1 = (Map)dataSnapshot.child("RealtimeDHT").getValue();
               String temp = String.valueOf(map1.get("Temperature"));
               String leveltemp = String.valueOf(map1.get("statusTemperature"));

               String humid = String.valueOf(map1.get("Humidity"));
               String levelhumid = String.valueOf(map1.get("statusHumidity"));

               String light = String.valueOf(map1.get("LuxRating"));
               String levellight = String.valueOf(map1.get("statusLuxRating"));




               // update UI
               temperaturevalue.setText(temp +"°C");
               Tleveltemp.setText("ระดับ :"+leveltemp);

               humidityvalue.setText(humid +" %RH");
               Tlevelhumid.setText("ระดับ :"+levelhumid);

               lightvalue.setText(light+" Lux");
               Tlevellight.setText("ระดับ :"+levellight);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });


        return  rootView;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }

   //  @Override
   // public void onCreate( Bundle savedInstanceState) {
   //     super.onCreate(savedInstanceState);

        //get value from firebase อ่านค่าจากไฟล์ Firebase
      /*  FirebaseDatabase database = FirebaseDatabase.getInstance();
        tempRef = database.getReference();
        temperaturevalue=getView().findViewById(R.id.datatemperature);

        tempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String value = String.valueOf(map.get("Valuetemp"));
                temperaturevalue.setText(value);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        }); */
    }


//}
