/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.model;

import org.elasticsearch.common.geo.GeoPoint;
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

    private String elasticTrackingStatusId;

    private String userId;

    private String username;

    private String status;

    private long heartRate;

    //lat lon
    //private GeoPoint position;
    private Position position;

    public String getTrackingStatusId() {
        return trackingStatusId;
    }

    public void setTrackingStatusId(String trackingStatusId) {
        this.trackingStatusId = trackingStatusId;
    }

    public String getElasticTrackingStatusId() {
        return elasticTrackingStatusId;
    }

    public void setElasticTrackingStatusId(String elasticTrackingStatusId) {
        this.elasticTrackingStatusId = elasticTrackingStatusId;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

//    public GeoPoint getPosition() {
//        return position;
//    }
//
//    public void setPosition(GeoPoint position) {
//        this.position = position;
//    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(long heartRate) {
        this.heartRate = heartRate;
    }

    @Override
    public String toString() {
        return "TrackingStatus{" + "trackingStatusId=" + trackingStatusId + ", elasticTrackingStatusId=" + elasticTrackingStatusId + ", userId=" + userId + ", username=" + username + ", status=" + status + ", heartRate=" + heartRate + ", position=" + position + '}';
    }

}
