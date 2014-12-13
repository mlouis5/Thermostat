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
import com.mac.thermostat.resources.annotations.AttributeInterpreter;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ReadableValue;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 * The SAVE ENERGY resource is a function that will apply a 4 degree offset to
 * your target temperature<br>
 * If you are heating and the target is 74, when you select the Save Energy
 * function the target will go to 70.<br>
 * This offset will apply across all program periods. Save Energy is useful when
 * you will be gone for a short period of time.
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveEnergy implements Resource, Getter<SaveEnergy>,
        Poster<SaveEnergy, SaveEnergy> {

    @JsonIgnore
    private static final float MIN_DELTA = .5f;
    @JsonIgnore
    private static final float MAX_DELTA = 9f;
    @JsonIgnore
    private static final int DISABLE = 0;
    @JsonIgnore
    private static final int ENABLE = 1;
    @JsonIgnore
    private static final int MIN_TYPE = 1;
    @JsonIgnore
    private static final int MAX_TYPE = 3;
    /**
     * Description: Thermostat save energy mode Request Type: GET, POST Data
     * Format: Integer value: 0 = disable 1 = enable
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("mode")
    @AttributeInterpreter(key = {0, 1}, values = {ReadableValue.DISABLE, ReadableValue.ENABLE})
    private int mode;
    /**
     * Description: Save energy mode type Request Type: GET, POST Data Format:
     * Integer value: 1 = Adjust target by delta 2 = Go to least consuming point
     * in the running program 3 = Save energy absolute setpoint
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("type")
    @AttributeInterpreter(key = {1, 2, 3}, values
            = {ReadableValue.ADJUST_TARGET_BY_DELTA,
                ReadableValue.LEAST_CONSUMING_POINT_IN_PROGRAM,
                ReadableValue.SAVE_ENERGY_ABSOLUTE_SETPOINT})
    private int type;
    /**
     * Description: Save energy Temperature delta Request Type: GET, POST Data
     * Format: Available only when mode and type are 1 Float value: 0.5 degree
     * increments with a valid temperature range from 1 to 9 degrees.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("delta")
    private Float delta;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode == ENABLE ? ENABLE : DISABLE;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type < MIN_TYPE ? MIN_TYPE : type > MAX_TYPE ? MAX_TYPE : type;
    }

    public Float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta < MIN_DELTA ? MIN_DELTA
                : delta > MAX_DELTA ? MAX_DELTA : delta;
    }

    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path("save_energy").build().getUriWithHttp();
    }

    @Override
    public SaveEnergy get() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), SaveEnergy.class);
    }

    @Override
    public SaveEnergy post(SaveEnergy resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            validateDelta();
            return template.postForObject(getResourcePath(), this, SaveEnergy.class);
        } else {
            resource.validateDelta();
            return template.postForObject(getResourcePath(), resource, resource.getClass());
        }
    }

    private void validateDelta() {
        if (Objects.nonNull(delta)) {
            if (mode != 1 || type != 1) {
                delta = null;
            }
        } else {
            if (mode == 1 && type == 1) {
                delta = 4f;
            }
        }
    }
}
