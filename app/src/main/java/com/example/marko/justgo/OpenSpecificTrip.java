package com.example.marko.justgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OpenSpecificTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_specific_trip);

        TripData openedTrip = null;

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            Integer tripId = extras.getInt("opened-trip");
            openedTrip = DataTripStorage.getTripById(tripId);
        }

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
