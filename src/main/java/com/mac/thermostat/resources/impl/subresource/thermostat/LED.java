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
import com.mac.thermostat.resources.impl.utilities.SimplePoster;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class LED extends SimplePoster<LED> {

    @JsonIgnore
    private static final String RESOURCE = "led";
    /**
     * Description: Energy LED Status Code Request Type: GET Data Format:
     * Integer that represents: 0 – Off 1 – Green 2 – Yellow 4 – Red
     */
    @AttributeInterpreter(key = {0, 1, 2, 4},
            values = {ReadableValue.OFF, ReadableValue.GREEN,
                ReadableValue.YELLOW, ReadableValue.RED})
    @RequestType(types = {RestType.POST})
    @JsonProperty("energy_led")
    private int energyLed;

    public LED() throws Exception {
        super(Thermostat.URI, LED.class, RESOURCE);
    }

    public int getEnergyLed() {
        return energyLed;
    }

    public void setEnergyLed(int energyLed) {
        this.energyLed = energyLed;
    }

    @Override
    protected void doBeforePost() {
    }
}
