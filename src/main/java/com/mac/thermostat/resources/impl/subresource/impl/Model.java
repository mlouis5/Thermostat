/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Requestor;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@RequestType
public class Model implements Resource, Requestor{
    
    @JsonIgnore
    private static ResourceURI URI;
    
    @JsonProperty("model")
    public String model;
        
    public Model() throws Exception {
        URI = Thermostat.URI.clone().path("model").build();
    }
    
    public String getModel(){
        return model;
    }
    
    @Override
    public Model get() throws Exception{
        RestTemplate template = new RestTemplate();
        return template.getForObject(URI.getUriWithHttp(), Model.class);
    }
    
    @Override
    public Resource post() throws Exception {
        return null;
    }

    @Override
    public String getUriString() throws Exception {
        return URI.getUriWithHttp();
    }
    
}
