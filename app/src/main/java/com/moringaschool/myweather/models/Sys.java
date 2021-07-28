
package com.moringaschool.myweather.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sys {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys() {
    }

    /**
     * 
     * @param country
     * @param sunrise
     * @param sunset
     */
    public Sys(String country, Integer sunrise, Integer sunset) {
        super();
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

}
