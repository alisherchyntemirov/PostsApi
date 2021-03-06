package com.example.postsapi.data.remote;

import com.example.postsapi.data.models.Post;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private OkHttpClient provideClient(){
        return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }
    private Retrofit retrofit = new Retrofit.Builder().
            baseUrl("https://android-3-mocker.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).
            client(provideClient()).
            build();

    public PostApi provideApi(){
        return retrofit.create(PostApi.class);
    }
}
