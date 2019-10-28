package com.example.a49ersense.ExampleAppliances;

public class ExampleDryer {
    private static int dryerPower;
    private static int dryerTimerMins;
    private static int dryerPowerPerMinute;
    private static int currentRunTime;

    public ExampleDryer(int powerPerMinute, int timer){
        this.dryerPower = 0;
        this.dryerPowerPerMinute = powerPerMinute;
        this.dryerTimerMins = timer;
    }

    public ExampleDryer(){}

    public void run(){
        currentRunTime = 0;
        //Average washer uses 600 watts per load, maybe a run time of 60 minutes
        //So 10 watts / minute, we'll say 1 second is 1 minute
        while(currentRunTime < dryerTimerMins){
            delay1();
            dryerPower = currentRunTime * dryerPowerPerMinute;
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

    public int getPower(){
        return this.dryerPower;
    }

    public int getCurrentRunTime(){return this.dryerTimerMins; }
}
