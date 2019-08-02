package com.example.boxoffice.films;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.boxoffice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.boxoffice.films.FilmAdapter.FilmListener;

public class FilmAsyncTask extends AsyncTask<URL, Void, List<Film>>  {

    private Context context;
    private Activity activity;
    ProgressDialog dialog;

    public FilmAsyncTask(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    @Override
    protected List<Film> doInBackground(URL[] objects) {
        URL url = objects[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder("");
            String s = "";

            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
            }

            JSONObject object = new JSONObject(stringBuilder.toString());
            JSONArray array = object.getJSONArray("movies");
            List<Film> filmList = new ArrayList<>();
            for(int i = 0;i < array.length();i++){
                Film film = new Film();
                JSONObject jsonObject = array.getJSONObject(i);
                Integer filmId = jsonObject.getInt("movie_id");
                String filmName = jsonObject.getString("movie_name");
                String filmPoster = jsonObject.getString("movie_poster");
                String filmTrailer = jsonObject.getString("movie_trailer");
                String filmOverview = jsonObject.getString("movie_overview");
                Integer length = jsonObject.getInt("length");
                film.setFilmId(filmId);
                film.setFilmPosterUrl(filmPoster);
                film.setFilmTrailer(filmTrailer);
                film.setFilmName(filmName);
                film.setFilmOverview(filmOverview);
                film.setFilmLength(length);
                filmList.add(film);
            }

            return filmList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Film> o) {
        RecyclerView movieList;
        movieList = (RecyclerView) activity.findViewById(R.id.rv_film_list);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        movieList.setLayoutManager(layoutManager);
        movieList.setHasFixedSize(false);
        if (o != null) {
            dialog.dismiss();
            FilmAdapter adapter = new FilmAdapter(o, (FilmListener) this.activity);
            movieList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
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

    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...Please Wait");
        dialog.show();
        super.onPreExecute();
    }
}
