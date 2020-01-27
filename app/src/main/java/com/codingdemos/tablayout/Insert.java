package com.codingdemos.tablayout;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;



public class Insert extends AppCompatActivity {
        TextView mTdate, mTtime;
        Button mBtdate, mBttime;

        Calendar c;
        DatePickerDialog dpd;
        TimePickerDialog tpd;

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

        }
        public  void openDataFragment(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
}