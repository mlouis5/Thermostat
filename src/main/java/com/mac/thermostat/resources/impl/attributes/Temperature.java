/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

/**
 *
 * @author MacDerson
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature implements Comparable<Temperature>{

    private static final double celciusPercent = .5556;
    private static final int celciusConstant = 32;
    private static final int kelvinConstant = 273;
    
    private double temp;
    
    public Temperature(){
        this.temp = 0;
    }
    
    public Temperature(double temp){
        this.temp = temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
    
    public double asCelcius(){
        return (this.temp - celciusConstant) * celciusPercent;
    }
    
    public double asFarenheit(){
        return this.temp;
    }
    
    public double asKelvin(){
        return asCelcius() + kelvinConstant;
    }

    @Override
    public int compareTo(Temperature o) {
        if(Objects.isNull(o)){
            return -1;
        }
        return  Objects.equals(temp, o.temp) ? 0 : ((temp - o.temp) < 0) ? -1 : 1;
    }
}
