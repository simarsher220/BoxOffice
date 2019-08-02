package com.example.boxoffice.cinemas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boxoffice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CinemaAdapter extends ArrayAdapter<Cinema> {

    ArrayList<Cinema> cinemas;
    Context context;
    int resource;

    public CinemaAdapter(Context context, int resource, ArrayList<Cinema> cinemas) {
        super(context, resource, cinemas);
        this.cinemas = cinemas;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_cinemas_layout, null, true);
        }
        Cinema cinema = getItem(position);
//        ImageView mCinemaImageView = convertView.findViewById(R.id.cinema_image);
//        TextView mCinemaNameView = convertView.findViewById(R.id.cinema_name);
        TextView mCinemaLocationView = convertView.findViewById(R.id.cinema_address);
//        TextView mCinemaNameView = convertView.findViewById(R.id.cinema_name);

//        Picasso.get().load(cinema.getLogoUrl()).into(mCinemaImageView);
//        mCinemaLocationView.setText(cinema.getAddress());
//        mCinemaNameView.setText(cinema.getCinemaName());
        return convertView;
    }
}
