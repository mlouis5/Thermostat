/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.thermostat.resources.Resource;
import com.mac.thermostat.resources.annotations.FeatureAvailability;
import com.mac.thermostat.resources.annotations.RequestType;
import com.mac.thermostat.resources.annotations.enums.RestType;
import com.mac.thermostat.resources.annotations.enums.ThermostatModel;
import com.mac.thermostat.resources.impl.attributes.Time;
import com.mac.thermostat.resources.impl.subresource.MessageArea;
import com.mac.thermostat.resources.impl.subresource.thermostat.AirBaffle;
import com.mac.thermostat.resources.impl.subresource.thermostat.Dehumidifier;
import com.mac.thermostat.resources.impl.subresource.thermostat.ExternalDehumidifier;
import com.mac.thermostat.resources.impl.subresource.thermostat.FanCirculationTime;
import com.mac.thermostat.resources.impl.subresource.thermostat.Humidifier;
import com.mac.thermostat.resources.impl.subresource.thermostat.HumidifierSetpoint;
import com.mac.thermostat.resources.impl.subresource.thermostat.Humidity;
import com.mac.thermostat.resources.impl.subresource.thermostat.LED;
import com.mac.thermostat.resources.impl.subresource.thermostat.Lock;
import com.mac.thermostat.resources.impl.subresource.thermostat.Model;
import com.mac.thermostat.resources.impl.subresource.thermostat.NightLight;
import com.mac.thermostat.resources.impl.subresource.thermostat.PriceMessageArea;
import com.mac.thermostat.resources.impl.subresource.thermostat.Program;
import com.mac.thermostat.resources.impl.subresource.thermostat.RemoteTemp;
import com.mac.thermostat.resources.impl.subresource.thermostat.SaveEnergy;
import com.mac.thermostat.resources.impl.subresource.thermostat.SimpleMode;
import com.mac.thermostat.resources.impl.subresource.thermostat.StageDelay;
import com.mac.thermostat.resources.impl.subresource.thermostat.TempSwing;
import com.mac.thermostat.resources.impl.subresource.thermostat.TimeFormat;
import com.mac.thermostat.resources.impl.subresource.thermostat.UserMessageArea;
import com.mac.thermostat.resources.impl.subresource.modes.abstracts.TempDifferential;
import com.mac.thermostat.resources.impl.subresource.modes.concretes.Cool;
import com.mac.thermostat.resources.impl.subresource.modes.concretes.Heat;
import com.mac.thermostat.resources.impl.utilities.ConcreteResourceURI;
import com.mac.thermostat.resources.impl.utilities.ResourceURI;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mac
 *
 *  *
 * Notes: # - The default behavior of POST on t_heat and t_cool is to update the
 * temporary target temperature. Some custom flavors of the firmware update the
 * absolute target temperature when t_heat or t_cool values are updated. This
 * distinction can be made by referring to the t_type_post attribute. This
 * differing behavior is deprecated and will be obsoleted in future versions of
 * the API. No comment can be made about whether the t_heat/t_cool data returned
 * in a GET /tstat/ response indicates temporary or absolute target
 * temperatures. * - Some thermostat models may not support all program modes.
 */
