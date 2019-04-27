/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.service;

import com.dreamteam.watchme.model.TrackingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dreamteam.watchme.repository.TrackingStatusRepository;
import javax.xml.ws.http.HTTPException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author wdrdr
 */
@Service
public class TrackingStatusService {

    @Autowired
    private TrackingStatusRepository trackingStatusRepository;

    public TrackingStatus getUserTrackingDetail(String userId) {
        return trackingStatusRepository.findById(userId).get();
    }

    public TrackingStatus updateUserTrackingStatus(TrackingStatus trackingStatus) {
        TrackingStatus updatedTrackingStatus = null;
        try {
            updatedTrackingStatus = trackingStatusRepository.save(trackingStatus);
        } catch (Exception e) {
            System.out.println("===Exception===");
            System.out.println(e.getMessage());
            return trackingStatusRepository.save(trackingStatus);
        }
        throw new HTTPException(HttpStatus.GATEWAY_TIMEOUT.value());
    }
    
    

}
