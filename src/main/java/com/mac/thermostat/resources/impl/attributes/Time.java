/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Time {
  
    /**
     * Integer value representing the day of the week, with day 0 being Monday.
     * 6 = Sunday
     */
    @JsonProperty("day")
    private int day;
    /**
     * Integer value representing number of hours elapsed since midnight.
     */
    @JsonProperty("hours")
    private int hours;
    /**
     * Integer value representing number of minutes since start of the hour.
     */
    @JsonProperty("minutes")
    private int minutes;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
