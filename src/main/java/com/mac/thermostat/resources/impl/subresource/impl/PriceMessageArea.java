/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.impl.subresource.MessageArea;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceMessageArea extends MessageArea {

    /**
     * Description: Enable/Disable control for uma and pma
     * Request Type: POST
     * Data Format: Integer
     * Value: 0 = Disable 2 = Enable
     */
    @RequestType(types = {RestType.POST})
    @JsonProperty("message")
    private int mode;

    public PriceMessageArea() throws Exception {
        super("pma");
    }

    @Override
    public void setLine(int line) {
        if(line >= 0 && line < 4){
            this.line = line;
        }
    }

    @Override
    public void setMessage(String msg) {
        if (Objects.nonNull(msg)) {
            if (msg.matches("-?\\d+(\\.\\d+)?")) {
                this.message = msg;
            }
        }
    }
    
    public int getMode(){
        return mode;
    }
    
    public void setMode(int mode){
        if(mode == 0 || mode == 2){
            this.mode = mode;
        }
    }

    @Override
    public Resource get() throws Exception {
        return null;
    }

    @Override
    public Resource post() throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(getUriString(), PriceMessageArea.class);
    }

}
