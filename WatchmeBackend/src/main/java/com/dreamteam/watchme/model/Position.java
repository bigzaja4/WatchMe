/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author wdrdr
 */
public class Position {

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lon")
    private double longitude;

  //  private String geohash;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
//
//    public String getGeohash() {
//        return geohash;
//    }
//
//    public void setGeohash(String geohash) {
//        this.geohash = geohash;
//    }

    @Override
    public String toString() {
        return "Position{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
