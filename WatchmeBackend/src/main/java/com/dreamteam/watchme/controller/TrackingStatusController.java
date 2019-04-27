/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.controller;

import com.dreamteam.watchme.service.TrackingStatusService;
import com.dreamteam.watchme.model.TrackingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author wdrdr
 */
@Controller
public class TrackingStatusController {

    @Autowired
    private TrackingStatusService trackingStatusService;

    @GetMapping("/signal/{userId}")
    public ResponseEntity<TrackingStatus> getUserTrackingDetailBy(@PathVariable String userId) {
        return new ResponseEntity<TrackingStatus>(trackingStatusService.getUserTrackingDetail(userId), HttpStatus.OK);
    }
    
    

}
