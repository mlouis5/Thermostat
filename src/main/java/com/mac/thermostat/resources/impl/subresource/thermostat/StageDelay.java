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
public class StageDelay extends SimpleRequester<StageDelay> {

    @JsonIgnore
    private static final String RESOURCE = "stage_delay";
    @JsonIgnore
    private static final int MIN_DELAY = 0;
    @JsonIgnore
    private static final int MAX_DELAY = 60;
    /**
     * Description: Thermostat stage to stage delay<br>
     * Request Type: GET, POST<br>
     * Data Format: Integer value: 0 to 60 minutes
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty(RESOURCE)
    private int stageDelay;

    public StageDelay() throws Exception {
        super(Thermostat.URI, StageDelay.class, RESOURCE);
    }

    public int getStageDelay() {
        return stageDelay;
    }

    public void setStageDelay(int stageDelay) {
        this.stageDelay = stageDelay < MIN_DELAY ? MIN_DELAY
                : stageDelay > MAX_DELAY ? MAX_DELAY : stageDelay;
    }

    @Override
    protected void doBeforeGet() {}

    @Override
    protected void doBeforePost() {}
}
