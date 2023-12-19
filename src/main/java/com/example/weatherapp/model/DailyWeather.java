package com.example.weatherapp.model;

import com.example.weatherapp.Controller.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class DailyWeather extends Weather
{
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("pop")
    private double precipitationProbability;
    @JsonProperty("temp")
    private JsonNode temp;
    @JsonProperty("feels_like")
    private JsonNode feelLike;
    @JsonProperty("sunrise")
    private long sunRiseTimeStamp;
    @JsonProperty("sunset")
    private long sunSetTimeStamp;
    @JsonProperty("dt")
    private long reportTimeStamp;
    private String date;
    private String dayOfTheWeek;

    private String sunRise;
    private String sunSet;

    private int morningTemperature;
    private int dayTemperature;
    private int eveningTemperature;
    private int nightTemperature;
    private int dailyMax;
    private int dailyMin;

    private int morningFeelLikeTemperature;
    private int dayFeelLikeTemperature;
    private int eveningFeelLikeTemperature;
    private int nightFeelLikeTemperature;

    public DailyWeather()
    {

    }

    public DailyWeather(double uv, int humidity, int cloud, double windSpeed, String summary, double precipitationProbability, JsonNode temp, JsonNode feelLike)
    {
        super(uv, humidity, cloud, windSpeed);
        this.summary = summary;
        this.precipitationProbability = precipitationProbability;
        this.temp = temp;
        this.feelLike = feelLike;
        initialize(temp, feelLike);
    }

    public String getSummary()
    {
        return summary;
    }

    public double getPrecipitationProbability()
    {
        return precipitationProbability;
    }

    public int getDailyMax()
    {
        return dailyMax;
    }

    public int getDailyMin()
    {
        return dailyMin;
    }

    public int getMorningTemperature()
    {
        return morningTemperature;
    }

    public int getDayTemperature()
    {
        return dayTemperature;
    }

    public int getEveningTemperature()
    {
        return eveningTemperature;
    }

    public int getNightTemperature()
    {
        return nightTemperature;
    }

    public int getMorningFeelLikeTemperature()
    {
        return morningFeelLikeTemperature;
    }

    public int getDayFeelLikeTemperature()
    {
        return dayFeelLikeTemperature;
    }

    public int getEveningFeelLikeTemperature()
    {
        return eveningFeelLikeTemperature;
    }

    public int getNightFeelLikeTemperature()
    {
        return nightFeelLikeTemperature;
    }

    public String getSunRise()
    {
        return sunRise;
    }

    public String getSunSet()
    {
        return sunSet;
    }

    public JsonNode getTemp()
    {
        return temp;
    }

    public JsonNode getFeelLike()
    {
        return feelLike;
    }

    public String getDate()
    {
        return date;
    }

    public String getDayOfTheWeek()
    {
        return dayOfTheWeek;
    }

    public void initialize(JsonNode temp, JsonNode feelLike)
    {
        super.initialize();
        this.sunRise = convertTimeStamp(sunRiseTimeStamp, WeatherController.timeZone);
        this.sunSet = convertTimeStamp(sunSetTimeStamp, WeatherController.timeZone);

        this.morningTemperature = temp.get("morn").asInt();
        this.dayTemperature = temp.get("day").asInt();
        this.eveningTemperature = temp.get("eve").asInt();
        this.nightTemperature = temp.get("night").asInt();
        this.dailyMax = temp.get("max").asInt();
        this.dailyMin = temp.get("min").asInt();

        this.morningFeelLikeTemperature = feelLike.get("morn").asInt();
        this.dayFeelLikeTemperature = feelLike.get("day").asInt();
        this.eveningFeelLikeTemperature = feelLike.get("eve").asInt();
        this.nightFeelLikeTemperature = feelLike.get("night").asInt();
        convertTime(reportTimeStamp, WeatherController.timeZone);
    }

    public void convertTime(long timeStamp, String timeZone)
    {
        ZoneId istZone = ZoneId.of(timeZone);
        ZonedDateTime time = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timeStamp), istZone);
        LocalDate date = time.toLocalDate();
        this.dayOfTheWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        this.date = date.toString().replace("T", " ");
    }
}
