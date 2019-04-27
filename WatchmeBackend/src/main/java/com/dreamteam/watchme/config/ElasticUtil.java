/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.watchme.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

/**
 *
 * @author wdrdr
 */
public class ElasticUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> ArrayList<T> searchHitsToList(SearchHits searchHits, Class<T> type) {
        ArrayList<T> elasticParseToPojo = new ArrayList();

        for (SearchHit hit : searchHits) {
            elasticParseToPojo.add(objectMapper.convertValue(hit.getSourceAsMap(), type));
        }
        return elasticParseToPojo;
    }

    public static Map<String, Object> pojoToMap(Object objectParesedToMap) {
        Map<String, Object> convertValue = objectMapper.convertValue(objectParesedToMap, Map.class);
        
        HashMap<String, Long> geoPoint = new HashMap();
        ArrayList<String> removedKeyList = new ArrayList();
        convertValue.forEach((key, value) -> {
            if (value == null) {
                removedKeyList.add((String) key);
            }
            if (key == "position") {
                System.out.println("1111111111111");
            }
        });
        removedKeyList.forEach((removedKey) -> {
            convertValue.remove(removedKey);
        });
//        System.out.println(geoPoint.get("lat"));
//        System.out.println("vfsd");
//        convertValue.put("lat", geoPoint.get("lat"));
//        convertValue.put("lon", geoPoint.get("lon"));

        return convertValue;
    }

}
