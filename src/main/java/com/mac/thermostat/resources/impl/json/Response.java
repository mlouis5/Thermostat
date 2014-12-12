/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.json;

/**
 *
 * @author Mac
 */
public class Response {
    
    private final String response;
    
    public Response(){
        this.response = null;
    }
    
    public Response(String response){
        this.response = response;
    }

    public String getResponse() {
        return response;
    }    
    
}
