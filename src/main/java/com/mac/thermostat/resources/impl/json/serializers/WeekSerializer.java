/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mac.thermostat.resources.impl.attributes.DayProgram;
import com.mac.thermostat.resources.impl.attributes.DayProgram.DayType;
import com.mac.thermostat.resources.impl.attributes.Minute;
import com.mac.thermostat.resources.impl.attributes.Temperature;
import com.mac.thermostat.resources.impl.attributes.Week;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author MacDerson
 */
public class WeekSerializer extends JsonSerializer<Week> {

    @Override
    public void serialize(Week t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        
        jg.writeStartObject();
        for(DayType type : DayType.values()){
            DayProgram day = t.getProgram(type);
            if(Objects.nonNull(day)){
                Set<Entry<Minute, Temperature>> entries = day.getEntries();
                if(Objects.nonNull(entries)){
                    Iterator<Entry<Minute, Temperature>> iter = entries.iterator();
                    
                    if(Objects.nonNull(iter)){
                        jg.writeArrayFieldStart(type.value());
                        while(iter.hasNext()){
                            Entry<Minute, Temperature> entry = iter.next();
                            if(Objects.nonNull(entry)){
                                jg.writeNumber(entry.getKey().getMinute());
                                jg.writeNumber(entry.getValue().asFarenheit());
                            }
                        }
                        jg.writeEndArray();
                    }
                }
            }
        }
        jg.writeEndObject();
    }
    
}
