package com.capstone.mapapi.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Destination findTopByUserIDOrderByDestinationIdDesc(String userID);
}
