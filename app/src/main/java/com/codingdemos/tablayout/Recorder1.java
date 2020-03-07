package com.codingdemos.tablayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Recorder1 extends AppCompatActivity {

    TextView mTdate;
    TextView mTtime;
    Button mBtdate;
    Button mBttime;

    TextView Tdate;
    Button btnsave;

    Spinner mushroomtype1;
    Spinner mushroomitem1;

    Spinner Rowbox;

    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;

    private DatabaseReference writedata;
    private FirebaseAuth auth;
    //private List<InsertV2> InserList;
    private ArrayList<Object> InsertList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_recorder);
        super.onCreate(savedInstanceState);

        mTdate = findViewById(R.id.textdate);
        mTtime = findViewById(R.id.texttime);
        mBtdate = findViewById(R.id.btndate);
        mBttime = findViewById(R.id.btntime);

        mBtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();

                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Recorder1.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view,int year,int month,int dayOfMonth) {
                        mTdate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        mBttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMiunte = c.get(Calendar.MINUTE);

                tpd = new TimePickerDialog(Recorder1.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view,int hourOfDay,int minute) {
                        mTtime.setText(hourOfDay + ":" + minute);
                    }
                },mHour,mMiunte,false);
                tpd.show();
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        writedata = database.getReference("TYPE");

        Tdate = (TextView) findViewById(R.id.textdate);
        btnsave = findViewById(R.id.btnsave);
        mushroomtype1 = findViewById(R.id.mushtype1);
        mushroomitem1 = findViewById(R.id.slotvalue1);
        Rowbox = findViewById(R.id.row);
        auth = FirebaseAuth.getInstance();
        InsertList = new ArrayList<>();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                reset();
            }
        });

        Button Return = findViewById(R.id.return1);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("ย้อนกลับ");
                openDataFragment();

            }
        });
    }
    public void addData(){
        //ประเภทเห็ด
        String type1 = mushroomtype1.getSelectedItem().toString();
        //จำนวนก้อนเห็ด
        String ivm1 = mushroomitem1.getSelectedItem().toString();
        //วันที่และเวลา
        String date = mTdate.getText().toString();
        String time = mTtime.getText().toString();

        if (!TextUtils.isEmpty(type1) || (!TextUtils.isEmpty(ivm1)) || (!TextUtils.isEmpty(date)) || (!TextUtils.isEmpty(time))) {
            //เขียนค่า ชนิดลง Firebase
            String id_insert = writedata.child("TYPE").push().getKey();
            InsertV2 Datatype = new InsertV2(id_insert,type1,ivm1,date,time);
            writedata.child(id_insert).setValue(Datatype);


            Toast.makeText(this,"บันทึกสำเร็จ",Toast.LENGTH_LONG).show();

        }

    }
    public void reset(){
        Intent resetpage = new Intent(this,Recorder1.class);
        startActivity(resetpage);
    }
    public  void openDataFragment(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
