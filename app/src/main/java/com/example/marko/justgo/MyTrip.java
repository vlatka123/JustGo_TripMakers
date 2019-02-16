package com.example.marko.justgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyTrip extends AppCompatActivity {

    Button add_new_trip;
    Button view_old_trips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip);

        add_new_trip = (Button) findViewById(R.id.Add_new_trip_btn);
        view_old_trips = (Button) findViewById(R.id.View_old_trips_btn);

        // Toolbar initialization
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        add_new_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyTrip.this, AddNewTrip.class);
                startActivity(intent);

            }
        });

        view_old_trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyTrip.this, SeePreviousTrips.class);
                startActivity(intent);

            }
        });

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
                Intent intent_menu = new Intent(MyTrip.this, Profile.class);
                startActivity(intent_menu);
                break;

            case R.id.my_trips_menu:
                Intent intent_trips = new Intent(MyTrip.this, MyTrip.class);
                startActivity(intent_trips);
                break;

            case R.id.utilities_menu:
                Intent intent_utilities = new Intent(MyTrip.this, Utilities.class);
                startActivity(intent_utilities);
                break;

            case R.id.bug_report_menu:
                Intent intent_bug = new Intent(MyTrip.this, BugReport.class);
                startActivity(intent_bug);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
