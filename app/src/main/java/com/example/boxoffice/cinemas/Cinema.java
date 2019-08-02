package com.example.boxoffice.cinemas;

import com.example.boxoffice.showtimings.Show;

import java.util.List;

public class Cinema {

    private Integer cinemaId;
    private String cinemaName;
    private String address;
    private Integer pincode;
    private String city;
    private List<String> shows;

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getShows() {
        return shows;
    }

    public void setShows(List<String> shows) {
        this.shows = shows;
    }
}
