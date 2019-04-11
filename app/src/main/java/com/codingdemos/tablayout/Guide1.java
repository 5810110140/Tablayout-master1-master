package com.codingdemos.tablayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class Guide1 extends AppCompatActivity {
    Spinner spinner;
    int Mushroom0 = 0;
    int Mushroom1 = 1;
    int Mushroom2 = 2;
    int Mushroom3 = 3;
    int Mushroom4 = 4;
    int Mushroom5 = 5;
    int Mushroom6 = 6;
    int Mushroom7 = 7;
    int Mushroom8 = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide1);
        spinner = (Spinner)findViewById(R.id.spinner1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (Mushroom0 == position){
                   return; // do nothing
                }
                else if (Mushroom1 == position){
                    Intent mush1 = new Intent(Guide1.this,mushroom1.class);
                    startActivity(mush1);

                }
                else if (Mushroom2 == position){
                    Intent mush2 = new Intent(Guide1.this,mushroom2.class);
                    startActivity(mush2);

                }
                else if (Mushroom3 == position){
                    Intent mush3 = new Intent(Guide1.this,mushroom3.class);
                    startActivity(mush3);

                }
                else if (Mushroom4 == position){
                    Intent mush4 = new Intent(Guide1.this,mushroom4.class);
                    startActivity(mush4);

                }
                else if (Mushroom5 == position){
                    Intent mush5 = new Intent(Guide1.this,mushroom5.class);
                    startActivity(mush5);

                }
                else if (Mushroom6 == position){
                    Intent mush6 = new Intent(Guide1.this,mushroom6.class);
                    startActivity(mush6);

                }
                else if (Mushroom7 == position){
                    Intent mush7 = new Intent(Guide1.this,mushroom7.class);
                    startActivity(mush7);

                }
                else if (Mushroom8 == position){
                    Intent mush8 = new Intent(Guide1.this,mushroom8.class);
                    startActivity(mush8);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
