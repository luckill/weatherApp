package com.example.weatherapp;

import org.springframework.boot.context.properties.*;

@ConfigurationProperties("api")
public record ApiConfiguration(String AuthorizeToke)
{

}