/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource;

import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.attributes.Day;
import com.mac.thermostat.resources.impl.attributes.Day.DayType;
import com.mac.thermostat.resources.impl.attributes.Week;
import com.mac.thermostat.resources.impl.attributes.enums.ProgramMode;
import com.mac.thermostat.resources.impl.utilities.ConcreteResourceURI;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class Program implements Resource {

    private ResourceURI URI;

    private ProgramMode mode;
    private Week week;
    private DayType dayType;

    public Program() {
        URI = Thermostat.URI.clone().path("program");
    }

    public Program mode(ProgramMode mode) {
        if(!Objects.equals(this.mode, mode)){
            this.mode = mode;
            URI = Thermostat.URI.clone().path("program");
            URI.path(mode.name());
            
            //make call to get program by week
        }  
        return this;
    }

    public Program day(DayType day) throws Exception {
        this.dayType = day;
        return this;
    }

    public Week getWeek() {

        return null;
    }

    public Day getDay() {

        return null;
    }

    @Override
    public ResourceURI builder() {
        return new ConcreteResourceURI();
    }

    @Override
    public void setURI(ResourceURI uri) {
        this.URI = uri;
    }

    @Override
    public String getUriString() throws Exception {
        return URI.getUriWithHttp();
    }

}
