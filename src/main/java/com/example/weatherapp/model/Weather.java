package com.example.weatherapp.model;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

import java.time.*;
import java.util.*;

public class Weather
{
    @JsonProperty("uvi")
    private double uv;
    @JsonProperty("humidity")
    private int humidity;
    @JsonProperty("clouds")
    private int cloud;
    @JsonProperty("wind_speed")
    private double windSpeed;
    @JsonProperty("weather")
    private JsonNode weatherCondition;

    private String weatherConditionId;
    private String weatherConditionDescription;
    private String weatherConditionIconURL;


    public Weather()
    {

    }

    public Weather(double uv, int humidity, int cloud, double windSpeed)
    {
        this.uv = uv;
        this.humidity = humidity;
        this.cloud = cloud;
        this.windSpeed = windSpeed;
        this.weatherCondition = weatherCondition;
    }

    public double getUv()
    {
        return uv;
    }

    public int getCloud()
    {
        return cloud;
    }

    public double getWindSpeed()
    {
        return windSpeed;
    }

    public int getHumidity()
    {
        return humidity;
    }

    public String getWeatherConditionId()
    {
        return weatherConditionId;
    }

    public String getWeatherConditionDescription()
    {
        return weatherConditionDescription;
    }

    public String getWeatherConditionIconURL()
    {
        return weatherConditionIconURL;
    }


    protected void initialize()
    {
        this.weatherConditionDescription = weatherCondition.get(0).get("description").asText();
        this.weatherConditionId = weatherCondition.get(0).get("id").asText();
        this.weatherConditionIconURL = "http://openweathermap.org/img/w/" + weatherCondition.get(0).get("icon").asText() + ".png";
    }

    public String convertTimeStamp(long timeStamp, String timeZone)
    {
        ZoneId istZone = ZoneId.of(timeZone);
        ZonedDateTime time = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timeStamp), istZone);

        return time.toLocalTime().toString();
    }
}
