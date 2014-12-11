/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.utilities;

import java.net.InetAddress;

/**
 *
 * @author Mac
 */
public interface ResourceURI {
    
    InetAddress getBase();
    
    String getPath();
    
    ResourceURI base(InetAddress ip);
    
    ResourceURI path(String path);
    
    ResourceURI build() throws Exception;
    
    String getUriWithHttp() throws Exception;
    
    String getUriWithoutHttp() throws Exception;
    
    ResourceURI clone();
}
