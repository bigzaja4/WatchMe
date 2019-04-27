/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
@Document(collection = "trackingstatus")
public class TrackingStatus {

    @Id
    private String trackingStatusId;

    private String userId;

    private String username;

    private long latitude;

    private long longitude;

    public String getTrackingStatusId() {
        return trackingStatusId;
    }

    public void setTrackingStatusId(String trackingStatusId) {
        this.trackingStatusId = trackingStatusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "TrackingStatus{" + "trackingStatusId=" + trackingStatusId + ", userId=" + userId + ", username=" + username + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
