package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a49ersense.ExampleSecurity.ExampleRPi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static com.example.a49ersense.FileIO.readFromFile;
import static com.example.a49ersense.FileIO.writeToFile;
import static java.lang.String.valueOf;

public class Security extends AppCompatActivity {

    Vector<String[]> sensorStates = new Vector<String[]>();
    enum SecuritySystem{Disarmed, ArmedStay, ArmedAway}
    enum GarageDoors{Open, Closed}
    enum Fan{Heat, Cool, Off}
    enum Lights{On, Off}
    enum Locks{Locked, Unlocked}
    enum Windows{Open, Closed}
    enum Sensors{On, Off}
    enum MotionDetector{Active, Inactive}
    public static Vector<Map<String,String>> allValues = new Vector<Map<String,String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        getSupportActionBar().hide();
        readInitialValues();

        final Spinner securitySystem = findViewById(R.id.securitySystem);
        final Spinner garageDoors = findViewById(R.id.garageDoors);
        final Spinner lights = findViewById(R.id.lights);
        final Spinner locks = findViewById(R.id.locks);
        final Spinner doorWindow = findViewById(R.id.DoorWindow);
        final Spinner sensors = findViewById(R.id.sensors);
        final Spinner motionDetector = findViewById(R.id.motionDetector);

        //securitySystem.setSelection(getIndex(securitySystem, allValues.get(0).get("HouseSystem")));
        //garageDoors.setSelection(getIndex(garageDoors, allValues.get(2).get("GarageDoors")));

        securitySystem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sensorState = securitySystem.getSelectedItem().toString().trim();
                System.out.println("Current state: " + ExampleRPi.getHouseSystem());
                writeToDB("HouseSystem", sensorState, "false");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        garageDoors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sensorState = garageDoors.getSelectedItem().toString().trim();
                writeToDB("GarageDoors" , sensorState, "null");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });



        lights.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sensorState = lights.getSelectedItem().toString().trim();
                writeToDB("Lights" , sensorState, "null");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        locks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sensorState = locks.getSelectedItem().toString().trim();
                writeToDB("Locks", sensorState, "false");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        doorWindow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sensorState = doorWindow.getSelectedItem().toString().trim();
                writeToDB("Windows", sensorState, "false");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        sensors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sensorState = sensors.getSelectedItem().toString().trim();
                writeToDB("Sensors", sensorState, "false");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        motionDetector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sensorState = motionDetector.getSelectedItem().toString().trim();
                writeToDB("MotionDetector", sensorState, "false");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    public void readInitialValues(){
        allValues.add(readFromDB("HouseSystem"));
        allValues.add(readFromDB("Locks"));
        allValues.add(readFromDB("GarageDoors"));
        allValues.add(readFromDB("Lights"));
        allValues.add(readFromDB("Sensors"));
        allValues.add(readFromDB("Windows"));
        allValues.add(readFromDB("MotionDetector"));
    }


    public Map<String, String> readFromDB(final String sensorName){
        final Map<String, String> returnMap = new HashMap<String, String>();
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_READ_SENSORS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String turnToMap;
                        turnToMap = response.substring(1);
                        turnToMap = response.substring(3, turnToMap.length());
                        turnToMap = turnToMap.substring(0, turnToMap.length() - 4);
                        String[] initialSplit = turnToMap.split(",");
                        for (int i =0; i < initialSplit.length; i++){
                            String[] KVP = initialSplit[i].split(":");
                            KVP[0] = KVP[0].substring(1);
                            KVP[0] = KVP[0].substring(0, KVP[0].length() - 1);
                            KVP[1] = KVP[1].substring(1);
                            KVP[1] = KVP[1].substring(0, KVP[1].length() - 1);
                            System.out.println("Key: " + KVP[0] + " Value: " + KVP[1]);
                            returnMap.put(KVP[0], KVP[1]);
                        }
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
                params.put("SensorName", sensorName);
                return params;
            }
        };
        queue.add(stringRequest);
        return returnMap;
    }


    public void writeToDB(final String sensorName, final String sensorState, final String sensorTripped) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATE_SENSORS,
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
                params.put("SensorName", sensorName);
                params.put("SensorState", sensorState);
                params.put("SensorTripped", sensorTripped);
                return params;
            }
        };


        queue.add(stringRequest);

    }

    public void testSystem(View view) {
        for (Map<String, String> currentSensor : allValues) {
            if (!currentSensor.get("SensorTripped").equals("null") && currentSensor.get("SensorState").equals("Armed")) {
                writeToDB(currentSensor.get("SensorName"), currentSensor.get("SensorState"), "true");
            }
            else if (!currentSensor.get("SensorTripped").equals("null") && currentSensor.get("SensorState").equals("Closed")) {
                writeToDB(currentSensor.get("SensorName"), currentSensor.get("SensorState"), "true");
            }
            else if (!currentSensor.get("SensorTripped").equals("null") && currentSensor.get("SensorState").equals("Active")) {
                writeToDB(currentSensor.get("SensorName"), currentSensor.get("SensorState"), "true");
            }
            else if (!currentSensor.get("SensorTripped").equals("null") && currentSensor.get("SensorState").equals("Locked")) {
                writeToDB(currentSensor.get("SensorName"), currentSensor.get("SensorState"), "true");
            }
            else if (!currentSensor.get("SensorTripped").equals("null") && currentSensor.get("SensorState").equals("On")) {
                writeToDB(currentSensor.get("SensorName"), currentSensor.get("SensorState"), "true");
            }
        }
    }

    /*private int getIndex(Spinner spinner, String myString){
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
     */

}
