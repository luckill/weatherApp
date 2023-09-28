package com.example.weatherapp.model;

import com.example.weatherapp.Controller.*;
import com.fasterxml.jackson.annotation.*;

public class CurrentWeather extends Weather
{
    @JsonProperty("temp")
    private int temperature;
    @JsonProperty("feels_like")
    private int feelLike;
    @JsonProperty("sunrise")
    private long sunRiseTimeStamp;
    @JsonProperty("sunset")
    private long sunSetTimeStamp;

    private String sunRise;
    private String sunSet;

    public CurrentWeather()
    {
        super();
    }

    public CurrentWeather(double uv, int humidity, int cloud, double windSpeed, int temperature, int feelLike)
    {
        super(uv, humidity, cloud, windSpeed);
        this.temperature = temperature;
        this.feelLike = feelLike;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public int getFeelLike()
    {
        return feelLike;
    }

    public String getSunRise()
    {
        return sunRise;
    }

    public String getSunSet()
    {
        return sunSet;
    }

    public void initialize()
    {
        super.initialize();
        this.sunRise = convertTimeStamp(sunRiseTimeStamp, WeatherController.timeZone);
        this.sunSet = convertTimeStamp(sunSetTimeStamp, WeatherController.timeZone);
    }

}
