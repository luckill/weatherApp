package com.example.weatherapp.model;

import com.fasterxml.jackson.annotation.*;

import java.time.*;

public class HourlyWeather extends Weather
{
    @JsonProperty("pop")
    private double precipitationProbability;
    @JsonProperty("temp")
    private int temperature;
    @JsonProperty("feels_like")
    private int feelLike;
    @JsonProperty("dt")
    private long reportTimeStamp;
    private String time;
    public HourlyWeather()
    {
        super();
    }

    public HourlyWeather(double uv, int humidity, int cloud, double windSpeed, double precipitationProbability, int temperature, int feelLike)
    {
        super(uv, humidity, cloud, windSpeed);
        this.precipitationProbability = precipitationProbability;
        this.temperature = temperature;
        this.feelLike = feelLike;
    }

    public double getPrecipitationProbability()
    {
        return precipitationProbability;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public int getFeelLike()
    {
        return feelLike;
    }

    public long getReportTimeStamp()
    {
        return reportTimeStamp;
    }

    public String getTime()
    {
        return time;
    }

    public void initialize()
    {
        super.initialize();
        this.time = convertTime();
    }

    private String convertTime()
    {
        long unixTime = this.reportTimeStamp;
        return LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.UTC).toString().replace("T", " ");
    }
}
