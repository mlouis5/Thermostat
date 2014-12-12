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
    DISABLE("Disable"), DISABLED("Disabled"), ENABLE("Enable"), ENABLED("Enabled");
    
    private final String value;
    
    ReadableValue(String val){
        this.value = val;
    }
    
    public String readableValue(){
        return value;
    }
}
