package com.example.marko.justgo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConverterFragment extends Fragment {


    public ConverterFragment() {
        // Required empty public constructor
    }

    Double resultVal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_converter, container, false);

        final Button button = (Button) v.findViewById(R.id.button);
        final TextView text = (TextView) v.findViewById(R.id.resultView);
        final TextView moneyText = (TextView) v.findViewById(R.id.moneyText);
        final Spinner fromSpinner = (Spinner) v.findViewById(R.id.fromSpinner);
        final Spinner toSpinner = (Spinner) v.findViewById(R.id.toSpinner);
        resultVal = 0.0;
        text.setText(resultVal.toString());

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        HttpURLConnection urlConnection = null;
                        try {
                            try {
                                String mainUrl = "https://ratesapi.io/api/latest";
                                String updatedUrl = mainUrl + "?base=" + fromSpinner.getSelectedItem();
                                URL url = new URL(updatedUrl);

                                urlConnection = (HttpURLConnection) url.openConnection();

                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                BufferedReader inReader = new BufferedReader(new InputStreamReader(in));
                                String inputLine = "";
                                String fullStr = "";
                                while ((inputLine = inReader.readLine()) != null) {
                                    fullStr += inputLine;
                                }

                                JSONObject jsonObj = new JSONObject(fullStr);
                                JSONObject result = jsonObj.getJSONObject("rates");


                                Double moneyValue = Double.valueOf(moneyText.getText().toString());

                                if (fromSpinner.getSelectedItem().equals(toSpinner.getSelectedItem())) {
                                    resultVal = moneyValue;
                                } else {
                                    Double rateValue = Double.valueOf(result.getString((String) toSpinner.getSelectedItem()));
                                    Double resultValue = moneyValue * rateValue;
                                    resultVal = resultValue;
                                }
                            } finally {
                                if (urlConnection != null)
                                    urlConnection.disconnect();
                            }


                        } catch (NumberFormatException e) {
                            //TODO: Alertbox ekle

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

                thread.start();
                try {
                    thread.join();
                    text.setText(resultVal.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        return v;
    }



}
