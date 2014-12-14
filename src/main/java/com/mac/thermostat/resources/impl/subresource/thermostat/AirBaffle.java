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
 * Thermostat Air Baffle<br>
 * This resource is available at:<br>
 * http://<ip-address>/tstat/air_baffle
 * 
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirBaffle extends SimpleRequester<AirBaffle> {

    @JsonIgnore
    private static final String RESOURCE = "air_baffle";
    @JsonIgnore
    private static final int MIN_BAFFLE_MODE = 1;
    @JsonIgnore
    private static final int MAX_BAFFLE_MODE = 2;
    /**
     * Description: External air baffle mode<br>
     * Request Type: GET, POST<br>
     * Data Format: Integer value:<br> 0 = External air baffle closed<br>
     * 1 = External air baffle opened temporarily<br>
     * 2 = External air baffle opened permanently (set by radio bus only)
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("baffle_mode")
    @AttributeInterpreter(key = {0, 1, 2},
            values = {ReadableValue.EXTERNAL_AIR_BAFFLE_CLOSED, ReadableValue.EXTERNAL_AIR_BAFFLE_OPENED_TEMP,
                ReadableValue.EXTERNAL_AIR_BAFFLE_OPENED_PERM})
    private int baffleMode;

    public AirBaffle() throws Exception {
        super(Thermostat.URI, AirBaffle.class, RESOURCE);
    }

    public int getBaffleMode() {
        return baffleMode;
    }

    public void setBaffleMode(int baffleMode) {
        this.baffleMode = baffleMode < MIN_BAFFLE_MODE ? MIN_BAFFLE_MODE
                : baffleMode > MAX_BAFFLE_MODE ? MAX_BAFFLE_MODE
                        : baffleMode;
    }

    @Override
    protected void doBeforeGet() {
    }

    @Override
    protected void doBeforePost() {
    }

}
