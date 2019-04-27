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
import java.util.List;
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

    public List<TrackingStatus> getUserTrackingDetailByUserId(String userId) {
        System.out.println("userID : " + userId);
        System.out.println(trackingStatusRepository.findByUserId(userId));
        return trackingStatusRepository.findByUserId(userId);
    }

    public TrackingStatus updateUserTrackingStatus(TrackingStatus trackingStatus) {
        TrackingStatus updatedTrackingStatus = null;
        try {
            updatedTrackingStatus = trackingStatusRepository.save(trackingStatus);
            return trackingStatus;
        } catch (Exception e) {
            System.out.println("===Exception===");
            System.out.println(e.getMessage());
            return trackingStatusRepository.save(trackingStatus);
        }

    }

    public List<TrackingStatus> getAllUserTrackingDetail() {
        return trackingStatusRepository.findAll();
    }

}
