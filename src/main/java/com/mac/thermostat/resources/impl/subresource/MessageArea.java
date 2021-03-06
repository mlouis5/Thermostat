/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import com.mac.thermostat.resources.impl.utilities.SimplePoster;

/**
 * Note: The PMA/UMA state is volatile. Their state may change when the WiFi<br> 
 * module or the thermostat reboots, or when the thermostat modifies it to<br>
 * show updates.<br><br>
 * Note: If a message field is specified the UMA/PMA is automatically turned<br>
 * on regardless of the value of mode.
 * 
 * @author Mac
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MessageArea<T extends Resource>  extends SimplePoster<T>{
    
    /**
     * Description: The line no. of the messaging area
     * Request Type: POST
     * Data Format: Integer representing the line number to write to.
     * Valid Values: 0, 1 (on UMA), 2, 3 (on PMA)
     */
    @RequestType(types = {RestType.POST})
    @JsonProperty("line")
    protected int line;
    
    /**
     * Description: The message to be displayed
     * Request Type: POST
     * Data Format: String containing the desired message to display.
     * Note: The PMA can only display numbers
     */
    @RequestType(types = {RestType.POST})
    @JsonProperty("message")
    protected String message;
    
    public MessageArea(ResourceURI uri, Class<T> tType, String resource) throws Exception{
        super(uri, tType, resource);
    }
    
    public abstract void setLine(int line);
    
    public abstract void setMessage(String msg);
    
    public int getLine(){
        return line;
    }
    
    public String getMessage(){
        return message;
    }   
}
