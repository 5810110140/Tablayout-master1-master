package com.codingdemos.tablayout;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;



public class Insert extends AppCompatActivity {
        TextView mTdate, mTtime;
        Button mBtdate, mBttime;



        // ตัวแปรบันทึกค่า
        TextView Tdate;
        Button btnsave;

        Spinner mushroomtype1;
        Spinner mushroomtype2;
        Spinner mushroomtype3;

        Spinner mushroomitem1;
        Spinner mushroomitem2;
        Spinner mushroomitem3;




    Calendar c;
        DatePickerDialog dpd;
        TimePickerDialog tpd;

        private DatabaseReference writedata;


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
            writedata = database.getReference();

            Tdate = (TextView) findViewById(R.id.textdate);
            btnsave = findViewById(R.id.btnsave);
            mushroomtype1 = findViewById(R.id.mushtype1);
            mushroomtype2 = findViewById(R.id.mushtype2);
            mushroomtype3 = findViewById(R.id.mushtype3);

            mushroomitem1 = findViewById(R.id.slotvalue1);
            mushroomitem2 = findViewById(R.id.slotvalue2);
            mushroomitem3 = findViewById(R.id.slotvalue3);



            writedata.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Map map = (Map)dataSnapshot.getValue();
                    Map map1 = (Map)dataSnapshot.child("Valuetemp1").getValue();
                    String textdate = String.valueOf(map1.get("id_data"));


                    Tdate.setText(textdate);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addData();

                }
            });

        }

        public  void addData(){
            //ประเภทเห็ด
            String typemushroom1 = mushroomtype1.getSelectedItem().toString();
            String typemushroom2 = mushroomtype2.getSelectedItem().toString();
            String typemushroom3 = mushroomtype3.getSelectedItem().toString();

            //จำนวนก้อนเห็ด
            String slot1 = mushroomitem1.getSelectedItem().toString();
            String slot2 = mushroomitem2.getSelectedItem().toString();
            String slot3 = mushroomitem3.getSelectedItem().toString();

            //วันที่และเวลา
            String textDate = mTdate.getText().toString();
            String textTime = mTtime.getText().toString();



            if(!TextUtils.isEmpty(typemushroom1)||(!TextUtils.isEmpty(typemushroom2))||(!TextUtils.isEmpty(typemushroom3))){
                //เขียนค่า ชนิดลง Firebase
              String id_data = writedata.push().getKey();
              typemushroom Datatype =    new typemushroom(id_data, typemushroom1,typemushroom2,typemushroom3);
              writedata.child(id_data).setValue(Datatype);




            }

            if (!TextUtils.isEmpty(slot1)||(!TextUtils.isEmpty(slot2))||(!TextUtils.isEmpty(slot3))){
                //เขียนค่า จำนวนก้อนเห็ดลง Firebase
                String id_value =writedata.push().getKey();
                insert_value_mushroom Datavalue = new insert_value_mushroom(id_value, slot1,slot2,slot3);
                writedata.child(id_value).setValue(Datavalue);


            }
            if(!TextUtils.isEmpty(textDate)||(!TextUtils.isEmpty(textTime))){
                //บันทึกค่าวันที่และเวลาลง Firebase
                String id_date =writedata.push().getKey();
                TimeandDate Data_date =new TimeandDate(id_date,textDate,textTime);
                writedata.child(id_date).setValue(Data_date);

                Toast.makeText(this,"บันทึกสำเร็จ", Toast.LENGTH_LONG).show();
            }


        }


        public  void openDataFragment(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


}