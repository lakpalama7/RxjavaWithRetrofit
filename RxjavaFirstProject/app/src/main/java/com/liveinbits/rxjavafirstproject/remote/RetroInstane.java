package com.liveinbits.rxjavafirstproject.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstane {
    private static final String BASEURL="https://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit=null;
    private static Retrofit getInstance(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIService getService(){
        return getInstance().create(APIService.class);
    }
}
