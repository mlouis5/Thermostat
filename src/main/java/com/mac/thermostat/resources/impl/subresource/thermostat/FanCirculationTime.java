/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.thermostat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.SimpleRequester;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FanCirculationTime extends SimpleRequester<FanCirculationTime> implements Resource {

    @JsonIgnore
    private static final String RESOURCE = "fan_ctime";
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
    @JsonProperty(RESOURCE)
    private int fanCTime;

    public FanCirculationTime() throws Exception {
        super(Thermostat.URI, FanCirculationTime.class, RESOURCE);
    }

    public int getFanCTime() {
        return fanCTime;
    }

    public void setFanCTime(int fanCTime) {
        this.fanCTime = fanCTime < MIN_CTIME ? MIN_CTIME
                : fanCTime > MAX_CTIME ? MAX_CTIME : fanCTime;
    }

    @Override
    protected void doBeforeGet() {
    }

    @Override
    protected void doBeforePost() {
    }
}
