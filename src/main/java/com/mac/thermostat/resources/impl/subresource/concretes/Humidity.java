/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Getter;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
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
public class Humidity implements Resource, Getter<Humidity> {
    
    /**
     * Description: Current humidity value<br>
     * Request Type: GET<br>
     * Data Format: Float value: Value is % relative humidity from 0 to 100%
     */
    @RequestType
    @JsonProperty("humidity")
    private float humidity;

    public float getHumidity() {
        return humidity;
    }

    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path("humidity").build().getUriWithHttp();
    }

    @Override
    public Humidity get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), Humidity.class);
    }
    
}
