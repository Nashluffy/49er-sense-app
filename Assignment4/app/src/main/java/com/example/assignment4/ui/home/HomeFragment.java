package com.example.assignment4.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignment4.PackageTracker;
import com.example.assignment4.R;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    int i = 0;
    String overall = "";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        final Button button = root.findViewById(R.id.button2);
        final Spinner startingLoc = root.findViewById(R.id.spinner);
        final Spinner endingLoc = root.findViewById(R.id.spinner5);
        final TextView packaging = root.findViewById(R.id.editText);
        final TextView dimensions = root.findViewById(R.id.editText2);
        final TextView weight = root.findViewById(R.id.editText3);

        final SQLiteDatabase mydatabase = getContext().openOrCreateDatabase("location",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS location(trackingNumber VARCHAR, count INT, location VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS package(weight VARCHAR, packaging VARCHAR, dimensions VARCHAR, trackingNumber VARCHAR)");


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(weight.getText().toString());

                PackageTracker.Package primePackage = new PackageTracker.Package(Integer.parseInt(weight.getText().toString()), packaging.getText().toString(), dimensions.getText().toString());
                PackageTracker.startingLocation = startingLoc.toString();
                PackageTracker.endLocation = endingLoc.toString();
                Thread packageTrack1 = new Thread(new PackageTracker());
                packageTrack1.start();

                try {
                    packageTrack1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                PackageTracker.shipPackage(primePackage);

                PackageTracker.trackPackage(primePackage.getTrackingNumber());

                TextView theTextView = root.findViewById(R.id.textView8);
                Cursor result = mydatabase.rawQuery("SELECT location FROM location where location = 'DURHAM,NC'", null);
                result.moveToFirst();
                overall = result.getString(0);
                //overall += "Click Registered Cunt" + i + "\n";
                theTextView.setText(overall);
                i++;
            }
        });

        return root;
    }
    private void addPackage() {

        try {
            SQLiteStatement stmt = mydatabase.compileStatement("SELECT * FROM Country WHERE code = ?");
            stmt.bindString(1, "US");
            stmt.execute();
            String query = "INSERT INTO packages (vcTrackingNumber, vcDimensions, vcPackaging, intWeight)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, trackingNumber);
            preparedStmt.setString (2, dimensions);
            preparedStmt.setString   (3, packaging);
            preparedStmt.setInt(4, weight);
            preparedStmt.execute();
        }
        catch(Exception e) {System.out.println(e);}
    }
}