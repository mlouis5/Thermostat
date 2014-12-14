/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.utilities;

import com.mac.thermostat.resources.Getter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 * @param <T>
 */
public abstract class SimpleGetter <T> extends ResourceLocator implements Getter<T>{

    private final Class<T> tType;
    
    public SimpleGetter(ResourceURI baseResource, Class<T> tType, String... resources) throws Exception{
        super(baseResource, resources);
        this.tType = tType;
    }
        
    @Override
    public T get() throws Exception {
        doBeforeGet();
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), tType);
    }
    
    protected abstract void doBeforeGet();
}
