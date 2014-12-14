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
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Mac
 */
public class DaySerializer extends JsonSerializer<DayProgram> {

    @Override
    public void serialize(DayProgram day, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        if (Objects.nonNull(day)) {
            Set<Map.Entry<Minute, Temperature>> entries = day.getEntries();
            if (Objects.nonNull(entries)) {
                Iterator<Map.Entry<Minute, Temperature>> iter = entries.iterator();

                if (Objects.nonNull(iter)) {
                    DayType type = day.getDayType();
                    if (Objects.nonNull(type)) {
                        jg.writeStartObject();
                        jg.writeArrayFieldStart(type.value());
                        
                        while (iter.hasNext()) {
                            Map.Entry<Minute, Temperature> entry = iter.next();
                            if (Objects.nonNull(entry)) {
                                jg.writeNumber(entry.getKey().getMinute());
                                jg.writeNumber(entry.getValue().asFarenheit());
                            }
                        }
                        jg.writeEndArray();
                        jg.writeEndObject();
                    }
                }
            }
        }
    }
}
