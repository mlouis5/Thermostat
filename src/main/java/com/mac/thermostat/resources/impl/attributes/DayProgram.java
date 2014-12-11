/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Mac
 */
public class DayProgram {

    public enum DayType {

        MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);

        private int dayVal;

        DayType(int dayVal) {
            this.dayVal = dayVal;
        }

        public int dayValue() {
            return dayVal;
        }

        public String value() {
            return String.valueOf(dayVal);
        }
    }

    @JsonIgnore
    private DayType day;

    private Map<Minute, Temperature> values;

    public DayProgram() {
        values = new HashMap();
    }

    public DayProgram(List<Minute> minutes, List<Temperature> temperatures) {
        values = new HashMap();
        fillMap(minutes, temperatures);
    }

    private synchronized void fillMap(List<Minute> minutes, List<Temperature> temperatures) {
        int i, c;
        for (i = 0, c = minutes.size(); i < minutes.size() && temperatures.size() == c; i++) {
            values.put(minutes.get(i), temperatures.get(i));
        }
    }
    
    public DayType getDayType(){
        return day;
    }
    
    public Temperature getTempAtTime(Minute minute){
        return values.get(minute);
    }

    public Set<Entry<Minute, Temperature>> getEntries(){
        return values.entrySet();
    }
}
