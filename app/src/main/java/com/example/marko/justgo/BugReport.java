package com.example.marko.justgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BugReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);


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
                Intent intent_menu = new Intent(BugReport.this, Profile.class);
                startActivity(intent_menu);
                break;

            case R.id.my_trips_menu:
                Intent intent_trips = new Intent(BugReport.this, MyTrip.class);
                startActivity(intent_trips);
                break;

            case R.id.utilities_menu:
                Intent intent_utilities = new Intent(BugReport.this, Utilities.class);
                startActivity(intent_utilities);
                break;

            case R.id.bug_report_menu:
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
