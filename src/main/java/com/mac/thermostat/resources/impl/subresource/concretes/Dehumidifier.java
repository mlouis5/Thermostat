/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.utilities.SimpleRequester;

/**
 * Thermostat Dehumidifier<br>
 * This resource is available at:<br>
 * http://<ip-address>/tstat/dehumidifier (Only available on CT80)<br>
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dehumidifier extends SimpleRequester<Dehumidifier> {

    @JsonIgnore
    private static final String RESOURCE = "dehumidifier";

    @JsonIgnore
    private static final int MIN_MODE = 0;
    @JsonIgnore
    private static final int MAX_MODE = 2;
    @JsonIgnore
    private static final int MIN_SETPOINT = 20;
    @JsonIgnore
    private static final int MAX_SETPOINT = 90;
    /**
     * Description: Dehumidifier mode <br>
     * Request Type: GET, POST<br>
     * Data Format: Integer value: 0 = off 1 = on with fan 2 = on without fan
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("mode")
    @AttributeInterpreter(key = {0, 1, 2},
            values = {ReadableValue.OFF, ReadableValue.ON_WITH_FAN,
                ReadableValue.ON_WITHOUT_FAN})
    private int mode;
    /**
     * Description: Dehumidifier setpoint<br>
     * Request Type: GET, POST<br>
     * Data Format: Integer value: Relative humidity from 20 to 90 in percentage
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("setpoint")
    private int setpoint;

    public Dehumidifier(Class<Dehumidifier> tType, String resource) throws Exception {
        super(Dehumidifier.class, RESOURCE);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode < MIN_MODE ? MIN_MODE
                : mode > MAX_MODE ? MAX_MODE : mode;
    }

    public int getSetpoint() {
        return setpoint;
    }

    public void setSetpoint(int setpoint) {
        this.setpoint = setpoint < MIN_SETPOINT ? MIN_SETPOINT
                : setpoint > MAX_SETPOINT ? MAX_SETPOINT : setpoint;
    }

    @Override
    protected void doBeforeGet() {
    }

    @Override
    protected void doBeforePost() {
    }

}
