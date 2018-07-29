package com.example.jaideepsinghrawat.volleyexample;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue requestQueue;
    private Context context;
    private VolleySingleton(Context context){
        this.context=context;
        requestQueue=getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue=Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleySingleton getmInstance(Context context){
        if(mInstance==null){
            mInstance=new VolleySingleton(context);
        }
        return mInstance;
    }
public <T> void addToRequest(Request<T> request){
        getRequestQueue().add(request);

}
}
