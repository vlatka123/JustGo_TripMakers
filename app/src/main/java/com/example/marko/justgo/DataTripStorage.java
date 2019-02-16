package com.example.marko.justgo;

import java.util.ArrayList;

public class DataTripStorage {

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
