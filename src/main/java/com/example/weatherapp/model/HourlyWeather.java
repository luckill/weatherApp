package com.example.weatherapp.model;

import com.example.weatherapp.Controller.*;
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
        this.time = convertTime(reportTimeStamp, WeatherController.timeZone);
    }

    public String convertTime(long timeStamp, String timeZone)
    {
        ZoneId istZone = ZoneId.of(timeZone);
        ZonedDateTime time = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timeStamp), istZone);
        return time.toLocalDateTime().toString().replace("T", " ");
    }
}
