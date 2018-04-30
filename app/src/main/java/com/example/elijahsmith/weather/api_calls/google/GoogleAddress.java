package com.example.elijahsmith.weather.api_calls.google;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogleAddress {


    @SerializedName("results")
    private List<Results> resultsList;

    public List<Results> getResultsList() {
        return resultsList;
    }

    class Results{

        @SerializedName("formatted_address")
        private String formattedAddress;


        @SerializedName("geometry")
        private Geometry geometry;

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public Geometry getGeometry() {
            return geometry;
        }
         class Geometry {

            @SerializedName("location")
             private GoogleLocation mGoogleLocation;

             public GoogleLocation getmGoogleLocation() {
                 return mGoogleLocation;
             }
             class GoogleLocation {
                 @SerializedName("lat")

                 private double latitude;

                 @SerializedName("lng")

                 private double longitude;

                 public double getLatitude() {
                     return latitude;
                 }

                 public double getLongitude() {
                     return longitude;
                 }
             }
         }


    }
}
