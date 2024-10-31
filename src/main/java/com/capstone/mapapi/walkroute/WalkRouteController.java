package com.capstone.mapapi.walkroute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WalkRouteController {

    @Autowired
    private WalkRouteService routeService;

    // 경로 찾기 메서드
    @PostMapping("/findWalkRoute")
    public ResponseEntity<Void> findRoute(@RequestBody Map<String, String> request) {
        String userID = request.get("userID");
        // RouteService에서 findRoute 메서드를 호출하여 경로를 찾기
        routeService.findRoute(userID);
        return ResponseEntity.ok().build();
    }



    // userID 기준으로 최신 WalkRoute 조회
    @GetMapping("/getWalkRoute/{userID}")
    public ResponseEntity<WalkRoute> getWalkRoute(@PathVariable String userID) {
        try {
            WalkRoute walkRoute = routeService.getWalkRouteByUserID(userID); // userID로 최근 경로 조회

            if (walkRoute == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(walkRoute);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
