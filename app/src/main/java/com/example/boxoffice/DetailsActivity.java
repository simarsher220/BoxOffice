package com.example.boxoffice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boxoffice.films.Film;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ImageView poster;
    private TextView filmTitle;
    private TextView filmReleaseDate;
    private TextView filmOverview;
    private ImageButton filmTrailer;
    private Button bookTickets;
    private Film item;
    private String location;

    private static final String API_URL = "https://localhost:8080/movie/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        poster = findViewById(R.id.film_big_poster);
        filmTitle = findViewById(R.id.film_title);
        filmReleaseDate = findViewById(R.id.film_release_date);
        filmOverview = findViewById(R.id.film_overview);
        filmTrailer = findViewById(R.id.film_trailer);
        bookTickets = findViewById(R.id.book_tickets);

        Intent i = getIntent();
        if (i.resolveActivity(getPackageManager()) != null){
            item = (Film) i.getSerializableExtra(Intent.EXTRA_TEXT);
            location = i.getStringExtra("location");
            filmTitle.setText(item.getFilmName());
            filmReleaseDate.setText(item.getFilmLength() + " minutes");
            filmOverview.setText(item.getFilmOverview());
            Picasso.get().load(item.getFilmPosterUrl()).into(poster);
        }

        bookTickets.setOnClickListener(this);
        filmTrailer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.book_tickets) {
            Intent i = new Intent(DetailsActivity.this, CinemasActivity.class);
            i.putExtra(Intent.EXTRA_TEXT, item);
            i.putExtra("location", location);
            startActivity(i);
        }
        else if(view.getId() == R.id.film_trailer) {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getFilmTrailer()));
            this.startActivity(appIntent);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
