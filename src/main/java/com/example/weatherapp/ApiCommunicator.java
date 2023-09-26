package com.example.weatherapp;

import org.springframework.http.*;
import org.springframework.web.client.*;

public class ApiCommunicator
{
    public String getWeatherData(String latitude, String longitude, String key, String unit)
    {
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=" + latitude + "&lon=" + longitude + "&exclude=minutely&units=" + unit + "&appid=" + key;
        return getData(url, key);
    }

    public String getLocationData(String city, String key)
    {
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=1&appid=" + key;
        return getData(url, key);
    }

    private String getData(String url, String key)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", key);
        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return response.getBody();
    }
}