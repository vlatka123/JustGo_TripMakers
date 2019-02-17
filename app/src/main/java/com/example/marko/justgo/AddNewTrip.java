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
import android.widget.EditText;
import android.widget.Toast;

public class AddNewTrip extends AppCompatActivity {

    Button saveTripData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip);

        // Interface koji ce nam posluziti za spremanje podataka u tablicu
        final TripsDataSource myDs = new TripsDataSource(getApplicationContext());
        myDs.open();

        // Polja koja korisnik popunjava, a ti se podaci spremaju u bazu podataka
        final EditText drzava = (EditText) findViewById(R.id.Enter_county);
        final EditText grad = (EditText) findViewById(R.id.Enter_city);
        final EditText od_datuma = (EditText) findViewById(R.id.From_date);
        final EditText do_datuma = (EditText) findViewById(R.id.To_date);

        // Botun koji ce pokrenuti spremanje podataka u bazu podataka kada se na njega klikne
        saveTripData = (Button) findViewById(R.id.Save_trip);

        saveTripData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDs.addTripToDb(drzava.getText().toString(), grad.getText().toString(),
                        od_datuma.getText().toString(), do_datuma.getText().toString());

                Toast.makeText(getApplicationContext(), "New trip added: " + drzava.getText().toString(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddNewTrip.this, MyTrip.class);
                startActivity(intent);
            }
        });

    }


}
