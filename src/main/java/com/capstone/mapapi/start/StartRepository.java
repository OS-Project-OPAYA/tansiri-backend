package com.capstone.mapapi.start;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartRepository extends JpaRepository<Start, Integer> {
    Start findTopByUserIDOrderByStartIdDesc(String userID);
}
