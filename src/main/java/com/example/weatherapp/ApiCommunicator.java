package com.example.weatherapp;

import com.example.weatherapp.model.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.util.*;

public class ApiCommunicator
{
    public String getData(String latitude, String longitude, String key, String unit, WeatherOption option)
    {
        String url;
        if(option == WeatherOption.HOURLY)
        {
            url = "https://api.openweathermap.org/data/3.0/onecall?lat=" + latitude + "&lon=" + longitude + "&exclude=minutely,daily,current&units=" + unit + "&appid=" + key;

        }
        else
        {
            url = "https://api.openweathermap.org/data/3.0/onecall?lat=" + latitude + "&lon=" + longitude + "&exclude=minutely&units=" + unit + "&appid=" + key;
        }
        return getDataFromAPI(url, key);
    }

    public String getData(String city, String key)
    {
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=1&appid=" + key;
        return getDataFromAPI(url, key);
    }

    public Map<String, String> getData(String latitude, String longitude, String key)
    {
        Map<String, String> airPollutionMap = new HashMap<>();
        String url = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + latitude + "&lon=" + longitude + "&appid=" + key;
        airPollutionMap.put("current", getDataFromAPI(url, key));
        return airPollutionMap;
    }

    private String getDataFromAPI(String url, String key)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", key);
        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return response.getBody();
    }
}