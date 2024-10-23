package com.capstone.mapapi.walkroute;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkRouteRepository extends JpaRepository<WalkRoute, Long> {
    WalkRoute findTopByOrderByIdDesc(); // ID 기준으로 내림차순 정렬하여 첫 번째 결과 반환
}
