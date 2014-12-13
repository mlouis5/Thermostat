/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.modes.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.attributes.WeekProgram;

/**
 *
 * @author MacDerson
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Mode implements Resource{

    private final String path;
    
    protected WeekProgram week;
    
    protected Mode(String programMode) throws Exception{
        this.path = programMode;
    }

    public WeekProgram getWeek() {
        return week;
    }

    public void setWeek(WeekProgram week) {
        this.week = week;
    }
    
    @Override
    public String getResourcePath() throws Exception {
        return path;
    }
    
}
