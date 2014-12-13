/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Getter;
import com.mac.thermostat.resources.Poster;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.impl.Thermostat;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
public class NightLight implements Resource, Getter<NightLight>, Poster<NightLight, NightLight> {

    /**
     * Description: Thermostat night light intensity Request Type: GET, POST Data
     * Format: Integer value: 0 = off 1 = 25% 2 = 50% 3 = 75% 4 = 100%
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("intensity")
    @AttributeInterpreter(key = {0, 1, 2, 3, 4}, values = {ReadableValue.OFF,
        ReadableValue._25, ReadableValue._50, ReadableValue._75,
        ReadableValue._100})
    private int intensity;

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        if(intensity < 0){
            intensity = 0;
        }
        if(intensity > 4){
            intensity = 4;
        }
        this.intensity = intensity;
    }
    
    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path("night_light").build().getUriWithHttp();
    }

    @Override
    public NightLight get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), NightLight.class);
    }

    @Override
    public NightLight post(NightLight resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, NightLight.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }

}
