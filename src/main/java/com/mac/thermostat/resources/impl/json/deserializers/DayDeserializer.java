/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mac.thermostat.resources.impl.attributes.Day;
import java.io.IOException;

/**
 *
 * @author Mac
 */
public class DayDeserializer extends JsonDeserializer<Day> {

    @Override
    public Day deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
