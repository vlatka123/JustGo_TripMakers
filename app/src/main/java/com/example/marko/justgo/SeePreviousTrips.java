package com.example.marko.justgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SeePreviousTrips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_previous_trips);

        TripsDataSource tripsDataSource = new TripsDataSource(getApplicationContext());

        DataTripStorage.allTripsList = tripsDataSource.getAllTrips();

        ListView tripsListView = (ListView) findViewById(R.id.list_of_my_trips);

        tripsListView.setAdapter(new TripListAdapter(getApplicationContext()));

        tripsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TripData clickedTrip = DataTripStorage.allTripsList.get(position);

                Intent openTripIntent = new Intent(SeePreviousTrips.this, OpenSpecificTrip.class);
                openTripIntent.putExtra("opened-trip", clickedTrip.getId());

                startActivity(openTripIntent);

            }
        });

    }
}
