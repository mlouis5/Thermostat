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
 * Thermostat Time Format<br>
 * This resource is available at:<br>
 * • http://&lt;ip-address&gt;/tstat/time/format
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeFormat extends SimpleRequester<TimeFormat> {

    @JsonIgnore
    private static final String[] RESOURCE = {"time", "format"};
    @JsonIgnore
    private static final int TWELVE_FORMAT = 1;
    @JsonIgnore
    private static final int TWENTY_FOURS_FORMAT = 2;
    /**
     * Description: Time display format<br>
     * Request Type: GET, POST<br>
     * Data Format: Integer value: 1 = 12 hour format (AM/PM) 2 = 24 hour format
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("format")
    @AttributeInterpreter(key = {1, 2},
            values = {ReadableValue._12_HOUR_FORMAT, ReadableValue._24_HOUR_FORMAT})
    private int format;

    public TimeFormat() throws Exception {
        super(Thermostat.URI, TimeFormat.class, RESOURCE);
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format == TWENTY_FOURS_FORMAT ? TWENTY_FOURS_FORMAT
                : TWELVE_FORMAT;
    }

    @Override
    protected void doBeforeGet() {
    }

    @Override
    protected void doBeforePost() {
    }

}
