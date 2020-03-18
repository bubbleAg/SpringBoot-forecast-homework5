package com.koro.forecast.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Forecast {

    @JsonProperty("consolidated_weather")
    private List<ConsolidatedWeather> consolidatedWeather = null;
    @JsonProperty("time")
    private String time;
    @JsonProperty("title")
    private String city;
    @JsonProperty("woeid")
    private Integer cityId;

//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("consolidated_weather")
    public List<ConsolidatedWeather> getConsolidatedWeather() {
        return consolidatedWeather;
    }

    @JsonProperty("consolidated_weather")
    public void setConsolidatedWeather(List<ConsolidatedWeather> consolidatedWeather) {
        this.consolidatedWeather = consolidatedWeather;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("title")
    public String getCity() {
        return city;
    }

    @JsonProperty("title")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("woeid")
    public Integer getCityId() {
        return cityId;
    }

    @JsonProperty("woeid")
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }

}