/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources;

/**
 *
 * @author MacDerson
 */
public interface Poster<R extends Resource, S> {
    
    S post(R resource) throws Exception;
}
