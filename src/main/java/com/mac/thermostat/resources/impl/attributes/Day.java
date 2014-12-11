/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mac
 */
public class Day {
    public enum DayType{
        MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);
        
        private int dayVal;
        
        DayType(int dayVal){
            this.dayVal = dayVal;
        }
        
        public int dayValue(){
            return dayVal;
        }
        
        public String value(){
            return String.valueOf(dayVal);
        }
    }
    
    @JsonIgnore
    private DayType day;
    
    private Map<Integer, Double> values;
}
