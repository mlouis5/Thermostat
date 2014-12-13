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
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.impl.Thermostat;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FanCirculationTime implements Resource, Getter<FanCirculationTime>,
        Poster<FanCirculationTime, FanCirculationTime> {

    @JsonIgnore
    private static final int MIN_CTIME = 1;
    @JsonIgnore
    private static final int MAX_CTIME = 9;
    /**
     * Description: Thermostat fan circulation time<br>
     * Request Type: GET, POST<br>
     * Data Format: Integer value: 1 to 9 minutes
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("fan_ctime")
    private int fanCTime;

    public int getFanCTime() {
        return fanCTime;
    }

    public void setFanCTime(int fanCTime) {
        this.fanCTime = fanCTime < MIN_CTIME ? MIN_CTIME
                : fanCTime > MAX_CTIME ? MAX_CTIME : fanCTime;
    }

    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path("fan_ctime").build().getUriWithHttp();
    }

    @Override
    public FanCirculationTime get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), FanCirculationTime.class);
    }

    @Override
    public FanCirculationTime post(FanCirculationTime resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, FanCirculationTime.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }

}
