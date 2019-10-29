package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static android.content.Context.*;
import static com.example.a49ersense.Constants.scheduleFilePath;
import static java.lang.Thread.sleep;

public class Schedule extends AppCompatActivity {
    static String path = scheduleFilePath;
    static boolean scheduleEmpty = false;
    Vector<String[]> currentSchedule = new Vector<String[]>();
    Vector<EditText> listMe = new Vector<EditText>();
    String beforeText;

    /*
    * The general idea of the schedule is to read and write from a file stored on the clients computer
    * By having it client-side, all handling of schedules can be done on this side as compared to server side where multiple servers may exist
    *
    * The general format is
    * Appliance Name, Start Date (MM/DD/YY), Start Hour + Minute (HH,MM), Duration (MM)
    * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        createListOfElements();
        final EditText appliance1= findViewById(R.id.appliance1);
        final EditText startDate1 = findViewById(R.id.startdate1);
        final EditText startTime1 = findViewById(R.id.starttime1);
        final EditText duration1 = findViewById(R.id.duration1);


        final EditText appliance2= findViewById(R.id.appliance2);
        final EditText startDate2 = findViewById(R.id.startdate2);
        final EditText startTime2 = findViewById(R.id.starttime2);
        final EditText duration2 = findViewById(R.id.duration2);


        final EditText appliance3= findViewById(R.id.appliance3);
        final EditText startDate3 = findViewById(R.id.startdate3);
        final EditText startTime3 = findViewById(R.id.starttime3);
        final EditText duration3 = findViewById(R.id.duration3);


        final EditText appliance4= findViewById(R.id.appliance4);
        final EditText startDate4 = findViewById(R.id.startdate4);
        final EditText startTime4 = findViewById(R.id.starttime4);
        final EditText duration4 = findViewById(R.id.duration4);

        duration1.setOnFocusChangeListener(new View.OnFocusChangeListener() {



            @Override
            public void onFocusChange(View view, boolean b) {
                //Two cases, either the duration is now empty or the something has been added
                //In the case that something has been added, we just need to write to our file now
                if (!duration1.hasFocus()) {
                    if (duration1.getText().length() != 0) {
                        try {
                            writeToFile(appliance1.getText().toString().trim() + "," + startDate1.getText().toString().trim() + "," +
                                    startTime1.getText().toString().trim() + "," + duration1.getText().toString().trim(), getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //This is a little more complicated... Now we have to find the line in our text file and delete it
                        String deleteMe;
                        if (startTime1.getText().toString().equals("")) {
                            deleteMe = "NOTHING TO DELETE HERE";
                        } else {
                            deleteMe = startTime1.getText().toString();
                        }
                        deleteEntry(deleteMe, getApplicationContext());
                        clearTableView();
                        currentSchedule.clear();
                        currentSchedule = readFromFile(getApplicationContext());
                        setTableView();
                    }
                }
            }
        });

        duration2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                //Two cases, either the duration is now empty or the something has been added
                //In the case that something has been added, we just need to write to our file now
                if (!duration2.hasFocus()) {
                    if (duration2.getText().length() != 0) {
                        try {
                            writeToFile(appliance2.getText().toString().trim() + "," + startDate2.getText().toString().trim() + "," +
                                    startTime2.getText().toString().trim() + "," + duration2.getText().toString().trim(), getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //This is a little more complicated... Now we have to find the line in our text file and delete it
                        String deleteMe;
                        if (startTime2.getText().toString().equals("")) {
                            deleteMe = "NOTHING TO DELETE HERE";
                        } else {
                            deleteMe = startTime2.getText().toString();
                        }
                        deleteEntry(deleteMe, getApplicationContext());
                        clearTableView();
                        currentSchedule.clear();
                        currentSchedule = readFromFile(getApplicationContext());
                        setTableView();
                    }
                }
            }
        });

        duration3.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                //Two cases, either the duration is now empty or the something has been added
                //In the case that something has been added, we just need to write to our file now
                if (!duration3.hasFocus()) {
                    if (duration3.getText().length() != 0) {
                        try {
                            writeToFile(appliance3.getText().toString().trim() + "," + startDate3.getText().toString().trim() + "," +
                                    startTime3.getText().toString().trim() + "," + duration3.getText().toString().trim(), getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //This is a little more complicated... Now we have to find the line in our text file and delete it
                        String deleteMe;
                        if (startTime3.getText().toString().equals("")) {
                            deleteMe = "NOTHING TO DELETE HERE";
                        } else {
                            deleteMe = startTime3.getText().toString();
                        }
                        deleteEntry(deleteMe, getApplicationContext());
                        clearTableView();
                        currentSchedule.clear();
                        currentSchedule = readFromFile(getApplicationContext());
                        setTableView();
                    }
                }
            }
        });


        duration4.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                //Two cases, either the duration is now empty or the something has been added
                //In the case that something has been added, we just need to write to our file now
                if (!duration4.hasFocus()) {
                    if (duration4.getText().length() != 0) {
                        try {
                            writeToFile(appliance4.getText().toString().trim() + "," + startDate4.getText().toString().trim() + "," +
                                    startTime4.getText().toString().trim() + "," + duration4.getText().toString().trim(), getApplicationContext());
                            System.out.println("Successfully wrote new line");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //This is a little more complicated... Now we have to find the line in our text file and delete it
                        String deleteMe;
                        if (startTime4.getText().toString().equals("")) {
                            deleteMe = "NOTHING TO DELETE HERE";
                        } else {
                            deleteMe = startTime4.getText().toString();
                        }
                        deleteEntry(deleteMe, getApplicationContext());
                        clearTableView();
                        currentSchedule.clear();
                        currentSchedule = readFromFile(getApplicationContext());
                        setTableView();
                    }
                }
            }
        });


        try {
            File file = this.getFileStreamPath("schedule.txt");
            file.delete();
            if(file == null || !file.exists()) {
                writeToFile("Washer,10/29/19,11:45,60\n", this);
                writeToFile("Dryer,10/29/19,11:50,60\n", this);
                writeToFile("Washer,10/30/19,15:20,30\n", this);
                writeToFile("Dryer,10/30/19,19:10,60\n", this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentSchedule.clear();
        currentSchedule = readFromFile(this);
        setTableView();



    }

    public static void writeToFile(String data, Context context) throws IOException
    {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("schedule.txt", MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void deleteEntry(String lineToRemove,Context context) {

        try {
            //Setup the input (old schedule file) and new file (tempSchedule)
            InputStream inputStream = context.openFileInput("schedule.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("tempSchedule.txt", MODE_APPEND));
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);

                String currentLine;

                while((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if(trimmedLine.contains(lineToRemove)){
                    }else{
                        writer.write(trimmedLine + "\n");
                    }

                }
                writer.close();
                reader.close();
                File from = new File(getFilesDir(), "tempSchedule.txt");
                File to = new File(getFilesDir(),"schedule.txt");
                to.delete();
                if(from.exists()){
                    from.renameTo(to);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private Vector<String[]> readFromFile(Context context) {

        Vector<String[]> scheduleVec = new Vector<String[]>();
        try {
            InputStream inputStream = context.openFileInput("schedule.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                System.out.println("Printing out current schedule.txt");
                scheduleVec.clear();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    System.out.println(receiveString);
                    String[] fourColumns = receiveString.split(",");
                    scheduleVec.add(fourColumns);
                }

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return scheduleVec;
    }

    private void setTableView(){
        int i = 0;
        String applianceText, startDateText, startTimeText, durationText;
        for (String[] currentAppliance:currentSchedule){
            EditText appliance = null,startDate = null, startTime = null, duration = null;
            if (i == 0){
                appliance = findViewById(R.id.appliance1);
                startDate = findViewById(R.id.startdate1);
                startTime = findViewById(R.id.starttime1);
                duration = findViewById(R.id.duration1);
            }
            else if (i == 1){
                appliance = findViewById(R.id.appliance2);
                startDate = findViewById(R.id.startdate2);
                startTime = findViewById(R.id.starttime2);
                duration = findViewById(R.id.duration2);
            }

            else if (i == 2){
                appliance = findViewById(R.id.appliance3);
                startDate = findViewById(R.id.startdate3);
                startTime = findViewById(R.id.starttime3);
                duration = findViewById(R.id.duration3);
            }

            else if (i == 3){
                appliance = findViewById(R.id.appliance4);
                startDate = findViewById(R.id.startdate4);
                startTime = findViewById(R.id.starttime4);
                duration = findViewById(R.id.duration4);
            }
            else if (i== 4){
                appliance = findViewById(R.id.appliance5);
                startDate = findViewById(R.id.startdate5);
                startTime = findViewById(R.id.starttime5);
                duration = findViewById(R.id.duration5);
            }
            appliance.setText(currentAppliance[0]);
            startDate.setText(currentAppliance[1]);
            startTime.setText(currentAppliance[2]);
            duration.setText(currentAppliance[3]);
            i++;
        }
    }

    private void clearTableView(){
        for(EditText currentEditText:listMe){
            currentEditText.setText("");
        }
    }

    private void createListOfElements(){


        EditText appliance1= findViewById(R.id.appliance1);
        EditText startDate1 = findViewById(R.id.startdate1);
        EditText startTime1 = findViewById(R.id.starttime1);
        EditText duration1 = findViewById(R.id.duration1);
        listMe.add(appliance1);
        listMe.add(startDate1);
        listMe.add(startTime1);
        listMe.add(duration1);

        EditText appliance2= findViewById(R.id.appliance2);
        EditText startDate2 = findViewById(R.id.startdate2);
        EditText startTime2 = findViewById(R.id.starttime2);
        EditText duration2 = findViewById(R.id.duration2);
        listMe.add(appliance2);
        listMe.add(startDate2);
        listMe.add(startTime2);
        listMe.add(duration2);

        EditText appliance3= findViewById(R.id.appliance3);
        EditText startDate3 = findViewById(R.id.startdate3);
        EditText startTime3 = findViewById(R.id.starttime3);
        EditText duration3 = findViewById(R.id.duration3);
        listMe.add(appliance3);
        listMe.add(startDate3);
        listMe.add(startTime3);
        listMe.add(duration3);

        EditText appliance4= findViewById(R.id.appliance4);
        EditText startDate4 = findViewById(R.id.startdate4);
        EditText startTime4 = findViewById(R.id.starttime4);
        EditText duration4 = findViewById(R.id.duration4);
        listMe.add(appliance4);
        listMe.add(startDate4);
        listMe.add(startTime4);
        listMe.add(duration4);

        EditText appliance5= findViewById(R.id.appliance5);
        EditText startDate5 = findViewById(R.id.startdate5);
        EditText startTime5 = findViewById(R.id.starttime5);
        EditText duration5 = findViewById(R.id.duration5);
        listMe.add(appliance5);
        listMe.add(startDate5);
        listMe.add(startTime5);
        listMe.add(duration5);


    }

}
