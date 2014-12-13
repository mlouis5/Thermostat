/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.utilities;

import com.mac.thermostat.resources.Poster;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.impl.Thermostat;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 * @param <T>
 */
public class SimplePoster<T extends Resource> implements Resource, Poster<T, T>{

    private final String path;
    private final Class<T> tType;
    
    public SimplePoster(Class<T> tType, String resource) throws Exception{
        this.path = resource;
        this.tType = tType;
    }
    
    @Override
    public String getResourcePath() throws Exception {
        return Thermostat.URI.clone().path(path).build().getUriWithHttp();
    }

    @Override
    public T post(T resource) throws Exception {
        RestTemplate template = new RestTemplate();
        if (Objects.isNull(resource)) {
            return template.postForObject(getResourcePath(), this, tType);
        } else {
            return template.postForObject(getResourcePath(), resource, tType);
        }
    }
    
}
