package com.capstone.mapapi.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PoiController {

    @Autowired
    private PoiService poiService;

    @PostMapping("/startSearch")
    public ResponseEntity<Poi> searchStartPoi(@RequestBody Poi.SearchRequest request) {
        Poi poi = poiService.searchFirstPoi(request.getKeyword(), true, request.getUserID()); // 출발지 검색 시 isStart=true
        if (poi != null) {
            return ResponseEntity.ok(poi);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/endSearch")
    public ResponseEntity<Poi> searchEndPoi(@RequestBody Poi.SearchRequest request) {
        Poi poi = poiService.searchFirstPoi(request.getKeyword(), false, request.getUserID()); // 목적지 검색
        if (poi != null && poi.getName() != null) {
            return ResponseEntity.ok(poi);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
