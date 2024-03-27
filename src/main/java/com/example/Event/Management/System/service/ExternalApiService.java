package com.example.Event.Management.System.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExternalApiService {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${distance.api.url}")
    private String distanceApiUrl;

    private final RestTemplate restTemplate;
    
    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getWeather(String cityName, String date) {
        String url = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
                .queryParam("city", cityName)
                .queryParam("date", date)
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
        
    }

    public ResponseEntity<String> getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        String url = UriComponentsBuilder.fromHttpUrl(distanceApiUrl)
                .queryParam("latitude1", latitude1)
                .queryParam("longitude1", longitude1)
                .queryParam("latitude2", latitude2)
                .queryParam("longitude2", longitude2)
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
    }

    // Implement error handling as per your requirement
}
