package com.example.boxoffice.showtimings;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.boxoffice.R;
import com.example.boxoffice.cinemas.Cinema;

import java.util.ArrayList;

public class ShowAdapter extends ArrayAdapter<Cinema> {

    ArrayList<Cinema> cinemas;
    Context context;
    int resource;

    public ShowAdapter(Context context, int resource, ArrayList<Cinema> cinemas) {
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
        TextView mCinemaLocationView = convertView.findViewById(R.id.cinema_address);
        GridLayout gridLayout = convertView.findViewById(R.id.show_timings);
        gridLayout.removeAllViews();
        int column = 4;
        int total = cinema.getShows().size();
        int row = total / column;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);
        for (int i = 0, c = 0, r = 0; i < total; i++, c++) {
            if (c == column) {
                c = 0;
                r++;
            }
            Button button = new Button(convertView.getContext());
            button.setBackgroundColor(Color.WHITE);
            button.setText(cinema.getShows().get(r+c));
            button.setLayoutParams(new GridLayout.LayoutParams());
            gridLayout.setUseDefaultMargins(true);
            GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec colspan = GridLayout.spec(GridLayout.UNDEFINED, 1);
//            if (r == 0 && c == 0) {
//                Log.e("", "spec");
//                colspan = GridLayout.spec(GridLayout.UNDEFINED, 2);
//                rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 2);
//            }
            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
                    rowSpan, colspan);
            gridLayout.addView(button, gridParam);
        }
        mCinemaLocationView.setText(cinema.getCinemaName());
        return convertView;
    }
}
