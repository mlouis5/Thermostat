/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mac
 */
public class DayProgramTest {
    
    public DayProgramTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDayType method, of class DayProgram.
     */
    @org.junit.Test
    public void testGetDayType() {
        System.out.println("getDayType");
        DayProgram instance = new DayProgram(DayProgram.DayType.WEDNESDAY);
        instance.addToProgram(new Minute(30), new Temperature(85.3));
        instance.addToProgram(new Minute(60), new Temperature(85.1));
        instance.addToProgram(new Minute(90), new Temperature(84.8));
        instance.addToProgram(new Minute(120), new Temperature(84.6));
        instance.addToProgram(new Minute(150), new Temperature(84.4));
        instance.addToProgram(new Minute(180), new Temperature(84.2));
        instance.addToProgram(new Minute(210), new Temperature(84));
        instance.addToProgram(new Minute(240), new Temperature(83.8));
        instance.addToProgram(new Minute(270), new Temperature(83.6));
        DayProgram.DayType expResult = DayProgram.DayType.WEDNESDAY;
        DayProgram.DayType result = instance.getDayType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(instance));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DayProgramTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTempAtTime method, of class DayProgram.
     */
    @org.junit.Test
    public void testGetTempAtTime() {
        System.out.println("getTempAtTime");
        Minute minute = null;
        DayProgram instance = new DayProgram();
        Temperature expResult = null;
        Temperature result = instance.getTempAtTime(minute);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getEntries method, of class DayProgram.
     */
//    @org.junit.Test
//    public void testGetEntries() {
//        System.out.println("getEntries");
//        DayProgram instance = new DayProgram();
//        Set<Map.Entry<Minute, Temperature>> expResult = null;
//        Set<Map.Entry<Minute, Temperature>> result = instance.getEntries();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
    
}
