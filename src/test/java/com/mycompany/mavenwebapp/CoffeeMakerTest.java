/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebapp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hannutam
 */
public class CoffeeMakerTest {
    
    public CoffeeMakerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getInstance method, of class CoffeeMaker.
     */
    @Test
    public void testGetInstance() {
        System.out.println("CoffeeMakerTest: getInstance");
        CoffeeMaker expResult = CoffeeMaker.getInstance();
        CoffeeMaker result = CoffeeMaker.getInstance();
        assertEquals(expResult, result);
    }
  
//    /**
//     * Test of getWater method, of class CoffeeMaker.
//     */
//    @Test
//    public void testGetWater() {
//        System.out.println("getWater");
//        CoffeeMaker instance = null;
//        int expResult = 0;
//        int result = instance.getWater();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getBeans method, of class CoffeeMaker.
//     */
//    @Test
//    public void testGetBeans() {
//        System.out.println("getBeans");
//        CoffeeMaker instance = null;
//        int expResult = 0;
//        int result = instance.getBeans();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPressed method, of class CoffeeMaker.
//     */
//    @Test
//    public void testGetPressed() {
//        System.out.println("getPressed");
//        CoffeeMaker instance = null;
//        int expResult = 0;
//        int result = instance.getPressed();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of isOn method, of class CoffeeMaker.
//     */
//    @Test
//    public void testIsOn() {
//        System.out.println("isOn");
//        CoffeeMaker instance = null;
//        boolean expResult = false;
//        boolean result = instance.isOn();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of pressOnOff method, of class CoffeeMaker.
     */
    @Test
    public void testPressOnOff() {
        System.out.println("pressOnOff");
        CoffeeMaker cm = CoffeeMaker.getInstance();
        cm.pressOnOff();
        assertTrue(cm.isOn());
        cm.pressOnOff();
        assertFalse(cm.isOn());
    }
//
//      /**
//     * Test of fillWater method, of class CoffeeMaker.
//     */
//    @Test
//    public void testFillWater() {
//        System.out.println("CoffeeMakerTest: fillWater");
//        CoffeeMaker instance = CoffeeMaker.getInstance();
//        instance.fillWater();
//    }
//


    /**
     * Test of brew method, of class CoffeeMaker.
     */
    @Test
    public void testBrew() {
        System.out.println("brew");
        CoffeeMaker cm = CoffeeMaker.getInstance();
        cm.pressOnOff();
        int water = cm.getWater();
        int beans = cm.getBeans();
        int pressed = cm.getPressed();
        cm.brew();
        assertEquals(cm.getWater(), water-30);
        assertEquals(cm.getBeans(), beans-8);
        assertEquals(cm.getPressed(), pressed+1);
    }
    
    /**
     * Test of fillBeans method, of class CoffeeMaker.
     */
    @Test
    public void testFillBeans() {
        System.out.println("CoffeeMakerTest: fillBeans");
        CoffeeMaker cm = CoffeeMaker.getInstance();
        cm.pressOnOff();
        cm.brew();
        cm.fillBeans();

    }
}
