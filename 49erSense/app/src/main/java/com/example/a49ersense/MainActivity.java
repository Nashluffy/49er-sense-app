package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a49ersense.ExampleAppliances.ExampleGeneric;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    public EditText loginUsername, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
    }

    //On login button click
    public void userLogin(View view){
        final String username = loginUsername.getText().toString().trim();
        final String password = loginPassword.getText().toString().trim();
        final Intent intent = new Intent(this, Dashboard.class);
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println(("Response is: "+ response.toString()));
                        if (response.toString().length() == 4 ){
                            System.out.println("Real user!");
                            startActivity(intent);
                        }
                        else{
                            System.out.println("Not real user");
                            Toast.makeText(getApplicationContext(),"Incorrect Login Information!",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
                System.out.println(error.toString());

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };


        queue.add(stringRequest);

    }


    //On register button click
    public void userRegister(View view){
        Intent intent = new Intent(this, UserRegistration.class);
        startActivity(intent);
    }
}
