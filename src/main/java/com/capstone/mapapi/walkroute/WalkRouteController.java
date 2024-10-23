package com.capstone.mapapi.walkroute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WalkRouteController {

    @Autowired
    private WalkRouteService routeService;

    @PostMapping("/api/route")
    public ResponseEntity<Void> findRoute() {
        // RouteService에서 findRoute 메서드를 호출하여 경로를 찾기
        routeService.findRoute();
        return ResponseEntity.ok().build();
    }

    // 최신 출발지 좌표를 가져오는 GET 메서드
    @GetMapping("/api/getRoute")
    public ResponseEntity<?> getLatestStartCoordinates() {
        try {
            WalkRoute latestWalkRoute = routeService.getLatestWalkRoute();

            if (latestWalkRoute == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No walk route found.");
            }

            Map<String, Double> coordinates = new HashMap<>();
            coordinates.put("startX", Double.parseDouble(latestWalkRoute.getStartX()));
            coordinates.put("startY", Double.parseDouble(latestWalkRoute.getStartY()));

            return ResponseEntity.ok(coordinates);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    // WalkRoute 데이터를 안드로이드로 전송하는 API
    @GetMapping("/api/getLatestWalkRoute")
    public ResponseEntity<WalkRoute> getLatestWalkRoute() {
        try {
            WalkRoute latestWalkRoute = routeService.getLatestWalkRoute();

            if (latestWalkRoute == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(latestWalkRoute); // WalkRoute 객체를 그대로 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
