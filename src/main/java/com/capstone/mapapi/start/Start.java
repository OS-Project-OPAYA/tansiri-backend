package com.capstone.mapapi.start;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Start {

    // Getter 및 Setter 메서드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int startId;

    private String startName;
    private String startLat;
    private String startLon;
    private String userID;

    // 기본 생성자
    public Start() {}

    // 모든 필드를 포함한 생성자
    public Start(String startName, String  startLat, String startLon, String userID) {
        this.startName = startName;
        this.startLat = startLat;
        this.startLon = startLon;
        this.userID = userID;
    }

    public String getStartLon() {
        return startLon;
    }

    public String getStartLat() {
        return startLat;
    }


    public String getStartName() {
        return startName;
    }

    public String getUserID() {
        return userID;
    }
}