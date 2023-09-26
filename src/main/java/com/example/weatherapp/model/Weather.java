package com.example.weatherapp.model;


import com.fasterxml.jackson.annotation.*;

import java.sql.*;
import java.util.*;

public class Weather
{
    @JsonProperty("temp")
    private int temperature;
    @JsonProperty("feels_like")
    private int feelLike;
    @JsonProperty("uvi")
    private double uv;
    @JsonProperty("clouds")
    private double cloud;
    @JsonProperty("wind_speed")
    private double windSpeed;
    private String sunRise;
    private String Sunset;
    private WeatherCondition condition;

    public Weather()
    {

    }

    public Weather(int temperature, int feelLike, double uv, double cloud, double windSpeed, Time sunRise, Time sunset)
    {
        this.temperature = temperature;
        this.feelLike = feelLike;
        this.uv = uv;
        this.cloud = cloud;
        this.windSpeed = windSpeed;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public int getFeelLike()
    {
        return feelLike;
    }

    public double getUv()
    {
        return uv;
    }

    public double getCloud()
    {
        return cloud;
    }

    public double getWindSpeed()
    {
        return windSpeed;
    }

    public String getSunRise()
    {
        return sunRise;
    }

    public String getSunset()
    {
        return Sunset;
    }

    public void setSunRise(String sunRise)
    {
        this.sunRise = sunRise;
    }

    public void setSunset(String sunset)
    {
        Sunset = sunset;
    }

    public void setCondition(WeatherCondition condition)
    {
        this.condition = condition;
    }
}
