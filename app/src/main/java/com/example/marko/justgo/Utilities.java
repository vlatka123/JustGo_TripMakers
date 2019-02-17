package com.example.marko.justgo;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Utilities extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;

    private ViewPager mViewPager;
    private SectionPagerAdapter mSectionsPageAdapter;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities);



        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("JustGo");

        mViewPager = (ViewPager) findViewById(R.id.main_tabPager);
        mSectionsPageAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPageAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_menu:
                Intent intent_menu = new Intent(Utilities.this, Profile.class);
                startActivity(intent_menu);
                break;

            case R.id.my_trips_menu:
                Intent intent_trips = new Intent(Utilities.this, MyTrip.class);
                startActivity(intent_trips);
                break;

            case R.id.utilities_menu:
                break;

            case R.id.bug_report_menu:
                Intent intent_bug = new Intent(Utilities.this, BugReport.class);
                startActivity(intent_bug);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }




}