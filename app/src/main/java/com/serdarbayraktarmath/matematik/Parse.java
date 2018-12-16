package com.serdarbayraktarmath.matematik;

import android.app.Application;


public class Parse extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        com.parse.Parse.setLogLevel(com.parse.Parse.LOG_LEVEL_DEBUG);

        com.parse.Parse.initialize(new com.parse.Parse.Configuration.Builder(this)
                .applicationId("y7eWKXsipDzwDT7AssZM4V22uRR4udWwbjMb1SQ4")
                .clientKey("sejXxL7sKVvXyirbRj5pyLZPlnCyNrR7mBPkUCvt")
                .server("https://parseapi.back4app.com/")
                .build()




         );


    }
}
