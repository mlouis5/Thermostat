/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.modes.abstracts;

import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.impl.attributes.WeekProgram;

/**
 *
 * @author MacDerson
 */
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
