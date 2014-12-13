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
public class RemoteTemp implements Resource, Getter<RemoteTemp>, Poster<RemoteTemp, RemoteTemp>{
    
    @JsonIgnore
    private final ResourceURI URI;
    
    /**
     * Description: Remote Temperature Mode
     * Request Type: POST
     * Data Format: Integer representing the current remote temperature 
     * mode of the thermostat 
     * (1 – enabled, 0 – disabled).
     * 
     * Note: For POST, the only valid value is 0, which disables the remote 
     * temperature mode.
     */
    @AttributeInterpreter(key = {0, 1}, 
            values = {ReadableValue.DISABLED, ReadableValue.ENABLED})
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("rem_mode")
    private int remMode;
    /**
     * Description: The message to be displayed
     * Request Type: POST
     * Data Format: Integer representing the value of the remote temperature in 
     * Fahrenheit. The remote temperature will take the place of the ambient
     * temperature as read by the local thermostat temperature sensor. 
     * A valid POST to this attribute automatically sets the remote temperature
     * mode to 1.
     * 
     * Note: For POST, the only valid value is 0, which disables the remote 
     * temperature mode.
     */
    @RequestType(types = {RestType.POST})
    @JsonProperty("rem_temp")
    private Integer remTemp;
    
    @JsonIgnore
    private int tempRemMem;
    
    public RemoteTemp() throws Exception{
        URI = Thermostat.URI.clone().path("remote_temp").build();
    }

    public int getRemMode() {
        return remMode;
    }

    public void setRemMode(int remMode) {
        this.remMode = remMode;
        if(this.remMode == 0){
            this.remTemp = null;
        }
    }

    public Integer getRemTemp() {
        if(Objects.isNull(remTemp) && Objects.nonNull(tempRemMem)){
            return tempRemMem;
        }
        return remTemp;
    }

    public void setRemTemp(Integer remTemp) {
        this.remTemp = remTemp;
        if(this.remTemp > 0){
            this.remMode = 1;
        }
    }

    @Override
    public String getResourcePath() throws Exception {
        return URI.getUriWithHttp();
    }

    @Override
    public RemoteTemp get() throws Exception {
        tempRemMem = remTemp;
        remTemp = null;
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), RemoteTemp.class);
    }

    @Override
    public RemoteTemp post(RemoteTemp resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, RemoteTemp.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }
    
}
