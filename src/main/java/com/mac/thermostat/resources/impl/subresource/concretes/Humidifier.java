/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Humidifier {

    @JsonIgnore
    private static final int MIN_HUMIDIFIER_MODE = 0;
    @JsonIgnore
    private static final int MAX_HUMIDIFIER_MODE = 2;
    /**
     * Description: Current humidifier setpoint value<br>
     * Request Type: GET, POST<br>
     * Data Format: Float value: Value is % relative humidity from 0 to 100%
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("humidifier_mode")
    @AttributeInterpreter(key = {0, 1, 2},
            values = {ReadableValue.OFF, ReadableValue.RUN_ONLY_WITH_HEAT,
                ReadableValue.HUMIDITY_ANYTIME})
    private int humidifierMode;

    public int getHumidifierMode() {
        return humidifierMode;
    }

    public void setHumidifierMode(int humidifierMode) {
        this.humidifierMode = humidifierMode < MIN_HUMIDIFIER_MODE
                ? MIN_HUMIDIFIER_MODE : humidifierMode > MAX_HUMIDIFIER_MODE
                        ? MAX_HUMIDIFIER_MODE : humidifierMode;
    }

}
