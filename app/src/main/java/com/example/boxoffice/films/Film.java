package com.example.boxoffice.films;

import java.io.Serializable;

public class Film implements Serializable {

    private Integer filmId;
    private String filmName;
    private String filmTrailer;
    private String filmPosterUrl;
    private Integer filmLength;
    private String filmOverview;


    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmTrailer() {
        return filmTrailer;
    }

    public void setFilmTrailer(String filmTrailer) {
        this.filmTrailer = filmTrailer;
    }

    public String getFilmPosterUrl() {
        return filmPosterUrl;
    }

    public void setFilmPosterUrl(String filmPosterUrl) {
        this.filmPosterUrl = filmPosterUrl;
    }

    public Integer getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(Integer filmLength) {
        this.filmLength = filmLength;
    }

    public String getFilmOverview() {
        return filmOverview;
    }

    public void setFilmOverview(String filmOverview) {
        this.filmOverview = filmOverview;
    }
}
