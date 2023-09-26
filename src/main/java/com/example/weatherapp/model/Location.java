package com.example.weatherapp.model;

import com.fasterxml.jackson.annotation.*;

public class Location
{
    @JsonProperty("name")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lon")
    private String longitude;


    public Location()
    {

    }

    public Location(String city, String state, String country,String latitude, String longitude)
    {
        this.city = city;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public String getCountry()
    {
        return country;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }
}
