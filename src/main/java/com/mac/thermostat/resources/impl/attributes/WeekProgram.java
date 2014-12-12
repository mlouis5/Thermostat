/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mac.thermostat.resources.impl.attributes.DayProgram.DayType;
import com.mac.thermostat.resources.impl.json.deserializers.WeekDeserializer;
import com.mac.thermostat.resources.impl.json.serializers.WeekSerializer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = WeekSerializer.class)
@JsonDeserialize(using = WeekDeserializer.class)
public class WeekProgram {
    
    private final Map<DayType, DayProgram> week;
    
    public WeekProgram(){
        this.week = new HashMap();
    }
    
    public WeekProgram(Map<DayType, DayProgram> week){
        this.week = week;
    }
    
    public void putDay(DayType type, DayProgram prog){
        this.week.put(type, prog);
    }
    
    public DayProgram getProgram(DayType dayType){
        return this.week.get(dayType);
    }
    
    public Temperature getTempAtDayForTime(DayType day, Minute time){
        return this.week.get(day).getTempAtTime(time);
    }
    
    public int hash(){
        return Objects.hashCode(this.week);
    }
}
