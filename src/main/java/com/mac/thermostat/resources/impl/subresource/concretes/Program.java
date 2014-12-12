/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.thermostat.resources.Getter;
import com.mac.thermostat.resources.Poster;
import com.mac.thermostat.resources.TraversableResource;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.attributes.DayProgram;
import com.mac.thermostat.resources.impl.attributes.WeekProgram;
import com.mac.thermostat.resources.impl.subresource.modes.abstracts.Day;
import com.mac.thermostat.resources.impl.subresource.modes.abstracts.Mode;
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
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
@RequestType(types = {RestType.GET, RestType.POST})
public class Program implements TraversableResource {

    protected ResourceURI URI;

    private Mode mode;
    private Day day;

    public Program() throws Exception {
        URI = Thermostat.URI.clone().path("program");
    }

    public Program mode(Mode mode) throws Exception {
        if (Objects.nonNull(mode)) {
            this.mode = mode;
            //URI = Thermostat.URI.clone().path("program").path(this.mode.getUriString());
        }
        return this;
    }

    public Program day(Day day) throws Exception {
        if (Objects.nonNull(day)) {
            this.day = day;
//            if (Objects.nonNull(mode)) {
//                URI = Thermostat.URI.clone().path("program")
//                        .path(mode.getUriString())
//                        .path(this.day.getUriString());
//            }
        }
        return this;
    }

    public Mode getMode() {
        return mode;
    }

    public Day getDay() {
        return day;
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

    public class ModeRequestor implements Getter<Program>,
            Poster<Program, Program> {

        private final ResourceURI uri;

        private ModeRequestor(ResourceURI uri) {
            this.uri = uri;
        }

        @Override
        public Program get() throws Exception {
            RestTemplate template = new RestTemplate();            
            DayProgram prog = template.getForObject(uri.getUriWithHttp(), DayProgram.class);
            Program.this.getDay().setDayProgram(prog);
            return Program.this;
        }

        @Override
        public Program post(Program resource) throws Exception {
            RestTemplate template = new RestTemplate();
            if (Objects.nonNull(resource)) {
                WeekProgram week = resource.getMode().getWeek();
                if (Objects.nonNull(week)) {
                    resource.getMode().setWeek(template.postForObject(uri.getUriWithHttp(), week, week.getClass()));
                }
            }
            return resource;
        }
    }//end ModeRequester
    
    public class DayRequestor implements Getter<Program>,
            Poster<Program, Program> {

        private final ResourceURI uri;

        private DayRequestor(ResourceURI uri) {
            this.uri = uri;
        }

        @Override
        public Program get() throws Exception {
            RestTemplate template = new RestTemplate();            
            DayProgram prog = template.getForObject(uri.getUriWithHttp(), DayProgram.class);
            Program.this.getDay().setDayProgram(prog);
            return Program.this;
        }

        @Override
        public Program post(Program resource) throws Exception {
            RestTemplate template = new RestTemplate();
            if (Objects.nonNull(resource)) {
                DayProgram day = resource.getDay().getDayProgram();
                if (Objects.nonNull(day)) {
                    resource.getDay().setDayProgram(template.postForObject(uri.getUriWithHttp(), day, day.getClass()));
                }
            }
            return resource;
        }
    }//end DayRequester
}
