package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a49ersense.ExampleAppliances.ExampleDryer;
import com.example.a49ersense.ExampleAppliances.ExampleGeneric;
import com.example.a49ersense.ExampleAppliances.ExampleWasher;

public class Energy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);



        final ExampleWasher ew = new ExampleWasher();
        final ExampleDryer ed = new ExampleDryer();
        final ExampleGeneric eg = new ExampleGeneric();
        final Spinner appliance = findViewById(R.id.spinner9);

        final Handler handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                TextView exampleApplianceEnergy = findViewById(R.id.energyUsed);
                TextView exampleApplianceTimer = findViewById(R.id.timeRan);

                if (appliance.getSelectedItem().equals("Washer")) {
                    exampleApplianceEnergy.setText("Total Energy Used: " + ew.getWasherPower());
                    exampleApplianceTimer.setText("Current Run Time: " + ew.getCurrentRunTime());

                    System.out.println(ew.getWasherPower());
                    System.out.println( "This is the text: "+ exampleApplianceEnergy.getText().toString());
                    handler.postDelayed(this, 500); // set time here to refresh textView
                }
                else if(appliance.getSelectedItem().equals(("Dryer"))){
                    exampleApplianceEnergy.setText("Total Energy Used: " + ed.getPower());
                    exampleApplianceTimer.setText("Current Run Time: " + ed.getCurrentRunTime());
                    //System.out.println(ed.getPower());
                    handler.postDelayed(this, 500); // set time here to refresh textView
                }
                else{
                    exampleApplianceEnergy.setText("Total Energy Used: " + eg.getAppliancePower());
                    exampleApplianceTimer.setText("Current Run Time: " + eg.getCurrentRunTime());
                    System.out.println(ew.getWasherPower());
                    System.out.println("Entered Random Loop" + appliance.getSelectedItem());
                    handler.postDelayed(this, 500); // set time here to refresh textView
                }
            }
        });
    }
}
