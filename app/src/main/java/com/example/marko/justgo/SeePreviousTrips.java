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

        // Dohvati ID-ove svih spremljenih trips
        DataTripStorage.allTripsList = tripsDataSource.getAllTrips();

        // Kreiraj listu koja ce prikazivati sva spremljana putovanja
        ListView tripsListView = (ListView) findViewById(R.id.list_of_my_trips);

        // Podesi kako ce izgledati pojedini item unutar liste
        tripsListView.setAdapter(new TripListAdapter(getApplicationContext()));

        // Ako se klikne na pojedini item unutar liste orvori novi activity te proslijedi tom acitivity-u ID trip-a na koji se kliknulo
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
