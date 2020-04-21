package com.codingdemos.tablayout;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.RemoteViews;
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
        refWater = FirebaseDatabase.getInstance().getReference("Node").child("RELAY1");
        StateWater = rootview.findViewById(R.id.state_waterpump);

        CoolingOff = rootview.findViewById(R.id.btn_CoolingOFF);
        CoolingOn = rootview.findViewById(R.id.btn_CoolingON);
        refCooling = FirebaseDatabase.getInstance().getReference("Node").child("RELAY2");
        Statecooling = rootview.findViewById(R.id.state_cooling);

        HeatingOff = rootview.findViewById(R.id.btn_HeatingOFF);
        HeatingOn = rootview.findViewById(R.id.btn_HeatingON);
        refHeating = FirebaseDatabase.getInstance().getReference("Node").child("RELAY3");
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
                DatabaseReference state = refWater.child("Pump");
                DatabaseReference text =refWater.child("statusPump");
                state.setValue(0);
                text.setValue("ปิด");

                closewaternotification();

            }
        });

        WaterOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refWater.child("Pump");
                DatabaseReference text = refWater.child("statusPump");
                state.setValue(1);
                text.setValue("เปิด");

                openwaternotification();

            }
        });

        CoolingOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refCooling.child("CoolingFan");
                DatabaseReference text = refCooling.child("statusCoolingFan");
                state.setValue(0);
                text.setValue("ปิด");

                closecoolingfannotification();
            }
        });

        CoolingOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refCooling.child("CoolingFan");
                DatabaseReference text = refCooling.child("statusCoolingFan");
                state.setValue(1);
                text.setValue("เปิด");

                opencoolingfannotification();
            }
        });

        HeatingOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refHeating.child("HeatingFan");
                DatabaseReference text = refHeating.child("statusHeatingFan");
                state.setValue(0);
                text.setValue("ปิด");

                closeheatingnotification();
            }
        });

        HeatingOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference state = refHeating.child("HeatingFan");
                DatabaseReference text = refHeating.child("statusHeatingFan");
                state.setValue(1);
                text.setValue("เปิด");

                openheatingnotification();
            }
        });



        refWater.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Map map = (Map)dataSnapshot.getValue();
                String statetext = String.valueOf(map.get("statusPump"));
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
                String statetext = String.valueOf(map.get("statusCoolingFan"));
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
                String statetext = String.valueOf(map.get("statusHeatingFan"));
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

    //code แจ้งเตือน ปิด-เปิดน้ำ
    private void closewaternotification(){
        Notification.InboxStyle style = new Notification.InboxStyle()
                .setBigContentTitle("แจ้งเตือน")
                .addLine("ปั๊มน้ำ")
                .addLine("ปิด");


        Notification.Builder builder = new Notification.Builder(getContext())
                .setSmallIcon(R.drawable.pipes)
                .setAutoCancel(true)

                .setStyle(style);
        Notification notif = builder.build();
        NotificationManager notifMan = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );



        notifMan.notify(123,notif);
    }
    private void openwaternotification() {
        Notification.InboxStyle style = new Notification.InboxStyle()
                .setBigContentTitle("แจ้งเตือน")
                .addLine("ปั๊มน้ำ")
                .addLine("เปิด");


        Notification.Builder builder = new Notification.Builder(getContext())
                .setSmallIcon(R.drawable.pipes)

                .setAutoCancel(true)
                .setStyle(style);
        Notification notif = builder.build();
        NotificationManager notifMan = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );

        notifMan.notify(123,notif);
    }

    //แจ้งเตือน ปิด-เปิด พัดลม

    private void closecoolingfannotification() {
        Notification.InboxStyle style1 = new Notification.InboxStyle()
                .setBigContentTitle("แจ้งเตือน")
                .addLine("พัดลม")
                .addLine("ปิด");


        Notification.Builder builder1 = new Notification.Builder(getContext())
                .setSmallIcon(R.drawable.coolingfan1)

                .setStyle(style1);
        Notification noti1 = builder1.build();
        NotificationManager notifMan1 = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

        notifMan1.notify(100,noti1);
    }

    private void opencoolingfannotification() {
        Notification.InboxStyle style2 = new Notification.InboxStyle()
                .setBigContentTitle("แจ้งเตือน")
                .addLine("พัดลม")
                .addLine("เปิด");


        Notification.Builder builder2 = new Notification.Builder(getContext())
                .setSmallIcon(R.drawable.coolingfan1)

                .setStyle(style2);
        Notification noti1 = builder2.build();
        NotificationManager notifMan1 = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

        notifMan1.notify(100,noti1);
    }

    //แจ้งเตือน ปิด-เปิด heating

    private void closeheatingnotification() {
        Notification.InboxStyle style3 = new Notification.InboxStyle()
                .setBigContentTitle("แจ้งเตือน")
                .addLine("พัดลม")
                .addLine("ปิด");


        Notification.Builder builder3 = new Notification.Builder(getContext())
                .setSmallIcon(R.drawable.heating)

                .setStyle(style3);
        Notification noti2 = builder3.build();
        NotificationManager notifMan2 = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

        notifMan2.notify(200,noti2);
    }

    private void openheatingnotification() {
        Notification.InboxStyle style3 = new Notification.InboxStyle()
                .setBigContentTitle("แจ้งเตือน")
                .addLine("พัดลม")
                .addLine("เปิด");


        Notification.Builder builder3 = new Notification.Builder(getContext())
                .setSmallIcon(R.drawable.heating)

                .setStyle(style3);
        Notification noti2 = builder3.build();
        NotificationManager notifMan2 = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

        notifMan2.notify(200,noti2);
    }




}