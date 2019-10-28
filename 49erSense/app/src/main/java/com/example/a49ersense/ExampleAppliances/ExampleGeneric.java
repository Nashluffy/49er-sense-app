package com.example.a49ersense.ExampleAppliances;

public class ExampleGeneric extends Thread {
    private static int appliancePower;
    private static int appliancePowerPerMinute;
    private static int applianceTimer;
    private static int currentTimer;
    private static String applianceName;
    public static boolean alreadyCreated;



    public ExampleGeneric(String applianceName, int appliancePowerPerMinute, int applianceTimer){
        this.appliancePowerPerMinute = appliancePowerPerMinute;
        this.applianceTimer = applianceTimer;
        this.applianceName = applianceName;
    }

    public ExampleGeneric(){
        this.alreadyCreated = false;
    }

    public void run(){
        currentTimer = 0;
        while(currentTimer < this.applianceTimer){
            delay1();
            this.appliancePower = appliancePowerPerMinute * applianceTimer;
            currentTimer += 1;
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

    public int getAppliancePower(){
        return this.appliancePower;
    }

    public int getApplianceTimer(){return this.applianceTimer;}

    public int getCurrentRunTime(){return this.currentTimer; }

    public int getAppliancePowerPerMinute() {return this.appliancePowerPerMinute;}

    public String getApplianceName(){return this.applianceName;}

    public void setApplianceName(String p) {this.applianceName = p;}

    public void setAppliancePowerPerMinute(int p){this.appliancePowerPerMinute = p;}

}
