/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.modes.abstracts;

import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.impl.attributes.DayProgram;
import com.mac.thermostat.resources.impl.attributes.DayProgram.DayType;


/**
 *
 * @author MacDerson
 */
public abstract class Day implements Resource {    
    
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
