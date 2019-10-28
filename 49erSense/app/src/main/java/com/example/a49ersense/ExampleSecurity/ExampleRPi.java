package com.example.a49ersense.ExampleSecurity;

import java.util.Random;

public class ExampleRPi extends Thread {
    enum SecuritySystem{Disarmed, ArmedStay, ArmedAway}
    enum GarageDoors{Open, Closed}
    enum Fan{Heat, Cool, Off}
    enum Lights{On, Off}
    enum Locks{Locked, Unlocked}
    enum Windows{Open, Closed}
    enum Sensors{On, Off}
    enum MotionDetector{Active, Inactive}

    private static SecuritySystem houseSystem;
    private static GarageDoors twoCarGarage,oneCarGarage;
    private static Fan mainFloorFan, upstairsFloorFan;
    private static int mainFloorControlTemp,mainFloorCurrentTemp, upstairsFloorControlTemp, upstairsFloorCurrentTemp;
    private static Lights mainFloorLights,upstairsFloorLights;
    private static Locks frontDoor, backDoor, mainDoor;
    private static Windows mainFloorWindows;
    private static Sensors upstairsFloorSensors, mainFloorSensors;
    private static MotionDetector mainFloorMotionDetector, upstairsFloorMotionDetector;



    public void run(){
        //Let's initialize some starting conditions
        setHouseSystem(SecuritySystem.ArmedStay);
        setOneCarGarage(GarageDoors.Closed);
        setTwoCarGarage(GarageDoors.Closed);
        setMainFloorFan(Fan.Cool);
        setUpstairsFloorFan(Fan.Cool);
        setMainFloorControlTemp(72);
        setUpstairsFloorControlTemp(72);
        setMainFloorLights(Lights.On);
        setUpstairsFloorLights(Lights.On);
        setFrontDoor(Locks.Locked);
        setBackDoor(Locks.Locked);
        setMainDoor(Locks.Locked);
        setMainFloorWindows(Windows.Closed);
        setUpstairsFloorSensors(Sensors.On);
        setMainFloorSensors(Sensors.On);
        setMainFloorMotionDetector(MotionDetector.Active);
        setUpstairsFloorMotionDetector(MotionDetector.Active);

    }

    public void setMainFloorControlTemp(int n){
        Random rn = new Random();
        this.mainFloorControlTemp = n;
        this.mainFloorCurrentTemp = rn.nextInt( (mainFloorControlTemp + 3) - (mainFloorControlTemp - 3) + 1) + (mainFloorControlTemp - 3);
    }

    public void setUpstairsFloorControlTemp(int n){
        Random rn = new Random();
        this.upstairsFloorControlTemp = n;
        this.upstairsFloorCurrentTemp = rn.nextInt( (upstairsFloorControlTemp+ 3) - (upstairsFloorControlTemp- 3) + 1) + (upstairsFloorControlTemp- 3);
    }

    public static SecuritySystem getHouseSystem() {
        return houseSystem;
    }

    public static GarageDoors getTwoCarGarage() {
        return twoCarGarage;
    }

    public static GarageDoors getOneCarGarage() {
        return oneCarGarage;
    }

    public static Fan getMainFloorFan() {
        return mainFloorFan;
    }

    public static Fan getUpstairsFloorFan() {
        return upstairsFloorFan;
    }

    public static int getMainFloorControlTemp() {
        return mainFloorControlTemp;
    }

    public static int getMainFloorCurrentTemp() {
        return mainFloorCurrentTemp;
    }

    public static int getUpstairsFloorControlTemp() {
        return upstairsFloorControlTemp;
    }

    public static int getUpstairsFloorCurrentTemp() {
        return upstairsFloorCurrentTemp;
    }

    public static Lights getMainFloorLights() {
        return mainFloorLights;
    }

    public static Lights getUpstairsFloorLights() {
        return upstairsFloorLights;
    }

    public static Locks getFrontDoor() {
        return frontDoor;
    }

    public static Locks getBackDoor() {
        return backDoor;
    }

    public static Locks getMainDoor() {
        return mainDoor;
    }

    public static Windows getMainFloorWindows() {
        return mainFloorWindows;
    }

    public static Sensors getUpstairsFloorSensors() {
        return upstairsFloorSensors;
    }

    public static Sensors getMainFloorSensors() {
        return mainFloorSensors;
    }

    public static MotionDetector getMainFloorMotionDetector() {
        return mainFloorMotionDetector;
    }

    public static MotionDetector getUpstairsFloorMotionDetector() {
        return upstairsFloorMotionDetector;
    }

    public static void setHouseSystem(SecuritySystem houseSystem) {
        ExampleRPi.houseSystem = houseSystem;
    }

    public static void setTwoCarGarage(GarageDoors twoCarGarage) {
        ExampleRPi.twoCarGarage = twoCarGarage;
    }

    public static void setOneCarGarage(GarageDoors oneCarGarage) {
        ExampleRPi.oneCarGarage = oneCarGarage;
    }

    public static void setMainFloorFan(Fan mainFloorFan) {
        ExampleRPi.mainFloorFan = mainFloorFan;
    }

    public static void setUpstairsFloorFan(Fan upstairsFloorFan) {
        ExampleRPi.upstairsFloorFan = upstairsFloorFan;
    }

    public static void setMainFloorLights(Lights mainFloorLights) {
        ExampleRPi.mainFloorLights = mainFloorLights;
    }

    public static void setUpstairsFloorLights(Lights upstairsFloorLights) {
        ExampleRPi.upstairsFloorLights = upstairsFloorLights;
    }

    public static void setFrontDoor(Locks frontDoor) {
        ExampleRPi.frontDoor = frontDoor;
    }

    public static void setBackDoor(Locks backDoor) {
        ExampleRPi.backDoor = backDoor;
    }

    public static void setMainDoor(Locks mainDoor) {
        ExampleRPi.mainDoor = mainDoor;
    }

    public static void setMainFloorWindows(Windows mainFloorWindows) {
        ExampleRPi.mainFloorWindows = mainFloorWindows;
    }

    public static void setUpstairsFloorSensors(Sensors upstairsFloorSensors) {
        ExampleRPi.upstairsFloorSensors = upstairsFloorSensors;
    }

    public static void setMainFloorSensors(Sensors mainFloorSensors) {
        ExampleRPi.mainFloorSensors = mainFloorSensors;
    }

    public static void setMainFloorMotionDetector(MotionDetector mainFloorMotionDetector) {
        ExampleRPi.mainFloorMotionDetector = mainFloorMotionDetector;
    }

    public static void setUpstairsFloorMotionDetector(MotionDetector upstairsFloorMotionDetector) {
        ExampleRPi.upstairsFloorMotionDetector = upstairsFloorMotionDetector;
    }

}
