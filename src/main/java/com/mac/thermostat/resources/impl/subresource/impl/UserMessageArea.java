/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.impl.subresource.MessageArea;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserMessageArea extends MessageArea {

    public UserMessageArea() throws Exception {
        super("uma");
    }

    @Override
    public void setLine(int line) {
        if (line >= 0 && line < 2) {
            this.line = line;
        }
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }
    
    @Override
    public Resource get() throws Exception {
        return null;
    }

    @Override
    public Resource post() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getUriString(), UserMessageArea.class);
    }

}
