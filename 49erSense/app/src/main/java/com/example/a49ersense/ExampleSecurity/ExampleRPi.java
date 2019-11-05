package com.example.a49ersense.ExampleSecurity;

import java.util.Random;

public class ExampleRPi extends Thread {
    public enum SecuritySystem{Disarmed, ArmedStay, ArmedAway}
    public enum GarageDoors{Open, Closed}
    public enum Fan{Heat, Cool, Off}
    public enum Lights{On, Off}
    public enum Locks{Locked, Unlocked}
    public enum Windows{Open, Closed}
    public enum Sensors{On, Off}
    public enum MotionDetector{Active, Inactive}

    private static SecuritySystem houseSystem;
    private static GarageDoors twoCarGarage,oneCarGarage;
    private static Fan mainFloorFan, upstairsFloorFan;
    private static int mainFloorControlTemp,mainFloorCurrentTemp, upstairsFloorControlTemp, upstairsFloorCurrentTemp;
    private static Lights mainFloorLights;
    private static Locks mainLocks;
    private static Windows mainFloorWindows;
    private static Sensors mainSensors;
    private static MotionDetector mainFloorMotionDetector;

    public static boolean houseSystemTripped = false,mainFloorTripped = false, mainSensorTripped = false, mainFloorMotionTripped = false, upstairMotionSensorTripped = false;

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
        setMainLocks(Locks.Locked);
        setMainFloorWindows(Windows.Closed);
        setMainSensors(Sensors.On);
        setMainFloorMotionDetector(MotionDetector.Active);

    }

    public static boolean getHouseSystemTripped(){
        return houseSystemTripped;
    }

    public static void setHouseSystemTripped(){
        if((getHouseSystem().equals(SecuritySystem.ArmedAway) || getHouseSystem().equals(SecuritySystem.ArmedStay))){
            houseSystemTripped = true;
        }
        else{
            houseSystemTripped = false;
        }
    }

    public static boolean getMainFloorMotionTripped(){
        return mainFloorMotionTripped;
    }

    public static void setMainFloorMotionTripped(){
        if(getMainFloorMotionDetector().equals(MotionDetector.Active)){
            mainFloorMotionTripped = true;
        }
        else{
            mainFloorMotionTripped = false;
        }
    }


    public static boolean getMainSensorTripped(){
        return mainSensorTripped;
    }

    public static void setMainSensorTripped(){
        if(getMainSensors().equals(Sensors.On)){
            mainSensorTripped = true;
        }
        else{
            mainSensorTripped = false;
        }
    }

    public static boolean getMainFloorTripped(){
        return houseSystemTripped;
    }

    public static void setMainFloorTripped(){
        if(getMainLocks().equals(Locks.Locked)){
            mainFloorTripped = true;
        }
        else{
            mainFloorTripped = false;
        }
    }

    public static void setMainFloorControlTemp(int n){
        Random rn = new Random();
        mainFloorControlTemp = n;
        mainFloorCurrentTemp = rn.nextInt( (mainFloorControlTemp + 3) - (mainFloorControlTemp - 3) + 1) + (mainFloorControlTemp - 3);
    }

    public static void setUpstairsFloorControlTemp(int n){
        Random rn = new Random();
        upstairsFloorControlTemp = n;
        upstairsFloorCurrentTemp = rn.nextInt( (upstairsFloorControlTemp+ 3) - (upstairsFloorControlTemp- 3) + 1) + (upstairsFloorControlTemp- 3);
    }

    public static String getHouseSystem() {
        String returnValue;
        if (houseSystem == SecuritySystem.ArmedAway){
            returnValue = "ArmedAway";
        }
        else if(houseSystem == SecuritySystem.ArmedStay){
            returnValue = "ArmedStay";
        }
        else{
            returnValue = "Disarmed";
        }

        return returnValue;
    }

    public static GarageDoors getTwoCarGarage() {
        return twoCarGarage;
    }

    public static String getOneCarGarage() {
        String returnValue;
        if(ExampleRPi.oneCarGarage == GarageDoors.Open){
            returnValue = "Open";
        }
        else{
            returnValue = "Closed";
        }
        return returnValue;
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

    public static String getMainFloorLights() {
        String returnValue;
        if(ExampleRPi.mainFloorLights == Lights.Off){
            returnValue = "Main Floor Lights - Off";
        }
        else{
            returnValue = "Main Floor Lights - On";
        }
        return returnValue;
    }

    public static String getUpstairsFloorLights() {
        String returnValue = "";

        return returnValue;
    }

    public static String getMainLocks() {
        String returnValue;
        if (ExampleRPi.mainLocks == Locks.Locked) {
            returnValue = "All Doors - Locked";
        }
        else{
            returnValue = "All Doors - Unlocked";
        }
        return returnValue;
    }

    public static String getMainFloorWindows() {
        String returnValue;
        if(ExampleRPi.mainFloorWindows.equals(ExampleRPi.Windows.Closed)){
            returnValue = "Closed";
        }
        else{
            returnValue = "Open";
        }
        return returnValue;
    }

    public static String getMainSensors() {
        String returnValue;
        if(ExampleRPi.mainSensors.equals(Sensors.On)){
            returnValue = "On";
        }
        else{
            returnValue = "Off";
        }
        return returnValue;
    }



    public static String getMainFloorMotionDetector() {
        String returnValue;
        if(ExampleRPi.mainFloorMotionDetector.equals(MotionDetector.Active)){
            returnValue = "On";
        }
        else{
            returnValue = "Off";
        }
        return returnValue;
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



    public static void setMainLocks(Locks mainLocks) {
        ExampleRPi.mainLocks = mainLocks;
    }

    public static void setMainFloorWindows(Windows mainFloorWindows) {
        ExampleRPi.mainFloorWindows = mainFloorWindows;
    }

    public static void setMainSensors(Sensors mainSensors) {
        ExampleRPi.mainSensors = mainSensors;
    }

    public static void setMainFloorMotionDetector(MotionDetector mainFloorMotionDetector) {
        ExampleRPi.mainFloorMotionDetector = mainFloorMotionDetector;
    }

}
