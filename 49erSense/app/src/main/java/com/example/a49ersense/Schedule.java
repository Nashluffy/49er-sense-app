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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
import static com.example.a49ersense.FileIO.writeToFile;
import static com.example.a49ersense.FileIO.readFromFile;
import static com.example.a49ersense.FileIO.deleteEntry;
import static java.lang.Thread.sleep;

public class Schedule extends AppCompatActivity {
    static String path = scheduleFilePath;
    static boolean scheduleEmpty = false;
    static Vector<String[]> currentSchedule = new Vector<String[]>();
    Vector<EditText> listMe = new Vector<EditText>();
    String beforeText;

    public static Vector<String[]> getCurrentSchedule(){return currentSchedule;}


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
        final EditText endDate1 = findViewById(R.id.endtime1);


        final EditText appliance2= findViewById(R.id.appliance2);
        final EditText startDate2 = findViewById(R.id.startdate2);
        final EditText startTime2 = findViewById(R.id.starttime2);
        final EditText duration2 = findViewById(R.id.duration2);
        final EditText endDate2 = findViewById(R.id.endtime2);

        final EditText appliance3= findViewById(R.id.appliance3);
        final EditText startDate3 = findViewById(R.id.startdate3);
        final EditText startTime3 = findViewById(R.id.starttime3);
        final EditText duration3 = findViewById(R.id.duration3);
        final EditText endDate3 = findViewById(R.id.endtime3);

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
                                    startTime1.getText().toString().trim() + "," + duration1.getText().toString().trim(), getApplicationContext(), "schedule.txt");
                            System.out.println("Successfully wrote new line");

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
                        deleteEntry(deleteMe, getApplicationContext(), "schedule.txt");
                        clearTableView();
                        currentSchedule.clear();
                        currentSchedule = readFromFile(getApplicationContext(), "schedule.txt");
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
                                    startTime2.getText().toString().trim() + "," + duration2.getText().toString().trim(), getApplicationContext(), "schedule.txt");
                            System.out.println("Successfully wrote new line");

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
                        deleteEntry(deleteMe, getApplicationContext(), "schedule.txt");
                        clearTableView();
                        currentSchedule.clear();
                        currentSchedule = readFromFile(getApplicationContext(), "schedule.txt");
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
                            writeToDB(appliance1.toString(),startDate1.toString(),endDate1.toString(), startTime1.toString(), duration1.toString());
                            System.out.println("Successfully wrote new line");
                        } catch (Exception e) {
                            System.out.println("Something went wrong");
                        }
                    } else {
                        //This is a little more complicated... Now we have to find the line in our text file and delete it
                        String deleteMe;
                        if (startTime3.getText().toString().equals("")) {
                            deleteMe = "NOTHING TO DELETE HERE";
                        } else {
                            deleteMe = startTime3.getText().toString();
                        }
                        deleteEntry(deleteMe, getApplicationContext(), "schedule.txt");
                        clearTableView();
                        currentSchedule.clear();
                        currentSchedule = readFromFile(getApplicationContext(), "schedule.txt");
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
                                    startTime4.getText().toString().trim() + "," + duration4.getText().toString().trim(), getApplicationContext(), "schedule.txt");
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
                        deleteEntry(deleteMe, getApplicationContext(), "schedule.txt");
                        clearTableView();
                        currentSchedule.clear();
                        currentSchedule = readFromFile(getApplicationContext(), "schedule.txt");
                        setTableView();
                    }
                }
            }
        });


        try {
            File file = this.getFileStreamPath("schedule.txt");
            file.delete();
            if(file == null || !file.exists()) {
                writeToFile("Washer,10/29/2019,11:45,60\n", this, "schedule.txt");
                writeToFile("Washer,10/30/2019,15:20,30\n", this, "schedule.txt");
                writeToFile("Dryer,10/30/2019,20:08,60\n", this, "schedule.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentSchedule.clear();
        currentSchedule = readFromFile(this, "schedule.txt");
        setTableView();



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

    public void writeToDB(final String appliance, final String startDate, final String endDate, final String startTime, final String duration) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_INSERT_SCHEDULE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(("Response is: " + response.toString()));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
                System.out.println(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Appliance", appliance);
                params.put("StartDate", startDate);
                params.put("EndDate", endDate);
                params.put("StartTime", startTime);
                params.put("Duration", duration);
                return params;
            }
        };


        queue.add(stringRequest);

    }

}
