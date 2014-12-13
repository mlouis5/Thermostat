/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.utilities;

import com.mac.thermostat.resources.Getter;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.impl.Thermostat;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 * @param <T>
 */
public abstract class SimpleGetter <T> implements Resource, Getter<T>{

    private final String path;
    private final Class<T> tType;
    
    public SimpleGetter(Class<T> tType, String resource) throws Exception{
        this.path = resource;
        this.tType = tType;
    }
    
    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path(path).build().getUriWithHttp();
    }
        
    @Override
    public T get() throws Exception {
        doBeforeGet();
        RestTemplate template = new RestTemplate();
        return template.getForObject(getResourcePath(), tType);
    }
    
    protected abstract void doBeforeGet();
}
