/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources;

/**
 *
 * @author Mac
 */
public interface Requestor {
    
    Resource get() throws Exception;
    
    Resource post() throws Exception;
}
