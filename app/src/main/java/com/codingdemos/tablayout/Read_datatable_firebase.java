package com.codingdemos.tablayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private String DataId;
    private ListView mListView;
    private int counttype;



    @SuppressLint("WrongViewCast")
    private void initView(){
        recyclerView =findViewById(R.id.messageRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static final String TAG = "Insert" ;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_adapter);
        //initView();

//        ReadFirebaseInsert();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //writedata = database.getReference("TYPE");
        writedata = FirebaseDatabase.getInstance().getReference("TYPE");
        auth = FirebaseAuth.getInstance();
        FirebaseUser user  = auth.getCurrentUser();
       // DataId = user.getUid();

        InsertList  = new ArrayList<>();

        writedata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                      for(DataSnapshot productSnapshot:dataSnapshot.getChildren()){
                          InsertV2 Insertdata = productSnapshot.getValue(InsertV2.class);
                          Log.d(TAG, "p.getCourse_title() : " + Insertdata.getDate());
                          InsertList.add(Insertdata);
                      }

                    }
                    adapter = new InsertAdapter(Read_datatable_firebase.this,InsertList);
                    //recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        LinearLayout menu_home = findViewById(R.id.insert_adapter);
        menu_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                overridePendingTransition(0,0);
                startActivity(intent);

            }
        });



    }

   /* private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            InsertV2 insertInfo = new InsertV2();
            insertInfo.setDate(ds.child(DataId).getValue(InsertV2.class).getDate());
            insertInfo.setTime(ds.child(DataId).getValue(InsertV2.class).getTime());
            insertInfo.setType1(ds.child(DataId).getValue(InsertV2.class).getType1());
            insertInfo.setTvm1(ds.child(DataId).getValue(InsertV2.class).getIvm1());
            insertInfo.setRow(ds.child(DataId).getValue(InsertV2.class).getRow());

            ArrayList<String> arrays = new ArrayList<>();
            arrays.add(insertInfo.getDate());
            arrays.add(insertInfo.getTime());
            arrays.add(insertInfo.getType1());
            arrays.add(insertInfo.getIvm1());
            arrays.add(insertInfo.getRow());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrays);
            mListView.setAdapter(adapter);
        }
    }  */

  /*  private void ReadFirebaseInsert(){
        auth = FirebaseAuth.getInstance();
        //InsertList = new ArrayList<>();


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
            //            Log.d(TAG,"iv2.getDate"+ iv2.getDate().toString());
                       // Log.d(TAG,"in2.getTime"+iv2.getTime().toString());


             //       }

                    //adapter = new InsertAdapter(Read_datatable_firebase.this, InsertList);
                    //รอแก้ไข
           //         recyclerView.setAdapter(adapter);
          //      }


         //   }

         //   @Override
         //   public void onCancelled(@NonNull DatabaseError databaseError) {

       //     }
     //   });

  //  }*/





}
