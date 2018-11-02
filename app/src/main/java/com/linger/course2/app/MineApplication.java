package com.linger.course2.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MineApplication extends Application {
   private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue=Volley.newRequestQueue(this);
    }

    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
