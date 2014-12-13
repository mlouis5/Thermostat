/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.modes.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Getter;
import com.mac.thermostat.resources.Poster;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TempDifferential implements Resource,
        Getter<TempDifferential>, Poster<TempDifferential> {

    @JsonIgnore
    private static final float MIN_DIFF = 2f;
    @JsonIgnore
    private static final float MAX_DIFF = 6f;
    @JsonIgnore
    private final String path;

    /**
     * Description: Thermostat temperature differential Request Type: GET, POST
     * Data Format: Float value 1.0 degree increments with a valid temperature
     * range from 2.0 to 6.0 degrees F.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("temp_diff")
    private float tempDiff;

    public TempDifferential(String resource) throws Exception {
        this.path = Thermostat.URI.clone().path(resource).build().getUriWithHttp();
        tempDiff = MIN_DIFF;
    }

    public float getTempDiff() {
        return tempDiff;
    }

    public void incrementTempDiff() {
        this.tempDiff = tempDiff < MAX_DIFF ? tempDiff++ : tempDiff;
    }

    public void decrementTempDiff() {
        this.tempDiff = tempDiff > MIN_DIFF ? tempDiff-- : tempDiff;
    }

    @Override
    public String getResourcePath() throws Exception {
        return this.path;
    }

    @Override
    public TempDifferential get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), this.getClass());
    }

    @Override
    public TempDifferential post() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.postForObject(getResourcePath(), this, this.getClass());
    }
}
