package com.capstone.mapapi.destination;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, String> {
    Destination findTopByOrderByDestinationIdDesc();
}
