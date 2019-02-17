package com.example.marko.justgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OpenSpecificTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_specific_trip);

        // Initialization variable u koju cemo spremiti vrijednosti trips te ih postaviti u TextView polja da ih korisnik moze vidjeti
        TripData openedTrip = null;

        // Dohvati sto je activity koji je pozvao ovaj activity proslijedio od podataka
        Bundle extras = getIntent().getExtras();

        // Provjeri jeli ista poslao pozivajuci activity
        if(extras != null) {
            Integer tripId = extras.getInt("opened-trip");
            openedTrip = DataTripStorage.getTripById(tripId);
        }

        // Provjeri jeli postoji trip s odgovarajucim id-om, ako postoji postavi vrijednosti u odgovarajuca TextView polja
        if (openedTrip != null) {

            TextView countryView = (TextView) findViewById(R.id.setCountry);
            TextView cityView = (TextView) findViewById(R.id.setCity);
            TextView fromDate = (TextView) findViewById(R.id.setFrom);
            TextView toDate = (TextView) findViewById(R.id.setTo);

            countryView.setText(openedTrip.getCountry());
            cityView.setText(openedTrip.getCity());
            fromDate.setText(openedTrip.getFrom_date());
            toDate.setText(openedTrip.getTo_date());
        }

    }
}
