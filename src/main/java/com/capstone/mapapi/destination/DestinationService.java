package com.capstone.mapapi.destination;

import com.capstone.mapapi.start.Start;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;


    public Destination saveDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    // 사용자 ID로 목적지를 가져오는 새로운 메서드
    public Destination getDestinationsByUserID(String userID) {
        return destinationRepository.findTopByUserIDOrderByDestinationIdDesc(userID);
    }

}
