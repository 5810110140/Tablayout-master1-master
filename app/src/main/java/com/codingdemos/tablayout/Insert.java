package com.codingdemos.tablayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.util.Log;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;




public class Insert extends AppCompatActivity {
        TextView mTdate,mTdate2,mTdate3, mTtime,mTtime2,mTtime3;
        Button mBtdate,mBtdate2,mBtdate3, mBttime,mBtime2,mBtime3;



        // ตัวแปรบันทึกค่า
        TextView Tdate;
        Button btnsave;

        Spinner mushroomtype1;
        Spinner mushroomtype2;
        Spinner mushroomtype3;

        Spinner mushroomitem1;
        Spinner mushroomitem2;
        Spinner mushroomitem3;
    public static final String TAG = "Insert" ;



        Calendar c;
        DatePickerDialog dpd;
        TimePickerDialog tpd;

        private RecyclerView recyclerView;
        private DatabaseReference writedata;
         private FirebaseAuth auth;
         //private InsertAdapter adapter;
         private List<InsertV2> InsertList;


         @SuppressLint("WrongViewCast")
         private void initView(){
             recyclerView =findViewById(R.id.recyclerview);
             recyclerView.setHasFixedSize(true);
             recyclerView.setLayoutManager(new LinearLayoutManager(this));
         }


        @Override
        protected void  onCreate(Bundle saveInstanceState) {

            super.onCreate(saveInstanceState);
            setContentView(R.layout.activity_insert);



            mTdate = (TextView)findViewById(R.id.textdate);

            mTtime = (TextView)findViewById(R.id.texttime) ;

            mBtdate = (Button)findViewById(R.id.btndate);

            mBttime = (Button)findViewById(R.id.btntime);


            mBtdate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    c = Calendar.getInstance();

                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year = c.get(Calendar.YEAR);

                    dpd = new DatePickerDialog(Insert.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                            mTdate.setText(mDay + "/" + (mMonth+1)+ "/" + mYear);
                        }
                    },year, month, day);
                    dpd.show();
                }
            });

            mBttime.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMiunte = c.get(Calendar.MINUTE);

                    tpd = new TimePickerDialog(Insert.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            mTtime.setText(hourOfDay +":" +minute);
                        }
                    },mHour, mMiunte, false);
                    tpd.show();
                }
            });



            Button Return = findViewById(R.id.return1);
            Return.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    System.out.print("ย้อนกลับ");
                    openDataFragment();
                }
            });



            FirebaseDatabase database = FirebaseDatabase.getInstance();
            writedata = database.getReference("TYPE");

            Tdate = (TextView) findViewById(R.id.textdate);
            btnsave = findViewById(R.id.btnsave);
            mushroomtype1 = findViewById(R.id.mushtype1);


            mushroomitem1 = findViewById(R.id.slotvalue1);


            auth = FirebaseAuth.getInstance();
           InsertList = new ArrayList<>();



           /* writedata.addValueEventListener(new ValueEventListener() {
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

                    adapter = new InsertAdapter(Insert.this, InsertList);
                    //รอแก้ไข
                    recyclerView.setAdapter(adapter);
                }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            }); */

            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addData();

                }
            });

        }

        public  void addData(){
            //ประเภทเห็ด
            String type1 = mushroomtype1.getSelectedItem().toString();


            //จำนวนก้อนเห็ด
            String ivm1 = mushroomitem1.getSelectedItem().toString();


            //วันที่และเวลา
            String date = mTdate.getText().toString();


            String time = mTtime.getText().toString();




           if(!TextUtils.isEmpty(type1)||(!TextUtils.isEmpty(ivm1))||(!TextUtils.isEmpty(date))||(!TextUtils.isEmpty(time))){
                //เขียนค่า ชนิดลง Firebase
              String id_insert = writedata.child("TYPE").push().getKey();
                InsertV2 Datatype =    new InsertV2( id_insert,  type1,  ivm1,  date, time);
                writedata.child(id_insert).setValue(Datatype);


                Toast.makeText(this,"บันทึกสำเร็จ", Toast.LENGTH_LONG).show();

            }
           /* if((type1.equals(""))&&(type1.equals(""))&&(ivm1.equals(""))&&(date.equals(""))&&(time.equals(""))){
                //เขียนค่า ชนิดลง Firebase
                String id_insert = writedata.child("TYPE").push().getKey();
                // InsertV2 Datatype =    new InsertV2( id_insert,  type1,  ivm1,  date, time);

                // writedata.child(id_insert).setValue(Datatype);


                //Toast.makeText(this,"บันทึกสำเร็จ", Toast.LENGTH_LONG).show();

            }*/

        }


        public  void openDataFragment(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


}