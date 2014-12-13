/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.thermostat.resources.Poster;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.subresource.MessageArea;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserMessageArea extends MessageArea implements Poster<UserMessageArea, UserMessageArea> {

    @JsonIgnore
    private static final int MIN_LINE = 0;
    @JsonIgnore
    private static final int MAX_LINE = 1;

    public UserMessageArea() throws Exception {
        super("uma");
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
    public UserMessageArea post(UserMessageArea resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, UserMessageArea.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }

}
