package com.lt.x.batch.airport;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * Value object to represent an airport.
 *
 * @author ffazil
 * @since 15/02/16
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fs",
        "iata",
        "icao",
        "name",
        "street1",
        "city",
        "cityCode",
        "countryCode",
        "countryName",
        "regionName",
        "timeZoneRegionName",
        "localTime",
        "utcOffsetHours",
        "latitude",
        "longitude",
        "elevationFeet",
        "classification",
        "active",
        "delayIndexUrl",
        "weatherUrl"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport {

    @JsonProperty("fs")
    private String fs;
    @JsonProperty("iata")
    private String iata;
    @JsonProperty("icao")
    private String icao;
    @JsonProperty("name")
    private String name;
    @JsonProperty("street1")
    private String street1;
    @JsonProperty("city")
    private String city;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("regionName")
    private String regionName;
    @JsonProperty("timeZoneRegionName")
    private String timeZoneRegionName;
    @JsonProperty("localTime")
    private String localTime;
    @JsonProperty("utcOffsetHours")
    private Double utcOffsetHours;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("elevationFeet")
    private Integer elevationFeet;
    @JsonProperty("classification")
    private Integer classification;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("delayIndexUrl")
    private String delayIndexUrl;
    @JsonProperty("weatherUrl")
    private String weatherUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}