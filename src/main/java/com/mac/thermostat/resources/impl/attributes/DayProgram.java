/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayProgram {

    public enum DayType {

        MONDAY(0, "MON"), TUESDAY(1, "TUE"), WEDNESDAY(2, "WED"),
        THURSDAY(3, "THU"), FRIDAY(4, "FRI"), SATURDAY(5, "SAT"),
        SUNDAY(6, "SUN");

        private int dayVal;
        private String abbr;

        DayType(int dayVal, String abbr) {
            this.dayVal = dayVal;
            this.abbr = abbr;
        }

        public int dayValue() {
            return dayVal;
        }

        public String value() {
            return String.valueOf(dayVal);
        }
        
        public String abbreviation(){
            return abbr;
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

    public DayType getDayType() {
        return day;
    }

    public Temperature getTempAtTime(Minute minute) {
        return values.get(minute);
    }

    public Set<Entry<Minute, Temperature>> getEntries() {
        return values.entrySet();
    }
}
