/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

/**
 *
 * @author MacDerson
 */
public class Temperature {

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
    
    public double asCelcius(){
        return (this.temp - celciusConstant) * celciusPercent;
    }
    
    public double asFarenheit(){
        return this.temp;
    }
    
    public double asKelvin(){
        return asCelcius() + kelvinConstant;
    }
    
    public static final void main(String[] args){
        Temperature temp = new Temperature(98.6);
        System.out.println(temp.asCelcius());
        System.out.println(temp.asKelvin());
    }
}
