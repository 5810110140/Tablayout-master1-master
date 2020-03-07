/*package com.codingdemos.tablayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.List;

public class RecoderInesert extends AppCompatActivity {

    TextView mTdate;
    TextView mTtime;
    Button mBtdate;
    Button mBttime;

    TextView Tdate;
    Button btnsave;

    Spinner mushroomtype1;
    Spinner mushroomitem1;

    Spinner Rowbox;

   // public static final String TAG = "RecoderInesert";

    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;

    private DatabaseReference writedata;
    private FirebaseAuth auth;
    private List<InsertV2> InserList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        mTdate = findViewById(R.id.textdate);
        mTtime = findViewById(R.id.texttime);
        mBtdate = findViewById(R.id.btndate);
        mBttime = findViewById(R.id.btntime);

        mBttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();

                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(RecoderInesert.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view,int year,int month,int dayOfMonth) {
                        mTdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year, month, day);
                dpd.show();
            }
        });
    }
}*/
