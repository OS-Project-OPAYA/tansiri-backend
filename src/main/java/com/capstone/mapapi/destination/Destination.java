package com.capstone.mapapi.destination;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Destination {

    // Getter 및 Setter 메서드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int destinationId;

    private String destinationName;
    private String destinationLat;
    private String destinationLon;

    // 기본 생성자
    public Destination() {}

    // 모든 필드를 포함한 생성자
    public Destination(String destinationName, String destinationLat, String destinationLon) {
        this.destinationName = destinationName;
        this.destinationLat = destinationLat;
        this.destinationLon = destinationLon;
    }


    public String getDestinationLon() {
        return destinationLon;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public String getDestinationName() {
        return destinationName;
    }
}
