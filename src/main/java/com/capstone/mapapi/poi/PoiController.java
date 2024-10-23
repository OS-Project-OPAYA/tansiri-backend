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
    public ResponseEntity<Poi> searchStartPoi(@RequestBody String keyword) {
        Poi poi = poiService.searchFirstPoi(keyword, true); // 출발지 검색 시 isStart=true
        if (poi != null) {
            return ResponseEntity.ok(poi); // POI가 있을 경우 200 OK 반환
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // POI가 없을 경우 404 NOT FOUND 반환
        }
    }

    @PostMapping("/endSearch")
    public ResponseEntity<Poi> searchEndPoi(@RequestBody String keyword) {
        Poi poi = poiService.searchFirstPoi(keyword, false); // 목적지 검색

        if (poi != null && poi.getName() != null) {
            return ResponseEntity.ok(poi); // POI가 있을 경우 200 OK 반환
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // POI가 없을 경우 404 NOT FOUND 반환
        }
    }
}
