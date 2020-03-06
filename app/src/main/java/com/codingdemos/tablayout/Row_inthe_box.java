package com.codingdemos.tablayout;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Row_inthe_box extends AppCompatActivity {

    private TextView rowA;
    private TextView rowB;
    private TextView rowC;
    private TextView rowD;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_rowin_the_box);
        super.onCreate(savedInstanceState);

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
