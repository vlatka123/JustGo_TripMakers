package com.example.marko.justgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MyTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip);


        // Toolbar initialization
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

    }

    // Omogucuje da se vidi menu na custom toolbar (tri tockice)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Omogucava da se preko menu-a ide na druge activity (za sada ne postoje drugi activity)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_menu:


                break;
            case R.id.my_trips_menu:



                break;
            case R.id.utilities_menu:


                break;
            case R.id.bug_report_menu:

                break;
            case R.id.check_for_updates_menu:
                Toast.makeText(getApplicationContext(), "Check for updates", Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
