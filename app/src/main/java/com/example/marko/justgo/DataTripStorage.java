package com.example.marko.justgo;

import java.util.ArrayList;

public class DataTripStorage {

    // Dohvati sve ID-ove Trips-a i spremi ih u ArrayList tip podatka
    public static ArrayList<TripData> allTripsList = new ArrayList<TripData>();

    public static TripData getTripById (Integer id) {

        for (int i = 0; i < allTripsList.size(); i++) {
            if (allTripsList.get(i).getId() == id) {
                return allTripsList.get(i);
            }
        }

        return null;
    }

}
