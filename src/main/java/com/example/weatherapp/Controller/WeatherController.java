package com.example.weatherapp.Controller;

import com.example.weatherapp.*;
import com.example.weatherapp.model.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class WeatherController
{
    public static String timeZone;
    private ApiCommunicator api = new ApiCommunicator();
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> airPollutionMap;
    private List<HourlyWeather> hourlyWeathers;
    private Location location;

    private final ApiConfiguration apiConfiguration;

    public WeatherController(ApiConfiguration apiConfiguration)
    {

        this.apiConfiguration = apiConfiguration;
    }

    @GetMapping("/")
    public String getWeatherData(Model model)
    {
        String city = "San Francisco";
        String json = api.getData(city, apiConfiguration.AuthorizeToke());
        try
        {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Location> locations = mapper.readValue(json, new TypeReference<List<Location>>()
            {
            });
            if (locations.isEmpty())
            {
                model.addAttribute("errorMessage", "Invalid city!!! - no weather data available for the city entered");
                return "index";
            }

            location = locations.get(0);
            airPollutionMap = api.getData(location.getLatitude(), location.getLongitude(), apiConfiguration.AuthorizeToke());
            AirQuality airQuality = mapper.treeToValue(mapper.readTree(airPollutionMap.get("current")).get("list").get(0).get("components"), AirQuality.class);

            json = api.getData(location.getLatitude(), location.getLongitude(), apiConfiguration.AuthorizeToke(), "metric", WeatherOption.CURRENT);
            JsonNode tree = mapper.readTree(json);
            timeZone = tree.path("timezone").asText();

            CurrentWeather currentWeather = mapper.treeToValue(tree.path("current"), CurrentWeather.class);
            currentWeather.initialize();

            List<DailyWeather> dailyWeathers = mapper.readValue(tree.path("daily").toString(), new TypeReference<List<DailyWeather>>() {});
            for (DailyWeather weather : dailyWeathers)
            {
                weather.initialize(weather.getTemp(), weather.getFeelLike());
            }

            String hourly = tree.path("hourly").toString();

            model.addAttribute("airQuality", airQuality);
            model.addAttribute("location", location);
            model.addAttribute("currentWeather", currentWeather);
            model.addAttribute("dailyWeathers", dailyWeathers);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/hour")
    public String getHourlyWeather(Model model)
    {
        try
        {
            String json = api.getData("Sacramento", apiConfiguration.AuthorizeToke());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Location> locations = mapper.readValue(json, new TypeReference<List<Location>>(){});
            location = locations.get(0);
            JsonNode tree = mapper.readTree(api.getData(location.getLatitude(), location.getLongitude(), apiConfiguration.AuthorizeToke(), "metric", WeatherOption.HOURLY)).path("hourly");
            List<HourlyWeather> hourlyWeathers = mapper.readValue(tree.toString(), new TypeReference<List<HourlyWeather>>(){});
            for (HourlyWeather weather : hourlyWeathers)
            {
                weather.initialize();
            }
            model.addAttribute("location", location);
            model.addAttribute("hourlyWeather", hourlyWeathers);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }

        return "hourlyWeather";
    }
}
