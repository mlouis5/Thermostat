/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.subresource.thermostat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.thermostat.resources.Getter;
import com.mac.thermostat.resources.Poster;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.Thermostat;
import com.mac.thermostat.resources.impl.attributes.DayProgram;
import com.mac.thermostat.resources.impl.attributes.WeekProgram;
import com.mac.thermostat.resources.impl.subresource.modes.abstracts.Day;
import com.mac.thermostat.resources.impl.subresource.modes.abstracts.Mode;
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
public class Program implements Resource, Getter<Program>, Poster<Program> {

    protected ResourceURI URI;

    private Requestor requestor;
    private Mode mode;
    private Day day;

    public Program() throws Exception {
    }

    public Program mode(Mode mode) throws Exception {
        if (Objects.nonNull(mode)) {
            this.mode = mode;
        }
        return this;
    }

    public Program day(Day day) throws Exception {
        if (Objects.nonNull(day)) {
            this.day = day;
        }
        return this;
    }

    public Mode getMode() {
        return mode;
    }

    /**
     * Thermostat Program for a Day<br>
     * The thermostat program for a single day (for either the heat or cool)<br>
     * mode can be accessed directly using a URI as follows:<br>
     * @return 
     */
    public Day getDay() {
        return day;
    }

    public Program build() throws Exception {
        if (Objects.nonNull(mode)) {
            URI = Thermostat.URI.clone().path("program").path(this.mode.getResourcePath());
            requestor = new ModeRequestor(URI);
            if (Objects.nonNull(day)) {
                URI = Thermostat.URI.clone().path("program")
                        .path(mode.getResourcePath())
                        .path(this.day.getResourcePath());
                requestor = new DayRequestor(URI);
            }
        }
        return this;
    }

    @Override
    public String getResourcePath() throws Exception {
        return URI.getUriWithHttp();
    }

    @Override
    public Program get() throws Exception {
        if (Objects.isNull(requestor)) {
            return this;
        }
        return requestor.get();
    }

    @Override
    public Program post() throws Exception {
        if (Objects.isNull(requestor)) {
            return this;
        }
        return requestor.post();
    }

    private static abstract class Requestor implements Getter<Program>,
            Poster<Program> {

        protected final ResourceURI uri;

        public Requestor(ResourceURI uri) {
            this.uri = uri;
        }
    }

    private class ModeRequestor extends Requestor {

        private ModeRequestor(ResourceURI uri) {
            super(uri);
        }

        @Override
        public Program get() throws Exception {
            RestTemplate template = new RestTemplate();
            DayProgram prog = template.getForObject(uri.getUriWithHttp(), DayProgram.class);
            Program.this.getDay().setDayProgram(prog);
            return Program.this;
        }

        @Override
        public Program post() throws Exception {
            RestTemplate template = new RestTemplate();
            WeekProgram week = Program.this.getMode().getWeek();
            if (Objects.nonNull(week)) {
                Program.this.getMode().setWeek(template.postForObject(uri.getUriWithHttp(), week, week.getClass()));
            }
            return Program.this;
        }
    }//end ModeRequester

    private class DayRequestor extends Requestor {

        private DayRequestor(ResourceURI uri) {
            super(uri);
        }

        @Override
        public Program get() throws Exception {
            RestTemplate template = new RestTemplate();
            DayProgram prog = template.getForObject(uri.getUriWithHttp(), DayProgram.class);
            Program.this.getDay().setDayProgram(prog);
            return Program.this;
        }

        @Override
        public Program post() throws Exception {
            RestTemplate template = new RestTemplate();
            DayProgram day = Program.this.getDay().getDayProgram();
            if (Objects.nonNull(day)) {
                Program.this.getDay().setDayProgram(template.postForObject(uri.getUriWithHttp(), day, day.getClass()));
            }
            return Program.this;
        }
    }//end DayRequester
}//end Program
