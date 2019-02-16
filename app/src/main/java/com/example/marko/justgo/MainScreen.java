package com.example.marko.justgo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Toolbar initialization
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);



        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainScreen.this, Profile.class);
                startActivity(intent4);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainScreen.this, MyTrip.class);
                startActivity(intent5);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(MainScreen.this, Utilities.class);
                startActivity(intent6);
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
                Intent intent_menu = new Intent(MainScreen.this, Profile.class);
                startActivity(intent_menu);
                break;

            case R.id.my_trips_menu:
                Intent intent_trips = new Intent(MainScreen.this, MyTrip.class);
                startActivity(intent_trips);
                break;

            case R.id.utilities_menu:
                Intent intent_utilities = new Intent(MainScreen.this, Utilities.class);
                startActivity(intent_utilities);
                break;

            case R.id.bug_report_menu:
                Intent intent_bug = new Intent(MainScreen.this, BugReport.class);
                startActivity(intent_bug);
                break;


            default:
        }
        return super.onOptionsItemSelected(item);
    }

}
