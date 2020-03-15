package com.codingdemos.tablayout;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Read_Harvest_Firebase  extends AppCompatActivity {

    private RecyclerView harvest_recyclerview;
    private FirebaseAuth auth;
    private HarvestAdapter adapter;
    public List<harvast_value> HarvestList;

    public static  final String TAG ="Harvest";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.harvest_list_view);

        initView();
        ReadFirebaseHarvest();

        FirebaseUser user = auth.getCurrentUser();
    }
    private void initView(){
        harvest_recyclerview = findViewById(R.id.HarvestRecyclerView);
        harvest_recyclerview.setHasFixedSize(true);
        harvest_recyclerview.setLayoutManager(new LinearLayoutManager(this));

    }

    private void ReadFirebaseHarvest(){
        auth = FirebaseAuth.getInstance();

        HarvestList = new ArrayList<>();

        DatabaseReference readdata = FirebaseDatabase.getInstance().getReference("harvesting ");
        readdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot productSbapshot : dataSnapshot.getChildren()){

                        harvast_value harvestdata = productSbapshot.getValue(harvast_value.class);
                        HarvestList.add(harvestdata);
                    }
                    adapter = new HarvestAdapter(Read_Harvest_Firebase.this,HarvestList);
                    harvest_recyclerview.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
