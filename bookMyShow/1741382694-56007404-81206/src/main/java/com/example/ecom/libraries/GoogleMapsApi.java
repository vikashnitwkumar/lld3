package com.example.ecom.libraries;

import com.example.ecom.libraries.models.GLocation;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GoogleMapsApi {

    public int estimate(GLocation src, GLocation dest) {
        // Call google maps service to get the number of seconds required to travel from src to dest
        Random random = new Random();
        return random.nextInt(100000000) + 1;
    }
}
//import com.example.ecom.libraries.models.GLocation;
//import com.google.maps.GeoApiContext;
//import com.google.maps.DistanceMatrixApi;
//import com.google.maps.model.DistanceMatrix;
//import com.google.maps.model.DistanceMatrixRow;
//import com.google.maps.model.DistanceMatrixElement;
//
//public class GoogleMapsApi {
//
//    private GeoApiContext context;
//
//    public GoogleMapsApi(String apiKey) {
//        // Initialize the GeoApiContext with your Google Maps API key
//        this.context = new GeoApiContext.Builder()
//                .apiKey(apiKey)
//                .build();
//    }
//
//    public int estimate(GLocation src, GLocation dest) {
//        try {
//            // Call Google Maps Distance Matrix API using latitude and longitude
//            String[] origins = {src.getLatitude() + "," + src.getLongitude()};
//            String[] destinations = {dest.getLatitude() + "," + dest.getLongitude()};
//
//            DistanceMatrix matrix = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations).await();
//
//            DistanceMatrixRow row = matrix.rows[0];
//            DistanceMatrixElement element = row.elements[0];
//
//            // Return the duration in seconds
//            return (int) element.duration.inSeconds;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1; // Return -1 if there's an error with the API call
//        }
//    }
//}
