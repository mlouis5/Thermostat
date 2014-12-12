/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Requestor;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
public class SimpleMode implements Resource, Requestor<Lock>{

    @JsonIgnore
    private final ResourceURI URI;
    
    /**
     * Description: Thermostat Lock Mode
     * Request Type: POST
     * Data Format: Integer value:
     * 1 = normal mode
     * 2 = simple mode
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("simple_mode")
    @AttributeInterpreter(key = {1, 2}, values = {ReadableValue.NORMAL_MODE, ReadableValue.SIMPLE_MODE})
    private int simpleMode;
    
    public SimpleMode() throws Exception{
        URI = Thermostat.URI.clone().path("simple_mode").build();
    }
    
    @Override
    public String getUriString() throws Exception {
        return URI.getUriWithHttp();
    }

    @Override
    public Lock get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getUriString(), Lock.class);
    }

    @Override
    public Lock post() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.postForObject(getUriString(), this, Lock.class);
    }
    
}
