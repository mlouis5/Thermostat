/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.impl.System;
import com.mac.thermostat.resources.impl.utilities.SimpleGetter;
import java.util.List;

/**
 * The data returned has the following information:
 * services_names: A list of service names available on the device. This
 * is the same as that announced on the SSDP protocol by this device.
 * This is returned as a JSON array of strings. Each member of the array
 * identifies one service.<br><br>
 * httpd_handlers: A list of all the URIs that are available along with the
 * kind of operations (GET/POST) available on them. This is returned as
 * JSON attribute-value pairs of the form “u1”:[a1, b1] where,<br>
 * ui: the URI
 * ai: 1-GET is allowed, 0-GET is not allowed
 * bi: 1-POST-allowed, 0-POST is not allowed
 * Note that while all the URIs on the /sys/ resource are listed, only the
 * top-level URIs of other resources are listed.<br><br>
 * @author Mac
 */
public class Services extends SimpleGetter<Services>{
    
    @JsonIgnore
    private static final String RESOURCE = "services";
    /**
     * Description: A list of service names available on the device. This 
     * is the same as that announced on the SSDP protocol by this device.
     * This is returned as a JSON array of strings. Each member of the array
     * identifies one service.
     * Request Type: GET
     * Data Format: list of Strings
     */
    @RequestType
    @JsonProperty("services_names")
    private List<String> servicesNames;
    /**
     * Description: A list of all the URIs that are available along with the
     * kind of operations (GET/POST) available on them. This is returned as
     * JSON attribute-value pairs of the form “u1”:[a1, b1] where,
     * Request Type: GET
     * Data Format: list of Strings
     */
    @RequestType
    @JsonProperty("httpd_handlers")
    private List<String> httpdHandlers;

    public Services() throws Exception {
        super(System.URI, Services.class, RESOURCE);
    }

    public List<String> getServicesNames() {
        return servicesNames;
    }

    public List<String> getHttpdHandlers() {
        return httpdHandlers;
    }

    @Override
    protected void doBeforeGet() {}
       
}
