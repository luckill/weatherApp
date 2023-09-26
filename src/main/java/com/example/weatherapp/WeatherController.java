package com.example.weatherapp;

import com.example.weatherapp.model.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import com.google.gson.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.time.*;
import java.util.*;

@Controller
public class WeatherController
{
    private ApiCommunicator api = new ApiCommunicator();
    ObjectMapper mapper = new ObjectMapper();

    private final ApiConfiguration apiConfiguration;
    public WeatherController(ApiConfiguration apiConfiguration)
    {

        this.apiConfiguration = apiConfiguration;
    }
    @GetMapping("/")
    public String getWeatherData()
    {
        String json = api.getLocationData("Sacramento", apiConfiguration.AuthorizeToke());
        JSONParser parser = new JSONParser();
        try
        {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Location> locations = mapper.readValue(json, new TypeReference<List<Location>>(){});
            Location location = locations.get(0);
            System.out.println();

            json = api.getWeatherData(location.getLatitude(), location.getLongitude(), apiConfiguration.AuthorizeToke(), "metric");
            JSONObject obj = (JSONObject) parser.parse(json);
            JSONObject currentWeather = (JSONObject) obj.get("current");
            Weather weather = mapper.readValue(currentWeather.toJSONString(), Weather.class);

            String timezone = obj.get("timezone").toString();
            long sunrise = (long)currentWeather.get("sunrise");
            long sunset = (long)currentWeather.get("sunset");
            JSONArray array = (JSONArray) obj.get("weather");
            List<WeatherCondition> conditions = mapper.readValue(json, new TypeReference<List<WeatherCondition>>(){});
            weather.setCondition(conditions.get(0));
            weather.setSunRise(convertTimeStamp(sunrise,timezone));
            weather.setSunset(convertTimeStamp(sunset,timezone));


            System.out.println();
        }
        catch (JsonProcessingException | ParseException e)
        {
            e.printStackTrace();
        }
        return "index";
    }
    private String convertTimeStamp(long timeStamp, String timeZone)
    {

        // Convert UTC to IST
        ZoneId istZone = ZoneId.of(timeZone);
        ZonedDateTime time = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timeStamp), istZone);

        return time.toLocalTime().toString();
    }



}
