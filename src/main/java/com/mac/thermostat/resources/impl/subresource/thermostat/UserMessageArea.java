/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.thermostat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.subresource.MessageArea;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserMessageArea extends MessageArea<UserMessageArea> {

    @JsonIgnore
    private static final String RESOURCE = "uma";
    @JsonIgnore
    private static final int MIN_LINE = 0;
    @JsonIgnore
    private static final int MAX_LINE = 1;

    public UserMessageArea() throws Exception {
        super(Thermostat.URI, UserMessageArea.class, RESOURCE);
    }

    @Override
    public void setLine(int line) {
        this.line = line < MIN_LINE ? MIN_LINE
                : line > MAX_LINE ? MAX_LINE : line;
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    protected void doBeforePost() {}

}
