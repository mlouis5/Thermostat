/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.modes.concretes;

import com.mac.thermostat.resources.impl.attributes.DayProgram.DayType;
import com.mac.thermostat.resources.impl.subresource.modes.abstracts.Day;

/**
 *
 * @author MacDerson
 */
public class Thursday extends Day{
    private static final DayType resourcePath = DayType.THURSDAY;
    
    public Thursday() throws Exception {
        super(resourcePath);
    }
}