/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.thermostat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.SimpleGetter;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Humidity extends SimpleGetter<Humidity> {
    
    @JsonIgnore
    private static final String RESOURCE = "humidity";
    /**
     * Description: Current humidity value<br>
     * Request Type: GET<br>
     * Data Format: Float value: Value is % relative humidity from 0 to 100%
     */
    @RequestType
    @JsonProperty(RESOURCE)
    private float humidity;

    public Humidity() throws Exception {
        super(Thermostat.URI, Humidity.class, RESOURCE);
    }

    public float getHumidity() {
        return humidity;
    }

    @Override
    protected void doBeforeGet() {
    }
}
