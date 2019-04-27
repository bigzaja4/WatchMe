/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.service;

import com.dreamteam.watchme.config.ElasticUtil;
import com.dreamteam.watchme.model.TrackingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dreamteam.watchme.repository.TrackingStatusRepository;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.http.HTTPException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.http.HttpStatus;

/**
 *
 * @author wdrdr
 */
@Service
public class TrackingStatusService {

    @Autowired
    private RestHighLevelClient elasticClient;

    private final String index = "tracking_status";

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
    

    public List<TrackingStatus> findTrackingStatusByUsingFilter(String[] eventTags, boolean isRecently, String eventDetail) throws IOException {
        BoolQueryBuilder queryFilter = new BoolQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest(index);
        SearchResponse searchResponse = null;

        System.out.println("--- Start Filtering ---");
        if (eventTags != null) {
            System.out.println("Event Tag");
            queryFilter = filterByTrackingStatusTags(queryFilter, eventTags);
        }
        if (eventDetail != null) {
            System.out.println("TrackingStatus Detail");
            queryFilter.must(filterByTrackingStatusDetail(eventDetail));
        }
        
        if (isRecently == true) {
            System.out.println("Recently");
            searchSourceBuilder = filterByRecently(searchSourceBuilder, "createEventDate");
        }

        searchSourceBuilder.query(queryFilter);
        searchRequest.source(searchSourceBuilder);
        searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);

        return ElasticUtil.searchHitsToList(searchResponse.getHits(), TrackingStatus.class);
    }

    public List<TrackingStatus> findAllTrackingStatusInElastic() throws IOException {
        System.out.println("find all event");
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);

        return ElasticUtil.searchHitsToList(searchResponse.getHits(), TrackingStatus.class);
    }

    public BoolQueryBuilder filterByTrackingStatusTags(BoolQueryBuilder queryFilter, String eventTags[]) {
        queryFilter.filter(QueryBuilders.termsQuery("eventTags", eventTags));
        return queryFilter;
    }

    public MatchQueryBuilder filterByTrackingStatusDetail(String eventDetail) {
        MatchQueryBuilder alreadyFilterByEventDetail = QueryBuilders.matchQuery("eventDetail", eventDetail);
        return alreadyFilterByEventDetail;
    }

    public SearchSourceBuilder filterByRecently(SearchSourceBuilder searchSourceBuilder, String sortedField) throws IOException {
        searchSourceBuilder.sort(sortedField, SortOrder.DESC);
        return searchSourceBuilder;
    }

}
