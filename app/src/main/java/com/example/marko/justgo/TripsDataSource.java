package com.example.marko.justgo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TripsDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public TripsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);

        try {
            this.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // otvori bazu za pisanje
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // zatvori bazu
    public  void close() {
        database.close();
    }

    // dodaj vrijednosti u tablicu "trips" koja se nalazi u bazi podataka
    public void addTripToDb(String country, String city, String from_d, String to_d) {
        ContentValues values = new ContentValues();
        values.put("country", country);
        values.put("city", city);
        values.put("from_date", from_d);
        values.put("to_date", to_d);
        database.insert("trips", null, values);
    }

    // Dohvati odredeno trip s ID-om "id" ako takav postoji i vrati podatke iz tog retka
    public TripData getTripById(int id) {
        TripData tripToReturn = new TripData();

        Cursor cursor = database.rawQuery("SELECT * FROM trips WHERE id = '" + String.valueOf(id) + "'", null);

        cursor.moveToFirst();

        if (! cursor.isAfterLast()) {
            tripToReturn.setId(cursor.getInt(0));
            tripToReturn.setCountry(cursor.getString(1));
            tripToReturn.setCity(cursor.getString(2));
            tripToReturn.setFrom_date(cursor.getString(3));
            tripToReturn.setTo_date(cursor.getString(4));
        }

        return tripToReturn;
    }

    // Dohvati sve trip-ove spremljene u bazu podataka
    public ArrayList<TripData> getAllTrips() {

        ArrayList<TripData> trips = new ArrayList<TripData>();

        Cursor cursor = database.rawQuery("SELECT * FROM trips", null);
        cursor.moveToFirst();

        while (! cursor.isAfterLast()) {
            TripData trip = new TripData();
            trip.setId(cursor.getInt(0));
            trip.setCountry(cursor.getString(1));
            trip.setCity(cursor.getString(2));
            trip.setFrom_date(cursor.getString(3));
            trip.setTo_date(cursor.getString(4));

            trips.add(trip);
            cursor.moveToNext();
        }

        cursor.close();
        return trips;
    }

}
