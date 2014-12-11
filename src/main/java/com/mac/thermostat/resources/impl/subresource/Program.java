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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mac
 */
public class Program implements Resource {

    private static final ResourceURI URI;
    
    private ProgramMode mode;
    private Week week;
    private static final String dayResource = "day";
    private Day day;
    private DayType dayType;

    static {
        URI = Thermostat.URI.clone().path("program");
    }
    
    public Program mode(ProgramMode mode){
        this.mode = mode;
        URI.path(mode.name());
        return this;
    }
    
    public Program day(DayType day) throws Exception{
        this.dayType = day;
        URI.path(dayResource);
        URI.build();
        return this;
    }
    
    public Program get(){
        //implement rest call to retrieve data for this program.
        return this;
    }

    @Override
    public ResourceURI builder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setURI(ResourceURI uri) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUriString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
