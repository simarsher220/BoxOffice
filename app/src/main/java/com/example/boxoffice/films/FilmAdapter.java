package com.example.boxoffice.films;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.boxoffice.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {

    List<Film> filmList;
    FilmListener listener;

    public interface FilmListener  {
        void onMovieItemClick(Film item);
    }

    public FilmAdapter(List<Film> filmList, FilmListener listener) {
        this.filmList = filmList;
        this.listener = listener;
    }

    @Override
    public FilmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_film_layout, parent, false);
        FilmHolder movieItemHolder = new FilmHolder(view);
        return movieItemHolder;
    }

    @Override
    public void onBindViewHolder(FilmHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class FilmHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView moviePoster;
        TextView movieName;

        public FilmHolder(View itemView) {
            super(itemView);
            moviePoster = (ImageView) itemView.findViewById(R.id.film_poster);
            itemView.setOnClickListener(this);
            setIsRecyclable(false);
            movieName = itemView.findViewById(R.id.film_name);
        }

        public void bind(int position){
            Picasso.get().load(filmList.get(position).getFilmPosterUrl()).fit().into(moviePoster);
//            ImageLoader.getInstance().displayImage(filmList.get(position).getFilmPosterUrl(), moviePoster);
            movieName.setText(filmList.get(position).getFilmName());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listener.onMovieItemClick(filmList.get(position));
        }
    }

    public void setData(List<Film> films){
        this.filmList = films;
        notifyDataSetChanged();
        // where this.data is the recyclerView's dataset you are
        // setting in adapter=new Adapter(this,db.getData());
    }
}
