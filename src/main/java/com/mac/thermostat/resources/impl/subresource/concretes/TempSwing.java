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
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.impl.Thermostat;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
public class TempSwing implements Resource, Getter<TempSwing>, Poster<TempSwing, TempSwing>{
    
    private static final float MIN_SWING = .5f;
    private static final float MAX_SWING = 3f;
    /**
     * Description: Thermostat temperature swing<br>
     * Request Type: GET, POST<br>
     * Data Format: Float value 0.5 degree increments with a valid<br>
     * temperature range from 0.5 to 3.0 degrees F.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("tswing")
    private float tSwing;

    public float gettSwing() {
        return tSwing;
    }

    public void settSwing(float tSwing) {
        if(tSwing < MIN_SWING){
            this.tSwing = MIN_SWING;
        }
        if(tSwing > MAX_SWING){
            this.tSwing = MAX_SWING;
        }
        this.tSwing = tSwing;
    }
            
    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path("tswing").build().getUriWithHttp();
    }

    @Override
    public TempSwing get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), TempSwing.class);
    }

    @Override
    public TempSwing post(TempSwing resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, TempSwing.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }
    
}