@FeatureAvailability(model = {ThermostatModel.CT30, ThermostatModel.CT50,
    ThermostatModel.CT80A, ThermostatModel.CT80B})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thermostat implements Resource {

    public static final ResourceURI URI;
    
    static {
        URI = new ConcreteResourceURI();

        try {
            URI.base(InetAddress.getByName("192.168.11.130")).path("tstat").build();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Thermostat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Thermostat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////ATTRIBUTES/////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Description: Current Temperature Request Type: GET Data Format: double,
     * representing temp in Fahrenheit
     */
    @RequestType
    @JsonProperty("temp")
    private double temp;
    /**
     * Description: Thermostat Operating Mode Request Type: GET, POST Data
     * Format: integer, 0=OFF, 1=HEAT, 2=COOL, 3=AUTO
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("tmode")
    private int tMode;
    /**
     * Description: Fan Operating Mode Request Type: GET, POST Data Format:
     * integer, 0=AUTO, 1=AUTO/CIRCULATE, 2=ON
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("fmode")
    private int fMode;
    /**
     * Description: Target temperature temporary override Request Type: GET Data
     * Format: integer, 0=Override is disabled, 1=Override is enabled Note:
     * Firmware versions prior to 1.04 can return any non-zero value if override
     * is enabled.
     */
    @RequestType
    @JsonProperty("override")
    private int override;
    /**
     * Description: Target temperature Hold status Request Type: GET, POST Data
     * Format: integer, 0=Hold is disabled, 1=Hold is enabled
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("hold")
    private int hold;
    /**
     * Description: Temporary Target Heat Setpoint: Sets target and MODE Request
     * Type: GET, POST Data Format: double, Floating point representing
     * temperature value in degree Fahrenheit. #
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("t_heat")
    private double tHeat;
    /**
     * Description: Temporary Target Cool Setpoint: Sets target and MODE Request
     * Type: GET, POST Data Format: double, Floating point representing
     * temperature value in degree Fahrenheit. #
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("t_cool")
    private double tCool;
    /**
     * Description: Temporary Target Heat Setpoint Request Type: GET, POST Data
     * Format: double, Floating point representing temperature value in degree
     * Fahrenheit.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("it_heat")
    private double itHeat;
    /**
     * Description: Temporary Target Cool Setpoint Request Type: GET, POST Data
     * Format: double, Floating point representing temperature value in degree
     * Fahrenheit.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("it_cool")
    private double itCool;
    /**
     * Description: Absolute Target Heat Setpoint Request Type: GET, POST Data
     * Format: double, Floating point representing temperature value in degree
     * Fahrenheit.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("a_heat")
    private double aHeat;
    /**
     * Description: Absolute Target Cool Setpoint Request Type: GET, POST Data
     * Format: double, Floating point representing temperature value in degree
     * Fahrenheit.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("a_cool")
    private double aCool;
    /**
     * Description: Absolute Target Temperature Mode Request Type: POST Data
     * Format: Integer representing the absolute target temperature mode. 0 –
     * Disable Absolute Target Temperature Mode 1 – Enable Absolute Target
     * Temperature Mode
     */
    @RequestType(types = {RestType.POST})
    @JsonProperty("a_mode")
    private int aMode;
    /**
     * Description: Target Temperature POST type Request Type: GET Data Format:
     * Integer value that indicates whether a POST on<br>
     * t_heat/t_cool will result in temporary or absolute temperature change. 0:
     * Temporary Target Temperature 1: Absolute Target Temperature 2: Unknown
     * This attribute is deprecated and will be obsoleted in future versions of
     * the API. #
     */
    //private int t_type_post;
    /**
     * Description: HVAC Operating State Request Type: GET Data Format: Integer
     * value: 0: OFF 1: HEAT 2: COOL Note: This functionality may not be
     * available in all models of the thermostat.
     */
    @RequestType
    @JsonProperty("tstate")
    private int tState;
    /**
     * Description: Fan Operating State Request Type: GET Data Format: Integer
     * value: 0: OFF 1: ON Note: Only available with CT-30
     */
    @RequestType
    @JsonProperty("fstate")
    private int fState;
    /**
     * Description: Thermostat’s internal representation of time Request Type:
     * GET, POST Data Format: JSON object with the following fields: day, hours,
     * minutes. day: Integer value representing the day of the week, with day 0
     * being Monday. hour: Integer value representing number of hours elapsed
     * since midnight. minutes: Integer value representing number of minutes
     * since start of the hour.
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("time")
    private Time time;
    /**
     * Description: Program Mode Request Type: GET, POST Data Format: Integer
     * representing the current program mode:* 0: Program A 1: Program B 2:
     * Vacation 3: Holiday
     */
    @RequestType(types = {RestType.GET, RestType.POST})
    @JsonProperty("program_mode")
    private int programMode;
    /**
     * Description: Current operating target Request Type: GET Data Format:
     * Integer representing the current target operating mode. For example, if
     * the thermostat is in AUTO mode and operating toward a cool target, then
     * this value will be 2. Valid values: 0: Off 1: Heat 2: Cool
     */
    @RequestType    //defaults to REstType.GET
    @JsonProperty("ttarget")
    private int tTarget;
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////END ATTRIBUTES///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////RESOURCES/////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @JsonIgnore
    private final Program program;

    @JsonIgnore
    private final Model model;

    @JsonIgnore
    private final LED led;
    
    @JsonIgnore
    private final MessageArea PMA;

    @JsonIgnore
    private final MessageArea UMA;
    
    @JsonIgnore
    private final RemoteTemp remoteTemp;
    
    @JsonIgnore
    private final Lock lock;
    
    @JsonIgnore
    private final SimpleMode simpleMode;
    
    @JsonIgnore
    private final SaveEnergy saveEnergy;
    
    @JsonIgnore
    private final TempSwing tempSwing;
    
    @JsonIgnore
    private final NightLight nightLight;
    
    @JsonIgnore
    private final TempDifferential cool;
    
    @JsonIgnore
    private final TempDifferential heat;
    
    @JsonIgnore
    private final StageDelay stageDelay;
    
    @JsonIgnore
    private final FanCirculationTime fanCirculationTime;
    
    @JsonIgnore
    private final Humidity humidity;
    
    @JsonIgnore
    private final HumidifierSetpoint humidifierSetpoint;
    
    @JsonIgnore
    private final Humidifier humidifier;
    
    @JsonIgnore
    private final Dehumidifier dehumidifier;
    
    @JsonIgnore
    private final ExternalDehumidifier externalDehumidifier;
    
    @JsonIgnore
    private final TimeFormat timeFormat;
    
    @JsonIgnore
    private final AirBaffle airBaffle;
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////END RESOURCES///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public Thermostat() throws Exception {
        program = new Program();
        model = new Model();
        led = new LED();
        PMA = new PriceMessageArea();
        UMA = new UserMessageArea();
        remoteTemp = new RemoteTemp();
        lock = new Lock();
        simpleMode = new SimpleMode();
        saveEnergy = new SaveEnergy();
        tempSwing = new TempSwing();
        nightLight = new NightLight();
        cool = new Cool();
        heat = new Heat();
        stageDelay = new StageDelay();
        fanCirculationTime = new FanCirculationTime();
        humidity = new Humidity();
        humidifierSetpoint = new HumidifierSetpoint();
        humidifier = new Humidifier();
        dehumidifier = new Dehumidifier();
        externalDehumidifier = new ExternalDehumidifier();
        timeFormat = new TimeFormat();
        airBaffle = new AirBaffle();
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int gettMode() {
        return tMode;
    }

    public void settMode(int tMode) {
        this.tMode = tMode;
    }

    public int getfMode() {
        return fMode;
    }

    public void setfMode(int fMode) {
        this.fMode = fMode;
    }

    public int getOverride() {
        return override;
    }

    public void setOverride(int override) {
        this.override = override;
    }

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) {
        this.hold = hold;
    }

    public double gettHeat() {
        return tHeat;
    }

    public void settHeat(double tHeat) {
        this.tHeat = tHeat;
    }

    public double gettCool() {
        return tCool;
    }

    public void settCool(double tCool) {
        this.tCool = tCool;
    }

    public double getItHeat() {
        return itHeat;
    }

    public void setItHeat(double itHeat) {
        this.itHeat = itHeat;
    }

    public double getItCool() {
        return itCool;
    }

    public void setItCool(double itCool) {
        this.itCool = itCool;
    }

    public double getaHeat() {
        return aHeat;
    }

    public void setaHeat(double aHeat) {
        this.aHeat = aHeat;
    }

    public double getaCool() {
        return aCool;
    }

    public void setaCool(double aCool) {
        this.aCool = aCool;
    }

    public int getaMode() {
        return aMode;
    }

    public void setaMode(int aMode) {
        this.aMode = aMode;
    }

    public int gettState() {
        return tState;
    }

    public void settState(int tState) {
        this.tState = tState;
    }

    public int getfState() {
        return fState;
    }

    public void setfState(int fState) {
        this.fState = fState;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getProgramMode() {
        return programMode;
    }

    public void setProgramMode(int programMode) {
        this.programMode = programMode;
    }

    public int gettTarget() {
        return tTarget;
    }

    public void settTarget(int tTarget) {
        this.tTarget = tTarget;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////RESOURCES ACCESSORS////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Thermostat Program Resource<br><br>
     * The thermostat maintains two programs<br>
     * a heat program and a cool program. Every program entry consists<br>
     * of time and the corresponding temperature setpoint. Every day of the week<br>
     * can have a set of time-setpoint pair programmed in the thermostat.<br>
     * @return Program
     */
    public Program getProgram() {
        return program;
    }

    public Model getModel() {
        return model;
    }

    public LED getLed() {
        return led;
    }

    public MessageArea getPMA() {
        return PMA;
    }

    public MessageArea getUMA() {
        return UMA;
    }

    public RemoteTemp getRemoteTemp() {
        return remoteTemp;
    }

    public Lock getLock() {
        return lock;
    }

    public SimpleMode getSimpleMode() {
        return simpleMode;
    }

    public SaveEnergy getSaveEnergy() {
        return saveEnergy;
    }

    public TempSwing getTempSwing() {
        return tempSwing;
    }

    public NightLight getNightLight() {
        return nightLight;
    }

    public TempDifferential getCool() {
        return cool;
    }

    public TempDifferential getHeat() {
        return heat;
    }

    public StageDelay getStageDelay() {
        return stageDelay;
    }

    public FanCirculationTime getFanCirculationTime() {
        return fanCirculationTime;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public HumidifierSetpoint getHumidifierSetpoint() {
        return humidifierSetpoint;
    }

    public Humidifier getHumidifier() {
        return humidifier;
    }

    public Dehumidifier getDehumidifier() {
        return dehumidifier;
    }

    public ExternalDehumidifier getExternalDehumidifier() {
        return externalDehumidifier;
    }

    public TimeFormat getTimeFormat() {
        return timeFormat;
    }

    public AirBaffle getAirBaffle() {
        return airBaffle;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////END RESOURCES ACCESSORS//////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    public String getResourcePath() throws Exception {
        return URI.getUriWithHttp();
    }
}
