package com.capstone.mapapi.walkroute;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class WalkRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    private String startX;
    private String startY;
    private String endX;
    private String endY;
    private String startName;
    private String endName;
    @Lob
    private String response;

    // 기본 생성자
    public WalkRoute() {
    }


    public WalkRoute(String startX, String startY, String endX, String endY, String startName, String endName, String response) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.startName = startName;
        this.endName = endName;
        this.response = response;
    }

    public String getStartX() {
        return startX;
    }

    public String getStartY() {
        return startY;
    }

    public String getResponse() {
        return response;
    }
}
