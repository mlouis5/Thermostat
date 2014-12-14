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
import com.fasterxml.jackson.databind.JsonNode;
import com.mac.thermostat.resources.impl.attributes.DayProgram;
import com.mac.thermostat.resources.impl.attributes.Minute;
import com.mac.thermostat.resources.impl.attributes.Temperature;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mac
 */
public class DayDeserializer extends JsonDeserializer<DayProgram> {

    @Override
    public DayProgram deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode rootNode = jp.getCodec().readTree(jp);
               
        DayProgram day = null;
        DayProgram.DayType[] types = DayProgram.DayType.class.getEnumConstants();
        for(DayProgram.DayType dayType : types){
            JsonNode singleDay = rootNode.path(dayType.value());
            
            if(!singleDay.isMissingNode()){
                Iterator<JsonNode> dayValues = singleDay.elements();
                
                List<Minute> mins = new ArrayList();
                List<Temperature> temps = new ArrayList();
                
                int pendulum = 2;
                while(dayValues.hasNext()){
                    JsonNode node = dayValues.next();
                    if(pendulum++ % 2 == 0){
                        Minute min = new Minute(node.intValue());
                        mins.add(min);
                    }else{
                        Temperature temp = new Temperature(node.doubleValue());
                        temps.add(temp);
                    }
                }                
                day = new DayProgram(dayType, mins, temps);
                return day;
            }
        }        
        return day;
    }    
}
