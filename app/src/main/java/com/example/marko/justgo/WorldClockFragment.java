package com.example.marko.justgo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorldClockFragment extends Fragment {


    public WorldClockFragment() {
        // Required empty public constructor
    }


    Calendar current;
    Spinner spinner;
    TextView txtTimeZoneTime,timezone,txtCurrentTime;
    long miliSeconds;
    ArrayAdapter<String> idAdapter;
    SimpleDateFormat sdf;
    Date resultDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_world_clock, container, false);

        //v.findViewById umisto bez v
        spinner = (Spinner)v.findViewById(R.id.spinner);
        timezone = (TextView)v.findViewById(R.id.timezone);
        txtCurrentTime = (TextView)v.findViewById(R.id.txtCurrentTime);
        txtTimeZoneTime = (TextView)v.findViewById(R.id.txtTimeZoneTime);

        String[]  idArray = TimeZone.getAvailableIDs();
        sdf = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss");

        //v.getContex() umisto this
        idAdapter = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_spinner_item,idArray);

        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(idAdapter);
        getGMTtime();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getGMTtime();
                String selectedID = (String)(parent.getItemAtPosition(position));

                TimeZone timeZone = TimeZone.getTimeZone(selectedID);
                String TimeZoneName = timeZone.getDisplayName();

                int TimeZoneOffset = timeZone.getRawOffset() / (60 * 1000);

                int hrs = TimeZoneOffset / 60;
                int mins =TimeZoneOffset % 60;

                miliSeconds = miliSeconds + timeZone.getRawOffset();

                resultDate = new Date(miliSeconds);
                System.out.println(sdf.format(resultDate));

                timezone.setText(TimeZoneName + " : GMT " + hrs + ":"+ mins);

                txtTimeZoneTime.setText("" + sdf.format(resultDate));
                miliSeconds = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;

    }

    private void getGMTtime() {
        current = Calendar.getInstance();
        txtCurrentTime.setText("" + current.getTime());

        miliSeconds = current.getTimeInMillis();
        TimeZone tzCurrent = current.getTimeZone();
        int offset = tzCurrent.getRawOffset();
        if (tzCurrent.inDaylightTime(new Date())){

            offset = offset + tzCurrent.getDSTSavings();
        }
        miliSeconds = miliSeconds - offset;
        resultDate = new Date(miliSeconds);
        System.out.println(sdf.format(resultDate));

    }

}