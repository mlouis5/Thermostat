/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

/**
 *
 * @author Mac
 */
public class Time {
  
    /**
     * Integer value representing the day of the week, with day 0 being Monday.
     * 6 = Sunday
     */
    private int day;
    /**
     * Integer value representing number of hours elapsed since midnight.
     */
    private int hours;
    /**
     * Integer value representing number of minutes since start of the hour.
     */
    private int minutes;
}
