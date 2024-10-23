package com.capstone.mapapi.start;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StartRepository extends JpaRepository<Start, String> {
    Start findTopByOrderByStartIdDesc();
}
