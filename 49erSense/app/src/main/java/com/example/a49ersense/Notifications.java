package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.util.Map;

public class Notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        updateNotifications();
    }

    void updateNotifications(){
        TextView notifications = findViewById(R.id.textView15);
        StringBuilder str = new StringBuilder();
        str.append(notifications.toString());
        for (Map<String, String> currentSensor : Security.allValues) {
            if (currentSensor.get("SensorTripped").equals("true")) {
                str.append(currentSensor.get("SensorName") + " has been tripped! \n");
                System.out.println(currentSensor.get("SensorName") + " has been tripped!");
            }
        }
        notifications.setText(str.toString());
    }
}
