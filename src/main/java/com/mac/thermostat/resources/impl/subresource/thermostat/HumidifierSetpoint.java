/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.thermostat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.utilities.SimpleRequester;

/**
 * Thermostat Humidifier Setpoint<br>
 * Data Representation of Thermostat Humidifier Setpoint<br>
 * This resource is available at: http://<ip-address>/tstat/thumidity
 * 
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class HumidifierSetpoint extends SimpleRequester<HumidifierSetpoint>{

    @JsonIgnore
    private static final String RESOURCE = "thumidity";
    @JsonIgnore
    private static final float MIN_T_HUMIDITY = 0f;
    @JsonIgnore
    private static final float MAX_T_HUMIDITY = 100f;
    /**
     * Description: Current humidifier setpoint value<br>
     * Request Type: GET, POST<br>
     * Data Format: Float value: Value is % relative humidity from 0 to 100%
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty(RESOURCE)
    private float tHumidity;

    public HumidifierSetpoint() throws Exception {
        super(Thermostat.URI, HumidifierSetpoint.class, RESOURCE);
    }

    public float getTHumidity() {
        return tHumidity;
    }

    public void setTHumidity(float tHumidity) {
        this.tHumidity = tHumidity < MIN_T_HUMIDITY ? MIN_T_HUMIDITY
                : tHumidity > MAX_T_HUMIDITY ? MAX_T_HUMIDITY : tHumidity;
    }

    @Override
    protected void doBeforeGet() {
    }

    @Override
    protected void doBeforePost() {
    }
}
