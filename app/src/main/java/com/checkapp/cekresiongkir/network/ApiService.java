package com.checkapp.cekresiongkir.network;

import android.content.Context;
import android.util.Log;

import com.checkapp.cekresiongkir.network.cekresi.ApiEndpoint;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;

    public static ApiService apiService;

    private final static String BASE_URL = "https://mocki.io/";
    private final static String API_KEY = "https://mocki.io/";


    private final static String BASE_URL2 = "https://pro.rajaongkir.com/api/";
    private final static String API_KEY2 = "";


    public static ApiService getInstance() {
        if (apiService == null) {
            apiService = new ApiService();
        }
        return apiService;
    }

    public Retrofit getUrlBiteship() {
        return getUrlBiteship(null);
    }

    public Retrofit getUrlRajaOngkir() {
        return getUrlRajaOngkir(null);
    }


    public Retrofit getUrlBiteship(Context context){
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

    public Retrofit getUrlRajaOngkir(Context context){
        Log.d("ini json", "null");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);

        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("key", API_KEY2)
                    .build();
            return chain.proceed(request);
        });

        OkHttpClient client = builder.build();

        if(retrofit2 == null){
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return  retrofit2;
    }
}
