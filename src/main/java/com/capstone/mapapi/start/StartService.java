package com.capstone.mapapi.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartService {

    @Autowired
    private StartRepository startRepository;

    // Start 엔티티 저장
    public Start saveStart(Start start) {
        return startRepository.save(start); // Start 객체를 그대로 저장
    }

    public Start getStartByUserID(String userID) {
        // 가장 최근 출발지를 찾기 위한 쿼리 메서드 호출
        return startRepository.findTopByUserIDOrderByStartIdDesc(userID);
    }

}
