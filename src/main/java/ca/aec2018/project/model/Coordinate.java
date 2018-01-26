package ca.aec2018.project.model;

import javax.persistence.*;

public class Coordinate {
    private Integer id;

    private Integer latitude;

    private Integer longitude;

    public Coordinate(Integer latitude, Integer longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinate(double latitude, double longitude){
        this.longitude = (int)Math.round(longitude);
        this.latitude = (int)Math.round(latitude);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public boolean sameCoord(Coordinate c){
        return (this.getLatitude() == c.getLatitude() && this.getLongitude() == c.getLongitude());
    }
}
