package com.codingdemos.tablayout;


import android.annotation.SuppressLint;
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

    private TextView temperaturevalue;
    private DatabaseReference tempRef;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_control, container, false);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }

     @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get value from firebase อ่านค่าจากไฟล์ Firebase
        temperaturevalue=getView().findViewById(R.id.datatemperature);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        tempRef = database.getReference();



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
        });
    }


}
