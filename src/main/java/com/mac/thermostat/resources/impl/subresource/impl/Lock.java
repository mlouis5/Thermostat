/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Requestor;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lock implements Resource, Requestor<Lock>{

    @JsonIgnore
    private final ResourceURI URI;
    
    /**
     * Description: Thermostat Lock Mode
     * Request Type: POST
     * Data Format: Integer that represents:
     * 0 = lock disabled
     * 1 = partial lock
     * 2 = full lock
     * 3 = utility lock (accessible via the radio only)
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("lock_mode")
    private int lockMode;
    
    public Lock() throws Exception{
        URI = Thermostat.URI.clone().path("lock").build();
    }
    
    @Override
    public String getUriString() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lock get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getUriString(), Lock.class);
    }

    @Override
    public Lock post() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.postForObject(getUriString(), this, Lock.class);
    }
    
}
