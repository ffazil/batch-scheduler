package com.lt.x.batch.airport;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "flightStats.api")
public class FlightStatsAPIProperties {

    private String baseUrl;
    private String activeAirportsUri;
    private String allAirportsUri;
    private String appId;
    private String appKey;


    public String getAuthenticatedActiveAirportsUri(){
        StringBuilder activeAirports = new StringBuilder();
        activeAirports.append(baseUrl);
        activeAirports.append(activeAirportsUri);
        activeAirports.append("?");
        activeAirports.append("appId");
        activeAirports.append("=");
        activeAirports.append(appId);
        activeAirports.append("&");
        activeAirports.append("appKey");
        activeAirports.append("=");
        activeAirports.append(appKey);

        return activeAirports.toString();
    }

    public String getAuthenticatedAllAirportsUri(){
        StringBuilder allAirports = new StringBuilder();
        allAirports.append(baseUrl);
        allAirports.append(allAirportsUri);
        allAirports.append("?");
        allAirports.append("appId");
        allAirports.append("=");
        allAirports.append(appId);
        allAirports.append("&");
        allAirports.append("appKey");
        allAirports.append("=");
        allAirports.append(appKey);

        return allAirports.toString();
    }
}
