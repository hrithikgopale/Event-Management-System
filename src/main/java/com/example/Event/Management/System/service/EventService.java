package com.example.Event.Management.System.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Event.Management.System.model.Event;
import com.example.Event.Management.System.repository.EventRepository;
import com.example.Event.Management.System.util.DistanceCalculator;

@Service

public class EventService {	
	
@Autowired
private EventRepository eventRepository;

@Autowired
private ExternalApiService externalAPIService;


 

 
public void save(MultipartFile file) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        String line;
        List<Event> events = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(","); // Assuming CSV format with comma-separated values
            Event event = new Event();
            event.setName(data[0]); // Assuming the first column is event name
            event.setCity(data[1]); // Assuming the second column is city name
            event.setDate(data[2]); // Assuming the third column is date
            event.setTime(data[3]); // Assuming the fourth column is time
            event.setLatitude(Double.parseDouble(data[4])); // Assuming the fifth column is latitude
            event.setLongitude(Double.parseDouble(data[5])); // Assuming the sixth column is longitude
            events.add(event);
            eventRepository.saveAll(events);
        }
    } catch (IOException e) {
        // Handle file processing error
        e.printStackTrace();
        throw new RuntimeException("Failed to save events from file");
    }
}

public List<Event> findEventsByLocationAndDate(double latitude, double longitude, String date, int page, int pageSize) {

    // Calculate date range for next 14 days from the specified date
    LocalDate startDate = LocalDate.parse(date).plusDays(1); // Start from the next day
    LocalDate endDate = startDate.plusDays(13); // Add 14 days to start date

    // Fetch events from repository
    Page<Event> eventPage = eventRepository.findByDateBetweenOrderByDateAsc(Date.valueOf(startDate), Date.valueOf(endDate), PageRequest.of(page, pageSize));

    // Convert Page to List
    List<Event> events = eventPage.getContent();

    // Call ExternalAPIService to get weather information and calculate distance for each event
    events = events.stream()
            .map(event -> {
                // Call Weather API
                ResponseEntity<String> weatherResponse = externalAPIService.getWeather(event.getCity(), event.getDate());
                if (weatherResponse.getStatusCode() == HttpStatus.OK) {
                    // Parse weather information and set it to event object
                    // Assuming weatherResponse.getBody() returns weather information in JSON format
                    event.setWeather(weatherResponse.getBody());
                }

                // Calculate distance
                double distance = DistanceCalculator.calculateDistance(latitude, longitude, event.getLatitude(), event.getLongitude());
                event.setDistance(distance);

                return event;
            })
            .collect(Collectors.toList());

    return events;
}


}
