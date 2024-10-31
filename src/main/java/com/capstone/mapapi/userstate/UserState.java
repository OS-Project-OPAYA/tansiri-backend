package com.capstone.mapapi.userstate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class UserState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double userLat;
    private double userLon;
    private double userDir;
    private String userID;

    // Getters and Setters
    public double getUserLat() {
        return userLat;
    }

    public void setUserLat(double userLat) {
        this.userLat = userLat;
    }

    public double getUserLon() {
        return userLon;
    }

    public void setUserLon(double userLon) {
        this.userLon = userLon;
    }

    public double getUserDir() {
        return userDir;
    }

    public void setUserDir(double userDir) {
        this.userDir = userDir;
    }

    public String getUserID() {
        return userID;
    }
}
