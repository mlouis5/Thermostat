/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.modes.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.attributes.DayProgram;
import com.mac.thermostat.resources.impl.attributes.DayProgram.DayType;


/**
 *
 * @author MacDerson
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Day implements Resource {    
    
    @JsonIgnore
    private final String path;
    
    protected DayProgram dayProgram;
    
    protected Day(DayType resourcePath){
        this.path = resourcePath.abbreviation().toLowerCase();
    }
    
    public DayProgram getDayProgram(){
        return dayProgram;
    }

    public void setDayProgram(DayProgram dayProgram) {
        this.dayProgram = dayProgram;
    }
    
    @Override
    public String getResourcePath() throws Exception {
        return path;
    }
}
