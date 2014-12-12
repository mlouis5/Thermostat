/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources;

/**
 *
 * @author Mac
 * @param <T>
 */
public interface Requestor<T extends Resource> {
    
    T get() throws Exception;
    
    T post() throws Exception;
}
