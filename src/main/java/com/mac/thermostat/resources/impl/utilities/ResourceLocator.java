/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.utilities;

import com.mac.thermostat.resources.Resource;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public abstract class ResourceLocator implements Resource{
    
    private final ResourceURI path;
    
    public ResourceLocator(ResourceURI uri, String... subResource) throws Exception{
        this.path = uri.clone();
        if(Objects.nonNull(subResource)){
            for(String sub : subResource){
                path.path(sub);
            }
        }
        this.path.build();
    }

    @Override
    public String getResourcePath() throws Exception {
        return this.path.getUriWithHttp();
    }
    
    
}
