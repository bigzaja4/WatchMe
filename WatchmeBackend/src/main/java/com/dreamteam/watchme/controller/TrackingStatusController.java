/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.controller;

import com.dreamteam.watchme.service.TrackingStatusService;
import com.dreamteam.watchme.model.TrackingStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author wdrdr
 */
@Controller
public class TrackingStatusController {

    @Autowired
    private TrackingStatusService trackingStatusService;

    @GetMapping("/status/{userId}")
    public ResponseEntity<TrackingStatus> getUserTrackingDetailBy(@PathVariable String userId) {
        return new ResponseEntity<TrackingStatus>(trackingStatusService.getUserTrackingDetail(userId), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<TrackingStatus>> getAllUserTrackingDetail() {
        return new ResponseEntity<List<TrackingStatus>>(trackingStatusService.getAllUserTrackingDetail(), HttpStatus.OK);
    }

    @PostMapping("/status")
    public ResponseEntity<TrackingStatus> updateUserTrackingStatus(
            @RequestParam int page, @RequestParam String contentPerPage,
            @RequestParam long longitude, @RequestParam long latitude,
            @RequestParam String radius,
            @RequestBody TrackingStatus trackingStatus) {
        return new ResponseEntity<TrackingStatus>(trackingStatusService.updateUserTrackingStatus(trackingStatus), HttpStatus.OK);
    }
    
    

}
