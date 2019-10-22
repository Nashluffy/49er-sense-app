package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();

    }

    public void goWeather(View view){
        final Intent intent = new Intent(this, Weather.class);
        startActivity(intent);
    }

    public void goControl(View view){
        final Intent intent = new Intent(this, Security.class);
        startActivity(intent);
    }

    public void goAppliances(View view){
        final Intent intent = new Intent( this, Appliances.class);
        startActivity(intent);
    }

    public void goNotifications(View view){
        final Intent intent = new Intent(this, Notifications.class);
        startActivity(intent);
    }

    public void goEnergy(View view){
        final Intent intent = new Intent (this, Energy.class);
    }


}
