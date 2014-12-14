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
public class NightLight extends SimpleRequester<NightLight> {

    @JsonIgnore
    private static final String RESOURCE = "night_light";
    @JsonIgnore
    private static final int MIN_INTENSITY = 0;
    @JsonIgnore
    private static final int MAX_INTENSITY = 4;
    /**
     * Description: Thermostat night light intensity Request Type: GET, POST
     * Data Format: Integer value: 0 = off 1 = 25% 2 = 50% 3 = 75% 4 = 100%
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("intensity")
    @AttributeInterpreter(key = {0, 1, 2, 3, 4}, values = {ReadableValue.OFF,
        ReadableValue._25, ReadableValue._50, ReadableValue._75,
        ReadableValue._100})
    private int intensity;

    public NightLight() throws Exception {
        super(Thermostat.URI, NightLight.class, RESOURCE);
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity < MIN_INTENSITY ? MIN_INTENSITY
                : intensity > MAX_INTENSITY ? MAX_INTENSITY : intensity;
    }

    @Override
    protected void doBeforeGet() {
    }

    @Override
    protected void doBeforePost() {
    }
}
