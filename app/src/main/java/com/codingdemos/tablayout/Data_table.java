package com.codingdemos.tablayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data_table extends Activity {

    private ArrayList<String>  arrayList = new ArrayList<>();
    private DatabaseReference datatable;
    private ArrayAdapter<String> arrayAdapter;
    ListView listView;
    TextView TVdate,TVtime,TType1,TType2,TType3;
    TextView Tvalue1,Tvalue2,Tvalue3;

    Map<String, String> map = new HashMap<>();



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datatable);
        listView  = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        datatable = database.getReference("TYPE");

        datatable.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                InsertV2 value = dataSnapshot.getValue(InsertV2.class);
                arrayList.add(String.valueOf(value));
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //arrayAdapter adapter = new ArrayAdapter(this,R.layout.insert_adapter,arrayList);
        //listView.setAdapter(adapter);

        TVdate = findViewById(R.id.Tdate);
        TVtime = findViewById(R.id.Ttime);

        TType1 = findViewById(R.id.type1);
        TType2 = findViewById(R.id.type2);
        TType3 = findViewById(R.id.type3);

        Tvalue1 = findViewById(R.id.Va1);
        Tvalue2 = findViewById(R.id.Va2);
        Tvalue3 = findViewById(R.id.Va3);

        findViewById(R.id.btnsave).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!TVdate.getText().toString().isEmpty()&&!TVtime.getText().toString().isEmpty()&&!TType1.getText().toString().isEmpty()&&!TType2.getText().toString().isEmpty()&&
                !TType3.getText().toString().isEmpty()&&!Tvalue1.getText().toString().isEmpty()&&!Tvalue1.getText().toString().isEmpty()&&!Tvalue2.getText().toString().isEmpty()&&Tvalue3.getText().toString().isEmpty());
                map.put(TVdate.getText().toString(),TVtime.getText().toString());
                //, TType1.getText().toString(),TType2.getText().toString(),TType3.getText().toString(),Tvalue1.getText().toString(),Tvalue2.getText().toString(),Tvalue3.getText().toString()

                //arrayAdapter adapter = new ArrayAdapter(Data_table.this,R.layout.insert_adapter,arrayList);
                arrayList.clear();
                arrayList.addAll(map.values());
               listView.setAdapter(arrayAdapter);





            }
        });

    }

}
