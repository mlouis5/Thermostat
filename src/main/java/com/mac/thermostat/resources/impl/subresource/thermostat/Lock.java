/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.thermostat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.SimpleRequester;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lock extends SimpleRequester<Lock> {

    @JsonIgnore
    private static final String RESOURCE = "lock";
    @JsonIgnore
    private static final int MIN_LOCK_MODE = 0;
    @JsonIgnore
    private static final int MAX_LOCK_MODE = 3;
    /**
     * Description: Thermostat Lock Mode Request Type: POST Data Format: Integer
     * that represents: 0 = lock disabled 1 = partial lock 2 = full lock 3 =
     * utility lock (accessible via the radio only)
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("lock_mode")
    @AttributeInterpreter(key = {0, 1, 2, 3},
            values = {ReadableValue.LOCK_DISABLED, ReadableValue.PARTIAL_LOCK,
                ReadableValue.FULL_LOCK, ReadableValue.UTILITY_LOCK})
    private int lockMode;

    public Lock() throws Exception {
        super(Thermostat.URI, Lock.class, RESOURCE);
    }

    public int getLockMode() {
        return lockMode;
    }

    public void setLockMode(int lockMode) {
        this.lockMode = lockMode < MIN_LOCK_MODE ? MIN_LOCK_MODE
                : lockMode > MAX_LOCK_MODE ? MAX_LOCK_MODE : lockMode;
    }

    @Override
    protected void doBeforeGet() {
    }

    @Override
    protected void doBeforePost() {
    }
}
