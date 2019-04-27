/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.repository;

import com.dreamteam.watchme.model.TrackingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wdrdr
 */
@Repository
public interface TrackingStatusRepository extends MongoRepository<TrackingStatus, String> {

}
