package com.codingdemos.tablayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Read_datatable_firebase extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference writedata;
    private FirebaseAuth auth;
    private InsertAdapter adapter;
    private List<InsertV2> InsertList;

    @SuppressLint("WrongViewCast")
    private void initView(){
        recyclerView =findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static final String TAG = "Insert" ;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_adapter);
        initView();

        ReadFirebaseInsert();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        writedata = database.getReference("TYPE");


     /*   writedata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                        InsertV2 iv2 =productSnapshot.getValue(InsertV2.class);

                        //รอแก้ไข
                        if(auth.getCurrentUser().toString().equals(iv2.getId_insert().toString()) ){
                            InsertList.add(iv2);
                        }
                        Log.d(TAG,"iv2.getDate"+ iv2.getDate().toString());
                        Log.d(TAG,"in2.getTime"+iv2.getTime().toString());
                    }

                    adapter = new InsertAdapter(Read_datatable_firebase.this, InsertList);
                    //รอแก้ไข
                    recyclerView.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); */




    }

    private void ReadFirebaseInsert(){
        auth = FirebaseAuth.getInstance();
        InsertList = new ArrayList<>();


        writedata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                        InsertV2 iv2 =productSnapshot.getValue(InsertV2.class);

                        //รอแก้ไข
                       /* if(auth.getCurrentUser().getEmail().toString().equals(iv2.getId_insert().toString()) ){
                            InsertList.add(iv2);
                        }*/
                        //Log.d(TAG,"iv2.getDate"+ iv2.getDate().toString());
                       // Log.d(TAG,"in2.getTime"+iv2.getTime().toString());
                    }

                    adapter = new InsertAdapter(Read_datatable_firebase.this, InsertList);
                    //รอแก้ไข
                    recyclerView.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
