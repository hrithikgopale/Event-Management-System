package com.example.Event.Management.System.util;

public class DistanceCalculator {
	
	 
	        // Implement distance calculation using Haversine formula or a library
	        // ...
		private static final int EARTH_RADIUS = 6371; // Radius of the earth in km
	    
	    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
	        double dLat = Math.toRadians(lat2 - lat1);
	        double dLon = Math.toRadians(lon2 - lon1);
	        
	        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	        		
	                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
	        
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        
	        double distance = EARTH_RADIUS * c;
	        
	        return distance;
	    }
	    
}
