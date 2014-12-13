/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.utilities.SimpleRequester;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class TempSwing extends SimpleRequester<TempSwing> {

    @JsonIgnore
    private static final String RESOURCE = "tswing";
    @JsonIgnore
    private static final float MIN_SWING = .5f;
    @JsonIgnore
    private static final float MAX_SWING = 3f;
    /**
     * Description: Thermostat temperature swing<br>
     * Request Type: GET, POST<br>
     * Data Format: Float value 0.5 degree increments with a valid<br>
     * temperature range from 0.5 to 3.0 degrees F.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("RESOURCE")
    private float tSwing;

    public TempSwing() throws Exception {
        super(TempSwing.class, RESOURCE);
    }

    public float gettSwing() {
        return tSwing;
    }

    public void settSwing(float tSwing) {
        this.tSwing = tSwing < MIN_SWING ? MIN_SWING
                : tSwing > MAX_SWING ? MAX_SWING : tSwing;
    }

    @Override
    protected void doBeforeGet() {}

    @Override
    protected void doBeforePost() {}
}
