
package com.moringaschool.myweather.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Coord {

    @SerializedName("lon")
    @Expose
    private Integer lon;
    @SerializedName("lat")
    @Expose
    private Integer lat;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coord() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public Coord(Integer lon, Integer lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

}
