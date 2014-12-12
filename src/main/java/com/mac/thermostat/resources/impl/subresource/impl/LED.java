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
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
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
@RequestType(types = {RestType.POST})
public class LED implements Resource, Requestor{
    
    @JsonIgnore
    private final ResourceURI URI;
    
    public LED() throws Exception{
        URI = Thermostat.URI.clone().path("led").build();
    }
    
    /**
     * Description: Energy LED Status Code
     * Request Type: GET
     * Data Format: Integer that represents:
     * 0 – Off
     * 1 – Green
     * 2 – Yellow
     * 4 – Red
     */
    @AttributeInterpreter(key = {0, 1, 2, 4}, 
            values = {ReadableValue.OFF, ReadableValue.GREEN,
            ReadableValue.YELLOW, ReadableValue.RED})
    @RequestType(types = {RestType.POST})
    @JsonProperty("energy_led")
    private int energyLed;
    
    public int getEnergyLed() {
        return energyLed;
    }

    public void setEnergyLed(int energyLed) {
        this.energyLed = energyLed;
    }
    
    @Override
    public Resource get() throws Exception {
        return null;
    }
    
    @Override
    public LED post() throws Exception{
        RestTemplate template = new RestTemplate();
        return template.postForObject(getUriString(), this, LED.class);
    }

    @Override
    public String getUriString() throws Exception {
        return URI.getUriWithHttp();
    }

    
    
}
