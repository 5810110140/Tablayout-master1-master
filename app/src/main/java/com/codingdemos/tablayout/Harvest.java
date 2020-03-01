package com.codingdemos.tablayout;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Harvest extends AppCompatActivity {

    Button mTDate,btnSave;
    TextView Weighttext;
    TextView textdate;
    Calendar c;
    DatePickerDialog dpd;
    Spinner Stypemushroom;
    Spinner Svaluemushroom;
    private DatabaseReference writedata;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.harvest_insert);

        mTDate =  findViewById(R.id.btndate);
        btnSave = findViewById(R.id.btnsave);
        Stypemushroom = findViewById(R.id.spinner1);
        Svaluemushroom = findViewById(R.id.spinner2);
        Weighttext = findViewById(R.id.weighttext);
        textdate = findViewById(R.id.iddate);





        mTDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Harvest.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        textdate.setText(mDay + "/" + (mMonth+1)+ "/" + mYear);
                    }
                },year, month, day);
                dpd.show();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        writedata = database.getReference("harvesting ");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

    }

    private void addData() {

        String date = mTDate.getText().toString();
        String type = Stypemushroom.getSelectedItem().toString();
        String value = Svaluemushroom.getSelectedItem().toString();
        String weight = Weighttext.getText().toString();

        if(!TextUtils.isEmpty(date)||(!TextUtils.isEmpty(type))||(!TextUtils.isEmpty(value))||(!TextUtils.isEmpty(weight))){

            String id_harvast = writedata.child("\" harvesting \"").push().getKey();
            harvast_value Datatype =    new harvast_value(id_harvast,  type,  value,  date,  weight);
            writedata.child(id_harvast).setValue(Datatype);


        }
    }
}

