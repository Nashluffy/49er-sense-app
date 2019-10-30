package com.example.a49ersense;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.a49ersense.ExampleAppliances.ExampleDryer;
import com.example.a49ersense.ExampleAppliances.ExampleGeneric;
import com.example.a49ersense.ExampleAppliances.ExampleWasher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

import static com.example.a49ersense.Schedule.getCurrentSchedule;

public class ScheduleThread extends Thread {
    boolean washerAlreadyStarted = false, dryerAlreadyStarted = false, genericAlreadyStarted = false;
    Vector<String[]> currentSchedule = new Vector<String[]>();

    public void run(){
        while(currentSchedule.isEmpty()){
            System.out.println("Schedule is still null");
            currentSchedule = getCurrentSchedule();
            sleep10();
        }
        while(true){
            System.out.println("Schedule is not null anymore");
            monitorSchedule();
            sleep10();
        }
    }

    private void monitorSchedule(){
        currentSchedule = getCurrentSchedule();
        for(String[] currentEntry:currentSchedule){
            SimpleDateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat formatterStartTime = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            if (currentEntry[1].equals(formatterDate.format(date))) continue;
            System.out.println("Dates are equal! Current time: " + formatterStartTime.format(date) + " And Entry time: " + currentEntry[2]);
            if (currentEntry[2].equals(formatterStartTime.format(date))) continue;
            System.out.println("Times are equal!");
            if (currentEntry[0].equals("Washer")){
                if (washerAlreadyStarted){}
                else {
                    ExampleWasher ew = new ExampleWasher(60, Integer.parseInt(currentEntry[3]));
                    ew.start();
                    System.out.println("Washer started due to schedule");
                    washerAlreadyStarted = true;
                }
            }
            else if (currentEntry[0].equals("Dryer")){
                if (dryerAlreadyStarted){}
                else {
                    ExampleDryer ed = new ExampleDryer(125, Integer.parseInt(currentEntry[3]));
                    ed.start();
                    System.out.println("Dryer started due to schedule");
                    dryerAlreadyStarted = true;
                }
            }
            else if (currentEntry[0].equals("Generic")){
                if (genericAlreadyStarted){}
                else {
                    ExampleGeneric egStats = new ExampleGeneric();
                    ExampleGeneric eg = new ExampleGeneric("Generic", egStats.getAppliancePowerPerMinute(), Integer.parseInt(currentEntry[3]));
                    eg.start();
                    System.out.println("Generic started due to schedule");
                    genericAlreadyStarted = true;
                }
            }
        }
    }

    private void sleep10(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
