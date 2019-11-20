package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.VideoView;

public class Dashboard extends AppCompatActivity {

    private static final String webcamURL = "http://192.168.1.2:8081/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        Switch videoSwitch = findViewById(R.id.videoSwitch);
        videoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            WebView webView= findViewById(R.id.webView);
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {

                if (isChecked) {
                    webView.loadUrl(webcamURL);
                }
                else {
                    webView.loadUrl("about:blank");
                }
            }
        });


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
        startActivity(intent);
    }

}
