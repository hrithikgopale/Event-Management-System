package com.example.Event.Management.System.controller;

import java.io.IOException;
import java.util.List;
import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Event.Management.System.model.Event;
import com.example.Event.Management.System.service.EventService;
@RequestMapping("/api/v1/events")
@RestController
public class EventController {

	@Autowired
	private EventService eventService;
	
	@PostMapping("/upload")
    public ResponseEntity<String> createEvents(@RequestParam("file") MultipartFile file) {
        eventService.save(file);
        
        return ResponseEntity.ok("Events saved successfully");
}

	 @GetMapping("/find")
	    public ResponseEntity<List<Event>> findEventsByLocationAndDate(
	            @RequestParam double latitude,
	            @RequestParam double longitude,
	            @RequestParam String date,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int pageSize) {

	        List<Event> events = eventService.findEventsByLocationAndDate(latitude, longitude, date, page, pageSize);
	        return ResponseEntity.ok(events);
	    }
	 @ExceptionHandler(MissingServletRequestParameterException.class)
	 public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
	     String paramName = ex.getParameterName();
	     String errorMessage = paramName + " parameter is missing";
	     return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	 }
}