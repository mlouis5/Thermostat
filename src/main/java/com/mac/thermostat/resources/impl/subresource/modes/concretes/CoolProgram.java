/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.modes.concretes;

import com.mac.thermostat.resources.impl.subresource.modes.abstracts.Mode;

/**
 *
 * @author MacDerson
 */
public class CoolProgram extends Mode{
    
    private static final String resourcePath = "cool";
    
    public CoolProgram() throws Exception {
        super(resourcePath);
    }

    
    
}
