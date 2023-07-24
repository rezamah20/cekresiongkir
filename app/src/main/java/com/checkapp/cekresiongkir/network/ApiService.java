package com.checkapp.cekresiongkir.network;

import android.util.Log;

import com.checkapp.cekresiongkir.network.cekresi.ApiEndpoint;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String B_URL = "";
    private static Retrofit retrofit = null;

    private static String crediential;

    public static Retrofit getUrl(String BASE_URL, String KEY, String API_KEY){

        crediential = Credentials.basic(KEY,API_KEY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);

        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                  //  .header("authorization", API_KEY)
                 //   .header("content-type", "application/json")
                    .addHeader("authorization", API_KEY)
                 //   .addHeader("content-type", "application/json")
                    .build();
            return chain.proceed(request);
        });

        OkHttpClient client = builder.build();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;

    }
}
