package com.example.assignment4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import com.example.assignment4.PackageTracker;

import static android.content.Context.MODE_PRIVATE;

public class dbHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Assignment4";

    public dbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS location(trackingNumber VARCHAR, count INT, location VARCHAR);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS package(weight VARCHAR, packaging VARCHAR, dimensions VARCHAR, trackingNumber VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS location");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS package");
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void sendSingleLocation(String location, PackageTracker.Package p) {
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO packageLocation (vcTrackingNumber, intNodesTraveled, vcLocation) VALUES (?,?,?)";
            //PreparedStatement preparedStmt = con.prepareStatement(query);
            //preparedStmt.setString (1, p.getTrackingNumber());
            //preparedStmt.setInt (2, p.lastLocation);
            //preparedStmt.setString (3, location);
            //preparedStmt.execute();
            //lastLocation += 1;
        }
        catch(Exception e) {//System.out.println(e); lastLocation -= 1;}
        }
        }
}
