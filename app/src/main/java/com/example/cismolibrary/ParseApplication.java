package com.example.famucismolibrary;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        ParseObject.registerSubclass(Book.class);

        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("cis-lib") // should correspond to APP_ID env variable
                .clientKey("CodepathMoveFastParse")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://cis-lib.herokuapp.com/parse/").build());
    }
}
