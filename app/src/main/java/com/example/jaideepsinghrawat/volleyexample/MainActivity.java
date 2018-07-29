package com.example.jaideepsinghrawat.volleyexample;
//chekcing commit
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextInputEditText email,name,password;
String url="http://192.168.0.29/getInfo.php";
    private Button login;
    private TextView notRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        notRegister=findViewById(R.id.notregister);
        login.setOnClickListener(this);
        notRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login:
                login();
                break;
            case R.id.notregister:
                Intent intent=new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();
                break;
                default:
                    return;
            }

        }


    private void login() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String email,name,password;
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    for(int i=0;jsonObject.length()>0;i++){
                        String message =jsonObject.getString("msg");
                        String data=jsonObject.getString("data");
                        JSONObject onjo=new JSONObject(data);
                        for(int j=0;onjo.length()>0;j++){
                           email= onjo.getString("email");
                            name=onjo.getString("name");
                            password=onjo.getString("password");
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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