package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UserRegistration extends AppCompatActivity {

    public EditText newFirstName, newLastName, newPassword,newEmail,newPhoneNumber, newUserName;
    public ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        dialog = new ProgressDialog(this);


        //Declare EditText
        newFirstName = findViewById(R.id.FirstName);
        newLastName = findViewById(R.id.LastName);
        newPassword= findViewById(R.id.Password);
        newEmail = findViewById(R.id.Email);
        newPhoneNumber =  findViewById(R.id.PhoneNumber);
        newUserName = findViewById(R.id.Username);

    }


    //On register button click
    public void userRegister(View view){

        final Intent intent = new Intent(this, MainActivity.class);
        final String email = newEmail.getText().toString().trim();
        final String password = newPassword.getText().toString().trim();
        final String firstName = newFirstName.getText().toString().trim();
        final String phone = newPhoneNumber.getText().toString().trim();
        final String lastName = newLastName.getText().toString().trim();
        final String username = newFirstName.getText().toString().trim();

        dialog.setMessage("Registering user....");
        dialog.show();

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println(("Response is: "+ response.toString()));

                        startActivity(intent);
                        /*
                        try {
                            JSONObject jsonObject = new JSONObject(response); // passing in JSON Object
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        */
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
                System.out.println(error.toString());
                dialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("email", email);
                params.put("phone", phone);
                return params;
            }
        };


        queue.add(stringRequest);
    }
}
