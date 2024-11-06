package com.capstone.mapapi.favorite;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startName;    // 출발지 이름
    private String endName;      // 도착지 이름

    private String startLat;     // 출발지 위도
    private String startLon;     // 출발지 경도
    private String endLat;       // 도착지 위도
    private String endLon;       // 도착지 경도

    @Lob
    private String response;     // 경로 응답
    private String userId;       // 사용자 ID

    // 기본 생성자
    public Favorite() {}

    // 모든 필드를 포함하는 생성자
    public Favorite(String startName, String endName, String startLat, String startLon,
                    String endLat, String endLon, String response, String userId) {

        this.startName = startName;
        this.endName = endName;
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.response = response;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getStartLon() {
        return startLon;
    }

    public void setStartLon(String startLon) {
        this.startLon = startLon;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getEndLon() {
        return endLon;
    }

    public void setEndLon(String endLon) {
        this.endLon = endLon;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}