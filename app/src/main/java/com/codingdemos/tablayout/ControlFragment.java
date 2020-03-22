package com.codingdemos.tablayout;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ControlFragment extends Fragment {

    ToggleButton waterpipe,coolingfan,heating;
    private DatabaseReference refwater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootview = inflater.inflate(R.layout.fragment_control, container, false);

        waterpipe = rootview.findViewById(R.id.toggleButtonpipe);
        coolingfan = rootview.findViewById(R.id.toggleButtonfan);
        heating = rootview.findViewById(R.id.toggleButtonheater);
        refwater = FirebaseDatabase.getInstance().getReference("waterstatus");

        //save switch state
        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("save1",MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("save2",MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = getContext().getSharedPreferences("save3",MODE_PRIVATE);





        /*refwater.child("waterstatus").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Check_state_waterpipe Check_water = dataSnapshot.getValue(Check_state_waterpipe.class);

                String notificationIndividualWater = Check_water.getNotification_water();

                if(notificationIndividualWater.equals("0")){
                SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("save1",MODE_PRIVATE);
                waterpipe.setChecked(sharedPreferences1.getBoolean("value1",true));
                waterpipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                          //  FirebaseDatabase database = FirebaseDatabase.getInstance();
                           // DatabaseReference myRef = database.getReference("waterstatus");
                            refwater.child("waterstatus").setValue("0");
                            SharedPreferences.Editor editor = getContext().getSharedPreferences("save1",
                                    MODE_PRIVATE).edit();
                            editor.putBoolean("value1",true);
                            editor.apply();
                           // myRef.setValue(1);
                            waterpipe.setChecked(true);
                        }
                    });
                }
                        if(notificationIndividualWater.equals("1")){
                            waterpipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                  //  DatabaseReference myRef = database.getReference("waterstatus");
                                    refwater.child("waterstatus").setValue("1");
                                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save1",
                                            MODE_PRIVATE).edit();
                                    editor.putBoolean("value1",false);
                                    editor.apply();
                                    //myRef.setValue(0);
                                    waterpipe.setChecked(false);
                                }
                            });
                        }
                    }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); */

        //switch waterpipe
        waterpipe.setChecked(sharedPreferences1.getBoolean("value1",true));
        waterpipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    //FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = refwater.child("waterstatus");
                    DatabaseReference myRef2 =refwater.child("statustext");
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save1",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value1",true);
                    editor.apply();
                    myRef.setValue("1");
                    myRef2.setValue("เปิด");
                    waterpipe.setChecked(true);
                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("waterstatus");
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save1",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value1",false);
                    editor.apply();
                    myRef.setValue("0");
                    waterpipe.setChecked(false);
                }
            }
        });

        //switch coolingfan
        coolingfan.setChecked(sharedPreferences2.getBoolean("value2",true));
        coolingfan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("coolingstatus");
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save2",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value2",true);
                    editor.apply();
                    myRef.setValue("1");
                    coolingfan.setChecked(true);


                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("coolingstatus");
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save2",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value2",false);
                    editor.apply();
                    myRef.setValue("0");
                    coolingfan.setChecked(false);
                }
            }
        });

        // switch heating
        heating.setChecked(sharedPreferences3.getBoolean("value3",true));
        heating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("heatingstatus");
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save3",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value3",true);
                    editor.apply();
                    myRef.setValue("1");
                    heating.setChecked(true);
                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("heatingstatus");
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save3",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value3",false);
                    editor.apply();
                    myRef.setValue("0");
                    heating.setChecked(false);

                }
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
