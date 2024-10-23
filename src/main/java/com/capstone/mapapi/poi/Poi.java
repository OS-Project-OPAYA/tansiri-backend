package com.capstone.mapapi.poi;

public class Poi {
    private String id;
    private String name;
    private String frontLat;
    private String frontLon;

    // 기본 생성자
    public Poi() {
    }

    // Getter와 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrontLat() {
        return frontLat;
    }   

    public void setFrontLat(String frontLat) {
        this.frontLat = frontLat;
    }

    public String getFrontLon() {
        return frontLon;
    }

    public void setFrontLon(String frontLon) {
        this.frontLon = frontLon;
    }
}
