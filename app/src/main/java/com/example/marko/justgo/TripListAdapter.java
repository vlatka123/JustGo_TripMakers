package com.example.marko.justgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TripListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public TripListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return DataTripStorage.allTripsList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_item_for_list_view, parent, false);
        }

        TextView idTv = (TextView) convertView.findViewById(R.id.id_trip);
        TextView titleTv = (TextView) convertView.findViewById(R.id.trip_name);

        TripData currentTrip = DataTripStorage.allTripsList.get(position);

        idTv.setText(String.valueOf(currentTrip.getId()));
        titleTv.setText(currentTrip.getCountry() + ", " + currentTrip.getCity());

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
