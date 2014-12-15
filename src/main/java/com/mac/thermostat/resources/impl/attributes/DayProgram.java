/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mac.thermostat.resources.impl.json.deserializers.DayDeserializer;
import com.mac.thermostat.resources.impl.json.serializers.DaySerializer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = DaySerializer.class)
@JsonDeserialize(using = DayDeserializer.class)
public class DayProgram {

    public enum DayType {

        MONDAY(0, "MON"), TUESDAY(1, "TUE"), WEDNESDAY(2, "WED"),
        THURSDAY(3, "THU"), FRIDAY(4, "FRI"), SATURDAY(5, "SAT"),
        SUNDAY(6, "SUN");

        private final int dayVal;
        private final String abbr;

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

        public String abbreviation() {
            return abbr;
        }
    }

    @JsonIgnore
    private DayType day;

    private final Map<Minute, Temperature> values;

    public DayProgram() {
        values = new TreeMap();
    }

    public DayProgram(DayType day) {
        values = new TreeMap();
        this.day = day;
    }

    public DayProgram(DayType day, List<Minute> minutes, List<Temperature> temperatures) {
        values = new TreeMap();
        fillMap(minutes, temperatures);
    }

    private synchronized void fillMap(List<Minute> minutes, List<Temperature> temperatures) {
        int i, c;
        for (i = 0, c = minutes.size(); i < minutes.size() && temperatures.size() == c; i++) {
            values.put(minutes.get(i), temperatures.get(i));
        }
    }

    public void addToProgram(Minute minute, Temperature temperature) {
        if (Objects.nonNull(minute) && Objects.nonNull(temperature)) {
            values.put(minute, temperature);
        }
    }

    public void setDayType(DayType day) {
        this.day = day;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.day);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DayProgram other = (DayProgram) obj;
        return Objects.equals(this.day, other.day);
    }
    
}
