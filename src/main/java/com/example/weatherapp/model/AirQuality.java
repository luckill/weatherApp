package com.example.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.concurrent.*;

public class AirQuality
{
    @JsonProperty("co")
    private double co;
    @JsonProperty("no")
    private double no;
    @JsonProperty("no2")
    private double no2;
    @JsonProperty("o3")
    private double o3;
    @JsonProperty("so2")
    private double so2;
    @JsonProperty("pm2_5")
    private double pm2_5;
    @JsonProperty("pm10")
    private double pm10;

    public AirQuality()
    {

    }

    public AirQuality(double co, double no, double no2, double o3, double so2, double pm2_5, double pm10)
    {
        this.co = co;
        this.no = no;
        this.no2 = no2;
        this.o3 = o3;
        this.so2 = so2;
        this.pm2_5 = pm2_5;
        this.pm10 = pm10;
    }

    public double getCo()
    {
        return co;
    }

    public double getNo()
    {
        return no;
    }

    public double getNo2()
    {
        return no2;
    }

    public double getO3()
    {
        return o3;
    }

    public double getSo2()
    {
        return so2;
    }

    public double getPm2_5()
    {
        return pm2_5;
    }

    public double getPm10()
    {
        return pm10;
    }
}
