package com.example.weatherapp.model;

import com.fasterxml.jackson.annotation.*;

public class WeatherCondition
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("main")
    private String main;
    @JsonProperty("description")
    private String description;
    @JsonProperty("icon")
    private String icon;

    public WeatherCondition()
    {

    }

    public WeatherCondition(String id, String main, String description, String icon)
    {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getId()
    {
        return id;
    }

    public String getMain()
    {
        return main;
    }

    public String getDescription()
    {
        return description;
    }

    public String getIcon()
    {
        return icon;
    }
}
