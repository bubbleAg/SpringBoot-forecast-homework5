package com.koro.forecast.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.koro.forecast.model.ConsolidatedWeather;
import com.koro.forecast.model.Forecast;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/forecast", produces = MediaType.APPLICATION_JSON_VALUE)
public class ForecastController {

    static final String BASE_URL = "https://www.metaweather.com/";

    public ForecastController() {
    }

    @GetMapping("/{city}")
    public ResponseEntity<Forecast> getForecastForDay(@PathVariable String city) {
        Forecast forecastForCity = getForecast(city);
        if (forecastForCity != null) {
            setImgForEveryDay(forecastForCity);
            return new ResponseEntity(forecastForCity, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private void setImgForEveryDay(Forecast forecastForCity) {
        List<ConsolidatedWeather> list = forecastForCity.getConsolidatedWeather();
        list.forEach(weather -> weather.setImg(getWeatherIconUrl(weather.getWeatherStateAbbr())));
    }

    private String getWeatherIconUrl(String weatherStateAbbr) {
        return BASE_URL.concat("static/img/weather/").concat(weatherStateAbbr).concat(".svg");
    }

    private Forecast getForecast(String city) {
        RestTemplate restTemplate = new RestTemplate();
        Long cityId = getCityId(city);
        String url = BASE_URL.concat("api/location/").concat(cityId.toString());
        Forecast forecast = restTemplate.getForObject(url, Forecast.class);
        return forecast;
    }

    private long getCityId(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL.concat("api/location/search/?query=").concat(city);
        JsonNode cityId = restTemplate.getForObject(url, JsonNode.class).findValue("woeid");
        return cityId.asLong();
    }
}
