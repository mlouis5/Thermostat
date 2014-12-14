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
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.utilities.SimpleGetter;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class HvacSettings extends SimpleGetter<HvacSettings> {

    @JsonIgnore
    private static final String RESOURCE = "hvac_settings";
    /**
     * Description: Normal/Pump<br>
     * Request Type: GET<br>
     * Data Format: Integer Value: 1 = Normal 2 = Heat Pump
     */
    @RequestType
    @JsonProperty("pump")
    @AttributeInterpreter(key = {1, 2},
            values = {ReadableValue.NORMAL, ReadableValue.HEAT_PUMP})
    private int pump;
    /**
     * Description: Normal/Pump<br>
     * Request Type: GET<br>
     * Data Format: Integer value: If Normal: 1 = Heat source is Gas 2 = Heat
     * source is Electric If Heat Pump: 1 = Auxiliary is Gas 2 = Auxiliary is
     * Electric
     */
    @RequestType
    @JsonProperty("aux_type")
    @AttributeInterpreter(key = {1, 2},
            values = {ReadableValue.NORMAL, ReadableValue.HEAT_PUMP})
    private int auxType;

    /**
     * Description: HVAC Setting Code<br>
     * Request Type: GET<br>
     * Data Format: Integer value: For Normal:
     * 1 = 1 stage heat, 1 stage cool
     * 2,4 = 2 stage heat, 1 stage cool
     * 3,5 = 2 stage heat, 2 stage cool
     * For Heat Pump:
     * 10,11 = 1 stage pump, 1 stage aux
     * 12 = 1 stage pump, no aux
     */
    @RequestType
    @JsonProperty("hvac_code")
//    @AttributeInterpreter(key = {1, 2},
//            values = {ReadableValue.NORMAL, ReadableValue.HEAT_PUMP})
    private Integer hvacCode;

    /**
     * Description: Auxiliary Stages<br>
     * Request Type: GET<br>
     * Data Format: Integer Value: If Heat Pump, this value is encoded
     * as the number of auxiliary stages. If Normal, this value is encoded as 
     * the number of heat stages.
     */
    @RequestType
    @JsonProperty("aux_stages")
    @AttributeInterpreter(key = {1, 2},
            values = {ReadableValue.NORMAL, ReadableValue.HEAT_PUMP})
    private Integer auxStages;

    /**
     * Description: Normal/Pump<br>
     * Request Type: GET<br>
     * Data Format: Integer Value: Number of heat pump stages
     */
    @RequestType
    @JsonProperty("heat_stages")
    @AttributeInterpreter(key = {1, 2},
            values = {ReadableValue.NORMAL, ReadableValue.HEAT_PUMP})
    private Integer heatStages;

    /**
     * Description: Normal/Pump<br>
     * Request Type: GET, POST<br>
     * Data Format: Integer Value: Number of cool stages
     */
    @RequestType
    @JsonProperty("cool_stages")
    @AttributeInterpreter(key = {1, 2},
            values = {ReadableValue.NORMAL, ReadableValue.HEAT_PUMP})
    private Integer coolStages;

    public HvacSettings() throws Exception {
        super(HvacSettings.class, RESOURCE);
    }

    public int getPump() {
        return pump;
    }

    public int getAuxType() {
        return auxType;
    }

    public Integer getHvacCode() {
        return hvacCode;
    }

    public Integer getAuxStages() {
        return auxStages;
    }

    public Integer getHeatStages() {
        return heatStages;
    }

    public Integer getCoolStages() {
        return coolStages;
    }
    
    @Override
    protected void doBeforeGet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
