package com.gr.grquickrescue.utils;

import com.google.code.geocoder.Geocoder; 
import com.google.code.geocoder.GeocoderRequestBuilder; 
import com.google.code.geocoder.model.GeocodeResponse; 
import com.google.code.geocoder.model.GeocoderRequest; 
import com.google.code.geocoder.model.GeocoderResult; 
import com.google.code.geocoder.model.LatLng;
import java.io.IOException; 
import java.util.List; 
 
public class GeocodingUtility { 

	public static LatLng getLocation(String place) throws IOException, LocationNotFoundException { 
		
		Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(place).getGeocoderRequest(); 
        GeocodeResponse geocodeResponse = geocoder.geocode(geocoderRequest); 
        
        List<GeocoderResult> results = geocodeResponse.getResults(); 
        if(results.size() >= 1) 
        {
        	LatLng latlng = new LatLng();
        	latlng.setLat(results.get(0).getGeometry().getLocation().getLat());
        	latlng.setLng(results.get(0).getGeometry().getLocation().getLng());
        	return latlng;
        }else 
        {
        	throw new LocationNotFoundException(place); 
        }
    } 
 
    public static class LocationNotFoundException extends Exception { 
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public LocationNotFoundException(String place) { 
            super("Unable to find location of: "+place); 
        } 
    } 
}