/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

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
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class StageDelay implements Resource, Getter<StageDelay>, Poster<StageDelay, StageDelay> {

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
    @JsonProperty("stage_delay")
    private int stageDelay;

    public int getStageDelay() {
        return stageDelay;
    }

    public void setStageDelay(int stageDelay) {
        this.stageDelay = stageDelay < MIN_DELAY ? MIN_DELAY
                : stageDelay > MAX_DELAY ? MAX_DELAY : stageDelay;
    }

    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path("stage_delay").build().getUriWithHttp();
    }

    @Override
    public StageDelay get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), StageDelay.class);
    }

    @Override
    public StageDelay post(StageDelay resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, StageDelay.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }

}
