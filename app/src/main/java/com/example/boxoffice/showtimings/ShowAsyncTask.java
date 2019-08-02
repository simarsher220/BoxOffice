package com.example.boxoffice.showtimings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.boxoffice.R;
import com.example.boxoffice.cinemas.Cinema;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShowAsyncTask extends AsyncTask<String, Void, List<Cinema>> {

    private Context context;
    private Activity activity;
    ProgressDialog dialog;

    public ShowAsyncTask(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }

    @Override
    protected List<Cinema> doInBackground(String... lists) {
        List<Cinema> cinemas = new ArrayList<>();
        try {
            URL url = new URL(lists[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String s = "";
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
            }
            JSONObject object = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = object.getJSONArray("theatres");
            for (int i=0;i<jsonArray.length();i++) {
                Cinema cinema = new Cinema();
                List<String> cinemaTimings = new ArrayList<>();
//                String city = jsonArray.getJSONObject(i).getString("city");
                String cinemaName = jsonArray.getJSONObject(i).getString("theatre_name");
                String address = jsonArray.getJSONObject(i).getString("theatre_location");
//                Integer pincode = jsonArray.getJSONObject(i).getInt("pincode");
                Integer id = jsonArray.getJSONObject(i).getInt("theatre_id");
//                cinema.setCity(city);
                cinema.setAddress(address);
//                cinema.setPincode(pincode);
                cinema.setCinemaName(cinemaName);
                cinema.setCinemaId(id);
                JSONArray showsArray = jsonArray.getJSONObject(i).getJSONArray("shows");
                if (showsArray.length() == 0) {
                    continue;
                }
                for (int j=0;j<showsArray.length();j++) {
                    cinemaTimings.add(String.valueOf(showsArray.getJSONObject(j).get("time")));
                }
                cinema.setShows(cinemaTimings);
                cinemas.add(cinema);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return cinemas;
    }

    @Override
    protected void onPostExecute(List<Cinema> cinemas) {

        ListView movieList;
        movieList = activity.findViewById(R.id.cinemas);
        if (cinemas != null) {
//            dialog.dismiss();
            ShowAdapter adapter = new ShowAdapter(context, R.layout.custom_cinemas_layout, (ArrayList<Cinema>) cinemas);
            movieList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }else {
            dialog.dismiss();
            movieList.setVisibility(View.INVISIBLE);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setMessage("Failed To Load Results");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    activity.finish();
                }
            });
            alertDialog.show();
        }
        super.onPostExecute(cinemas);
    }

    @Override
    protected void onPreExecute() {
//        dialog = new ProgressDialog(context);
//        dialog.setMessage("Loading...Please Wait");
//        dialog.show();
        super.onPreExecute();
    }
}
