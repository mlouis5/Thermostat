/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Poster;
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
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
public class PriceMessageArea extends MessageArea
        implements Poster<PriceMessageArea, PriceMessageArea> {

    @JsonIgnore
    private static final int DISABLE = 0;
    @JsonIgnore
    private static final int ENABLE = 2;
    /**
     * Description: Enable/Disable control for uma and pma Request Type: POST
     * Data Format: Integer Value: 0 = Disable 2 = Enable
     */
    @AttributeInterpreter(key = {0, 2},
            values = {ReadableValue.DISABLE, ReadableValue.ENABLE})
    @RequestType(types = {RestType.POST})
    @JsonProperty("mode")
    private int mode;

    public PriceMessageArea() throws Exception {
        super("pma");
        this.mode = 0;
        this.message = "0";
    }

    @Override
    public void setLine(int line) {
        if (line >= 0 && line < 4) {
            this.line = line;
        }
    }

    @Override
    public void setMessage(String msg) {
        if (Objects.nonNull(msg)) {
            if (msg.matches("-?\\d+(\\.\\d+)?")) {
                this.message = msg;
            }
        }
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode == ENABLE ? ENABLE : DISABLE;
    }

    @Override
    public PriceMessageArea post(PriceMessageArea resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if(this.mode == DISABLE){
            this.message = "0";
            this.line = 0;
        }
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, PriceMessageArea.class);
        } else {
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }

}
