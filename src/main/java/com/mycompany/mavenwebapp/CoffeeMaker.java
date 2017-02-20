package com.mycompany.mavenwebapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hannutam
 */
public class CoffeeMaker {

    private int water;
    private int beans;
    private boolean on;
    private int pressed;

    private static class NewSingletonHolder {

        private static final CoffeeMaker INSTANCE = new CoffeeMaker();
    }

    public static CoffeeMaker getInstance() {
        return NewSingletonHolder.INSTANCE;
    }

    private CoffeeMaker(int water, int beans, boolean on) {
        this.water = water;
        this.beans = beans;
        this.on = on;
        this.pressed = 0;
    }

    @Override
    public String toString() {
        return "CoffeeMaker: " + beans + " beans, " + water + " water, " + "on " + on + ", pressed " + pressed;
    }

    private CoffeeMaker() {
        this(100, 30, false);
    }

    public void fillWater() {
        this.water = 90;
    }

    public void fillBeans() {
        this.beans = 20;
    }

    public int getWater() {
        return this.water;
    }

    public int getBeans() {
        return this.beans;
    }

    public int getPressed() {
        return this.pressed;
    }

    public boolean isOn() {
        return this.on;
    }

    public void pressOnOff() {
        this.on = !this.on;
    }

    public void brew() {
        if (this.on) {
            if ((water >= 30) && (beans >= 9)) {
                this.water -= 30;
                this.beans -= 8;
                this.pressed++;
            }
        }
    }
}
