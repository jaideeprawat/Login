package com.example.jaideepsinghrawat.volleyexample;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText email,password,name;
    Button register;
    TextView alreadyRegistered;
    String url="http://localhost/getInfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email=findViewById(R.id.email_register);
        name=findViewById(R.id.name_register);
        password=findViewById(R.id.password_register);
        alreadyRegistered=findViewById(R.id.alreadyregistered);
        register=findViewById(R.id.register);
        register.setOnClickListener(this);
        alreadyRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alreadyregistered:
                Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register:
                registerUser();
                break;
                default:
                    break;
        }

    }

    private void registerUser() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Intent intent=new Intent(RegistrationActivity.this,UserProfile.class);
                    startActivity(intent);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> parameter = new HashMap<String, String>();
                    parameter.put("submit", "1");
                    parameter.put("email", email.getText().toString());
                    parameter.put("name",name.getText().toString());
                    parameter.put("password",password.getText().toString());
                    return parameter;
                }
            };




            VolleySingleton.getmInstance(this).addToRequest(stringRequest);
        }


}
