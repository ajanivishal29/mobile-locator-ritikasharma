package com.calleridname.calldetailcallhistory.Main_Ads.retrofit;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anupamchugh on 05/01/17.
 */

public class APIClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(getIp("aHR0cHM6Ly9tdGxtZWRpYS54eXov"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static Retrofit getweatherclient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(getIp("aHR0cHM6Ly9hcGkub3BlbndlYXRoZXJtYXAub3JnLw=="))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;

    }

    public static String getIp(String url)
    {
        byte[] data = Base64.decode(url, Base64.DEFAULT);
        String text = new String(data, StandardCharsets.UTF_8);
        return text;
    }

}
