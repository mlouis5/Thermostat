/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Mac
 */
public class WeekProgramTest {
    
    public WeekProgramTest() {
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
     * Test of putDay method, of class WeekProgram.
     */
    @Test
    public void testPutDay() {
        System.out.println("putDay");
        WeekProgram week = new WeekProgram();
        
        DayProgram tues = new DayProgram(DayProgram.DayType.TUESDAY);
        tues.addToProgram(new Minute(30), new Temperature(85.3));
        tues.addToProgram(new Minute(60), new Temperature(85.1));
        tues.addToProgram(new Minute(90), new Temperature(84.8));
        tues.addToProgram(new Minute(120), new Temperature(84.6));
        tues.addToProgram(new Minute(150), new Temperature(84.4));
        tues.addToProgram(new Minute(180), new Temperature(84.2));
        tues.addToProgram(new Minute(210), new Temperature(84));
        tues.addToProgram(new Minute(240), new Temperature(83.8));
        tues.addToProgram(new Minute(270), new Temperature(83.6));
        
        DayProgram wedn = new DayProgram(DayProgram.DayType.WEDNESDAY);
        wedn.addToProgram(new Minute(30), new Temperature(85.3));
        wedn.addToProgram(new Minute(60), new Temperature(85.1));
        wedn.addToProgram(new Minute(90), new Temperature(84.8));
        wedn.addToProgram(new Minute(120), new Temperature(84.6));
        wedn.addToProgram(new Minute(150), new Temperature(84.4));
        wedn.addToProgram(new Minute(180), new Temperature(84.2));
        wedn.addToProgram(new Minute(210), new Temperature(84));
        wedn.addToProgram(new Minute(240), new Temperature(83.8));
        wedn.addToProgram(new Minute(270), new Temperature(83.6));
        
        week.putDay(tues.getDayType(), tues);
        week.putDay(wedn.getDayType(), wedn);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(week));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DayProgramTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProgram method, of class WeekProgram.
     */
//    @Test
//    public void testGetProgram() {
//        System.out.println("getProgram");
//        DayProgram.DayType dayType = null;
//        WeekProgram instance = new WeekProgram();
//        DayProgram expResult = null;
//        DayProgram result = instance.getProgram(dayType);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of hash method, of class WeekProgram.
     */
//    @Test
//    public void testHash() {
//        System.out.println("hash");
//        WeekProgram instance = new WeekProgram();
//        int expResult = 0;
//        int result = instance.hash();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
