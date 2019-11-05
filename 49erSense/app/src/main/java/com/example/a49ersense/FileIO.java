package com.example.a49ersense;

import android.content.Context;
import android.util.Log;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static android.content.Context.MODE_APPEND;

public class FileIO {

    public static Vector<String[]> readFromFile(Context context, String fileName) {
        Vector<String[]> scheduleVec = new Vector<String[]>();
        try {
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
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

    public static void writeToFile(String data, Context context, String fileName) throws IOException
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static void deleteEntry(String lineToRemove,Context context, String fileName) {

        try {
            //Setup the input (old schedule file) and new file (tempSchedule)
            InputStream inputStream = context.openFileInput(fileName);
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
                File from = new File(context.getFilesDir(), "tempSchedule.txt");
                File to = new File(context.getFilesDir(),fileName);
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
}
