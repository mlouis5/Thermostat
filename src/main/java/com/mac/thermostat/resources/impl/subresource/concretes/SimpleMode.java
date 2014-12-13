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
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleMode implements Resource, Getter<SimpleMode>, Poster<SimpleMode, SimpleMode>{
    
    @JsonIgnore
    private static final int NORMAL_MODE = 1;
    @JsonIgnore
    private static final int SIMPLE_MODE = 2;
    /**
     * Description: Thermostat Lock Mode
     * Request Type: GET, POST
     * Data Format: Integer value:
     * 1 = normal mode
     * 2 = simple mode
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("simple_mode")
    @AttributeInterpreter(key = {1, 2}, values = {ReadableValue.NORMAL_MODE, ReadableValue.SIMPLE_MODE})
    private int simpleMode;

    public int getSimpleMode() {
        return simpleMode;
    }

    public void setSimpleMode(int simpleMode) {
        this.simpleMode = simpleMode == SIMPLE_MODE ? simpleMode : NORMAL_MODE;
    }
    
    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path("simple_mode").build().getUriWithHttp();
    }

    @Override
    public SimpleMode get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), SimpleMode.class);
    }

    @Override
    public SimpleMode post(SimpleMode resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, SimpleMode.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }
    
}
