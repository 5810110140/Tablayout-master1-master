package com.codingdemos.tablayout;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.ScrollView;
import android.widget.LinearLayout;


import com.google.firebase.auth.FirebaseAuth;

import lecho.lib.hellocharts.view.LineChartView;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabstatus;
    TabItem tabcontrol;
    TabItem tabdata;
    LineChartView chart;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return MainActivity.super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about_us){
            Intent about = new Intent(MainActivity.this, Insert.class);
            startActivity(about);
            return true;
        }
        else if  (id == R.id.guide){
            Intent myguide = new Intent(MainActivity.this, Guide.class);
            startActivity(myguide);
            return true;
        }
        else if (id == R.id.guide1){
            Intent myguide1 = new Intent(MainActivity.this, Guide1.class);
            startActivity(myguide1);
        }
        return MainActivity.super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabstatus = findViewById(R.id.tabstatus);
        tabcontrol = findViewById(R.id.tabcontrol);
        tabdata = findViewById(R.id.tabdata);
        viewPager = findViewById(R.id.viewPager);
        chart = findViewById(R.id.chart);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        showNotication();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                }if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                } else if (tab.getPosition() == 3) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));


                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorPrimary));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }




        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void showNotication() {
        Notification.InboxStyle style = new Notification.InboxStyle()
                .setBigContentTitle("แจ้งเตือน")
                .addLine("ระดับน้้ำน้อยกว่ากำหนด")
                .addLine("กรุณาเติมน้ำในถัง");
        Notification.Builder builder =
                new Notification.Builder(getBaseContext())
                        .setSmallIcon(R.drawable.watertank1)
                        .setStyle(style);

        Notification notif = builder.build();
        NotificationManager notifMan =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        notifMan.notify(123, notif);
    }


}
