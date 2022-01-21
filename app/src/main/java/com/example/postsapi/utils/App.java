package com.example.postsapi.utils;

import android.app.Application;

import com.example.postsapi.data.remote.PostApi;
import com.example.postsapi.data.remote.RetrofitClient;

public class App extends Application {
    public static PostApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient client = new RetrofitClient();
        api=client.provideApi();
    }
}
