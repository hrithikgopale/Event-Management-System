package com.example.Event.Management.System.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String city;
	    private String date;
	    private String time;
	    private Double latitude;
	    private Double longitude;
	    private String weather;
	    private double distance;

		public Event(Long id, String name, String city, String date, String time, Double latitude, Double longitude,
				String weather, double distance) {
			super();
			this.id = id;
			this.name = name;
			this.city = city;
			this.date = date;
			this.time = time;
			this.latitude = latitude;
			this.longitude = longitude;
			this.weather = weather;
			this.distance = distance;
		}
		
		public Event(Long id, String name, String city, String date, String time, Double latitude, Double longitude) {
			super();
			this.id = id;
			this.name = name;
			this.city = city;
			this.date = date;
			this.time = time;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		
		

		public Event(String name, String city, String date, String time, Double latitude, Double longitude) {
			super();
			this.name = name;
			this.city = city;
			this.date = date;
			this.time = time;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public Event() {
			// TODO Auto-generated constructor stub
		}

		public void setWeather(String weather) {
	        this.weather = weather;
	    }

	    public void setDistance(double distance) {
	        this.distance = distance;
	    }
		public String getWeather() {
			return weather;
		}
		public double getDistance() {
			return distance;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public Double getLatitude() {
			return latitude;
		}
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}
		public Double getLongitude() {
			return longitude;
		}
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}
		 public static class Builder {
		        private String weather;
		        private double distance;

		        public Builder setWeather(String weather) {
		            this.weather = weather;
		            return this;
		        }

		        public Builder setDistance(double distance) {
		            this.distance = distance;
		            return this;
		        }

		      
		    }
		
	    
	    
}
