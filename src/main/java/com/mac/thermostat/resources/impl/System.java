/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.utilities.ConcreteResourceURI;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mac
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class System {

    public static final ResourceURI URI;

    static {
        URI = new ConcreteResourceURI();

        try {
            URI.base(InetAddress.getByName("192.168.11.130")).path("sys").build();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Thermostat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Thermostat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Description: unique identifier for the device Request Type: GET Data
     * Format: String
     */
    @RequestType
    @JsonProperty("UUID")
    private String UUID;
    /**
     * Description: HTTP API version Request Type: GET Data Format: integer
     */
    @RequestType
    @JsonProperty("api_version")
    private int apiVersion;    
    /**
     * Description: Firmware version Request Type: GET Data Format: String
     */
    @RequestType
    @JsonProperty("fw_version")
    private String fwVersion;
    /**
     * Description: Underlying WLAN firmware version Request Type: GET Data
     * Format: String
     */
    @RequestType
    @JsonProperty("wlan_fw_version")
    private String wlanFwVersion;

    public String getUUID() {
        return UUID;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public String getFwVersion() {
        return fwVersion;
    }

    public String getWlanFwVersion() {
        return wlanFwVersion;
    }
        
}
