/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources;

import com.mac.thermostat.resources.impl.utilities.ResourceURI;

/**
 *
 * @author Mac
 */
public interface TraversableResource extends Resource{
    
    ResourceURI builder();
    
    void setURI(ResourceURI uri);
}
