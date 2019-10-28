package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.a49ersense.Constants.scheduleFilePath;

public class Schedule extends AppCompatActivity {
    String path = scheduleFilePath;
    boolean append_to_file = true;
    /*
    * The general idea of the schedule is to read and write from a file stored on the clients computer
    * By having it client-side, all handling of schedules can be done on this side as compared to server side where multiple servers may exist
    *
    * The general format is
    * Appliance Name, Start Date (MM/YY/DD), Start Hour + Minute (HH,MM), Duration (MM)
    * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        try {
            writeToFile("Testing me come on!!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void newTask(){
    }


    public void writeToFile( String textLine ) throws IOException {
        FileWriter write = new FileWriter( path , append_to_file);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s" + "%n" , textLine);
        print_line.close();
    }


}
