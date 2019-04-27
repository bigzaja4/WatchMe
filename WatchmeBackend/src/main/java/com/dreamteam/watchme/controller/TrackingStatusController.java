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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wdrdr
 */
@CrossOrigin(origins = "*")
@RestController
public class TrackingStatusController {

    @Autowired
    private TrackingStatusService trackingStatusService;

    @GetMapping("/status/{userId}")
    public ResponseEntity<List<TrackingStatus>> getUserTrackingDetailByUserId(@PathVariable String userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int contentPerPage,
            @RequestParam(required = false, defaultValue = "0.0") double longitude,
            @RequestParam(required = false, defaultValue = "0.0") double latitude,
            @RequestParam(required = false, defaultValue = "0km") String radius) {
        return new ResponseEntity<List<TrackingStatus>>(trackingStatusService.getUserTrackingDetailByUserId(userId,page, contentPerPage,longitude,latitude,radius),
                HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<TrackingStatus>> getAllUserTrackingDetail() {
        return new ResponseEntity<List<TrackingStatus>>(trackingStatusService.getAllUserTrackingDetail(), HttpStatus.OK);
    }

    @PostMapping("/status")
    public ResponseEntity<TrackingStatus> updateUserTrackingStatus(@RequestBody TrackingStatus trackingStatus) {
        System.out.println("----");
        System.out.println(trackingStatus);
        System.out.println("----");
        return new ResponseEntity<TrackingStatus>(trackingStatusService.updateUserTrackingStatus(trackingStatus),
                HttpStatus.OK);
    }

}
