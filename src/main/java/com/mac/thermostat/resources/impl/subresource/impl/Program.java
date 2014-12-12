/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.thermostat.resources.ManipulableResource;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.attributes.DayProgram;
import com.mac.thermostat.resources.impl.attributes.DayProgram.DayType;
import com.mac.thermostat.resources.impl.attributes.Week;
import com.mac.thermostat.resources.impl.attributes.enums.ProgramMode;
import com.mac.thermostat.resources.impl.json.Response;
import com.mac.thermostat.resources.impl.utilities.ConcreteResourceURI;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 * 2.2.3 Thermostat Program Resource The thermostat maintains two programs – a
 * heat program and a cool program.<br>
 * Every program entry consists of time and the corresponding temperature<br>
 * setpoint. Every day of the week can have a set of time-setpoint pair<br>
 * programmed in the thermostat.
 *
 * @author Mac
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@RequestType(types = {RestType.GET, RestType.POST})
public class Program implements ManipulableResource {

    private ResourceURI URI;

    private ProgramMode mode;
    private Week week;
    private DayType dayType;
    private int lastHash;

    public Program() {
        URI = Thermostat.URI.clone().path("program");
    }

    public Program mode(ProgramMode mode) throws Exception {
        if (Objects.nonNull(mode)) {
            this.mode = mode;
            URI = Thermostat.URI.clone().path("program");
            URI.path(mode.name().toLowerCase()).build();
        }
        return this;
    }

    public Program day(DayType day) throws Exception {
        this.dayType = day;
        return this;
    }

    public Week getWeek() throws Exception {
        RestTemplate template = new RestTemplate();
        week = template.getForObject(URI.getUriWithHttp(), Week.class);

        return week;
    }

    public DayProgram getDay() throws Exception {
        if (Objects.isNull(week)) {
            return getWeek().getProgram(dayType);
        }
        return week.getProgram(dayType);
    }

    public Response programWeek(Week week) throws Exception {
        if (Objects.nonNull(week)) {
            int hash = week.hash();
            if (lastHash != hash) {
                lastHash = hash;
                this.week = week;

                RestTemplate template = new RestTemplate();
                return new Response(template.postForObject(URI.getUriWithHttp(), week, String.class));
            }
        }
        return new Response();
    }

    public Response programDay(DayProgram dayProgram) throws Exception {
        if (Objects.nonNull(dayProgram)) {
            this.week.putDay(dayProgram.getDayType(), dayProgram);
            return programWeek(this.week);
        }
        return new Response();
    }

    public boolean post() {

        return false;
    }

    @Override
    public ResourceURI builder() {
        return new ConcreteResourceURI();
    }

    @Override
    public void setURI(ResourceURI uri) {
        this.URI = uri;
    }

    @Override
    public String getUriString() throws Exception {
        return URI.getUriWithHttp();
    }

}
