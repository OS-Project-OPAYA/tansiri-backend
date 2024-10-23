package com.capstone.mapapi.destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    // 가장 최근 목적지 가져오기
    public Destination getLatestDestination() {
        // 가장 최근 목적지를 찾기 위한 쿼리 메서드 호출
        return destinationRepository.findTopByOrderByDestinationIdDesc();
    }

    public Destination saveDestination(Destination destination) {
        return destinationRepository.save(destination);
    }
}
