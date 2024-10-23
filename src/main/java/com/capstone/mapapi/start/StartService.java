package com.capstone.mapapi.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartService {

    @Autowired
    private StartRepository startRepository;

    public Start getLatestStart() {
        // 가장 최근 출발지를 찾기 위한 쿼리 메서드 호출
        return startRepository.findTopByOrderByStartIdDesc();
    }

    // Start 엔티티 저장
    public Start saveStart(Start start) {
        return startRepository.save(start); // Start 객체를 그대로 저장
    }
}
