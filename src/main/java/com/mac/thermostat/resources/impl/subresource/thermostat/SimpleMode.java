/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.thermostat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.SimpleRequester;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleMode extends SimpleRequester<SimpleMode>{
    
    @JsonIgnore
    private static final String RESOURCE = "simple_mode";
    @JsonIgnore
    private static final int NORMAL_MODE = 1;
    @JsonIgnore
    private static final int SIMPLE_MODE = 2;
    /**
     * Description: Thermostat Lock Mode
     * Request Type: GET, POST
     * Data Format: Integer value:
     * 1 = normal mode
     * 2 = simple mode
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("simple_mode")
    @AttributeInterpreter(key = {1, 2}, values = {ReadableValue.NORMAL_MODE, ReadableValue.SIMPLE_MODE})
    private int simpleMode;

    public SimpleMode() throws Exception {
        super(Thermostat.URI, SimpleMode.class, RESOURCE);
    }

    public int getSimpleMode() {
        return simpleMode;
    }

    public void setSimpleMode(int simpleMode) {
        this.simpleMode = simpleMode == SIMPLE_MODE ? simpleMode : NORMAL_MODE;
    }

    @Override
    protected void doBeforeGet() {}

    @Override
    protected void doBeforePost() {}
    
}
