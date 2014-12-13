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
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lock implements Resource, Getter<Lock>, Poster<Lock, Lock>{

    @JsonIgnore
    private final ResourceURI URI;
    
    /**
     * Description: Thermostat Lock Mode
     * Request Type: POST
     * Data Format: Integer that represents:
     * 0 = lock disabled
     * 1 = partial lock
     * 2 = full lock
     * 3 = utility lock (accessible via the radio only)
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("lock_mode")
    @AttributeInterpreter(key = {0, 1, 2, 3}, 
            values = {ReadableValue.LOCK_DISABLED, ReadableValue.PARTIAL_LOCK,
            ReadableValue.FULL_LOCK, ReadableValue.UTILITY_LOCK})
    private int lockMode;
    
    public Lock() throws Exception{
        URI = Thermostat.URI.clone().path("lock").build();
    }
    
    @Override
    public String getResourcePath() throws Exception {
        return URI.getUriWithHttp();
    }

    @Override
    public Lock get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), Lock.class);
    }

    @Override
    public Lock post(Lock resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, Lock.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }
    
}
