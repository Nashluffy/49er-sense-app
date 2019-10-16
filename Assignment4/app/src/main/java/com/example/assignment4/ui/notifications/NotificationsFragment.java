package com.example.assignment4.ui.notifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment4.PackageTracker;
import com.example.assignment4.R;

import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

public class NotificationsFragment extends Fragment {

    int i = 0;
    String overall = "";
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        final Button button = root.findViewById(R.id.button);
        final Spinner startingLoc = root.findViewById(R.id.spinner2);
        final Spinner endingLoc = root.findViewById(R.id.spinner3);
        final TextView packaging = root.findViewById(R.id.editText);
        final TextView dimensions = root.findViewById(R.id.editText2);

        final SQLiteDatabase mydatabase = getContext().openOrCreateDatabase("location",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS location(trackingNumber VARCHAR, count INT, location VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS package(weight VARCHAR, packaging VARCHAR, dimensions VARCHAR, trackingNumber VARCHAR)");
        mydatabase.execSQL("INSERT INTO location VALUES ('asdfadfadfs','0', 'DURHAM,NC')");

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
                TextView weight = (TextView) root.findViewById(R.id.editText3);
                System.out.println(weight.getText().toString());

                PackageTracker.Package primePackage = new PackageTracker.Package(Integer.parseInt(weight.getText().toString()), packaging.getText().toString(), dimensions.getText().toString());
                Thread packageTrack1 = new Thread(new PackageTracker());
                packageTrack1.start();

                try {
                    packageTrack1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                TextView theTextView = root.findViewById(R.id.textView4);
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
}