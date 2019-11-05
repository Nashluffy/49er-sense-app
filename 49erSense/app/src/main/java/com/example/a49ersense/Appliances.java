package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a49ersense.ExampleAppliances.ExampleDryer;
import com.example.a49ersense.ExampleAppliances.ExampleGeneric;
import com.example.a49ersense.ExampleAppliances.ExampleWasher;

public class Appliances extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliances);

        final ExampleWasher ew = new ExampleWasher();
        final ExampleDryer ed = new ExampleDryer();

        final Spinner appliance = findViewById(R.id.selectedAppliance);

        final Handler handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                ExampleGeneric eg = new ExampleGeneric();

                EditText applianceName = findViewById(R.id.applianceName);
                EditText appliancePowerPMin = findViewById(R.id.appliancePowerPMin);

                if (appliance.getSelectedItem().equals("Washer")) {
                    applianceName.setText("Washer");
                    appliancePowerPMin.setText("60");
                    handler.postDelayed(this, 500); // set time here to refresh textView
                }

                else if(appliance.getSelectedItem().equals(("Dryer"))){
                    applianceName.setText("Dryer");
                    appliancePowerPMin.setText("120");
                    handler.postDelayed(this, 500); // set time here to refresh textView
                }
                else{
                    if(eg.alreadyCreated){
                        applianceName.setText(eg.getApplianceName());
                        appliancePowerPMin.setText(eg.getAppliancePowerPerMinute());
                    }
                    else{
                        if((applianceName.getText().length() == 0) || (appliancePowerPMin.getText().length() == 0)){
                        }
                        else{
                            eg.setApplianceName(applianceName.getText().toString());
                            eg.setAppliancePowerPerMinute(Integer.parseInt(appliancePowerPMin.getText().toString()));
                            eg.alreadyCreated = true;
                        }
                    }
                    handler.postDelayed(this, 500); // set time here to refresh textView
                }
            }
        });

    }

    public void startAppliance(View view){
        Spinner applianceSelection = findViewById(R.id.selectedAppliance);
        if (applianceSelection.getSelectedItem().toString().equals("Washer")){
            ExampleWasher ew = new ExampleWasher(60, 35);
            ew.start();
        }
        else if (applianceSelection.getSelectedItem().toString().equals("Dryer")){
            ExampleDryer ed  = new ExampleDryer(125, 60);
            ed.start();
        }
        else{
            ExampleGeneric eg = new ExampleGeneric("Slow-Cooker", 15, 15);
            eg.start();
        }
    }

    public void startSchedule(View view){
        final Intent intent = new Intent (this, Schedule.class);
        startActivity(intent);
    }

}
