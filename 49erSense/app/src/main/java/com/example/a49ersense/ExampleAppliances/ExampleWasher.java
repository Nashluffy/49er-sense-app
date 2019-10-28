package com.example.a49ersense.ExampleAppliances;

public class ExampleWasher extends Thread {

    private static int washerPowerPerMinute;
    private static int washerTimerMins;
    private static int washerPower;
    private static int currentRunTime;

    public ExampleWasher(int powerPerMinute, int timer){
        this.washerPower = 0;
        this.washerTimerMins = timer;
        this.washerPowerPerMinute = powerPerMinute;
    }

    public ExampleWasher(){}

    public void run(){
        currentRunTime = 0;
        //We'll use whatever the dryer power usage is per minute, where 1 second is a minute
        while(currentRunTime < washerTimerMins){
            delay1();
            washerPower = currentRunTime * washerPowerPerMinute;
            currentRunTime += 1;
        }
    }

    private void delay1(){
        try {
            synchronized (this){
                this.wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int  getWasherPower(){
        return washerPower;
    }

    public int getWasherTimer() {return washerTimerMins;}

    public int getCurrentRunTime(){return currentRunTime;}

    public int getWasherPowerPerMinute(){return this.getWasherPowerPerMinute();}

    public void setWasherPowerPerMinute(int ppm){ this.washerPowerPerMinute = ppm;}

}
