/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.annotations.enums;

/**
 *
 * @author Mac
 */
public enum ReadableValue {

    NORMAL_MODE("Normal Mode"), SIMPLE_MODE("Simple Mode"), 
    LOCK_DISABLED("Lock Disabled"), PARTIAL_LOCK("Partial Lock"), 
    FULL_LOCK("Full Lock"), UTILITY_LOCK("Utility Lock"),
    OFF("Off"), GREEN("Green"), YELLOW("Yellow"), RED("Red"),
    DISABLE("Disable"), DISABLED("Disabled"), ENABLE("Enable"), 
    ENABLED("Enabled"), ADJUST_TARGET_BY_DELTA("Adjust target by delta"),
    LEAST_CONSUMING_POINT_IN_PROGRAM("Least consuming point"),
    SAVE_ENERGY_ABSOLUTE_SETPOINT("Save energy absolute setpoint"),
    _25("25%"), _50("50%"), _75("75%"), _100("100%"),
    RUN_ONLY_WITH_HEAT("Run only with heat"), 
    HUMIDITY_ANYTIME("Humidity anytime (runs fan)"),
    ON_WITH_FAN("On with fan"), ON_WITHOUT_FAN("On without fan"),
    HUMIDISTAT_WITH_THERMOSTAT("Humidistat with thermostat"),
    ALWAYS_WITH_AC("Always with AC"), _12_HOUR_FORMAT("12 Hour format AM/PM"),
    _24_HOUR_FORMAT("24 Hour format"),
    EXTERNAL_AIR_BAFFLE_CLOSED("External air baffle closed"),
    EXTERNAL_AIR_BAFFLE_OPENED_TEMP("External air baffle opened temporarily"),
    EXTERNAL_AIR_BAFFLE_OPENED_PERM("External air baffle opened permanently (set by radio bus only"),
    NORMAL("Normal"), HEAT_PUMP("Heat pump");
    
    private final String value;
    
    ReadableValue(String val){
        this.value = val;
    }
    
    public String readableValue(){
        return value;
    }
}
