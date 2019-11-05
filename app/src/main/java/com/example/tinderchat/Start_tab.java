package com.example.tinderchat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.tinderchat.Vartical.Vertical_Matches;
import com.google.android.material.tabs.TabLayout;

public class Start_tab extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewpager;
    TabLayout tabLayout;
    private String TAG = "Start_tab";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tab);
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewpager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabb);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_person_pin_black));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.iconred));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.chat));
       //change color to all
         tabLayout.setTabIconTint(ContextCompat.getColorStateList(Start_tab.this, R.color.Black));

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Profile(), "About User");
        adapter.addFragment(new Start_page(), "swaaping");
        adapter.addFragment(new Vertical_Matches(), "Vertical_Matches");
        //Setting adapter

        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewpager));
        mViewpager.setCurrentItem(1);


        Intent n= getIntent();
        Bundle bbb = n.getExtras();
        if(bbb!=null)
        {    String jjj =(String) bbb.get("Chats");

            mViewpager.setCurrentItem(2);

               }

tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tab.getIcon().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
    }

    @Override public void onTabReselected(TabLayout.Tab tab) { }}); }
   // boolean doubleBackToExitPressedOnce = true;

    @Override
    public void onBackPressed() {

            AlertDialog.Builder builder=new AlertDialog.Builder(Start_tab.this);
            builder.setMessage("ARE YOU SURE You Want To Exit").setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
//                    System.exit(0);
                }
            }).setNegativeButton("Cancle",null);
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

    }

//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
    }
