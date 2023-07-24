package com.checkapp.cekresiongkir.network;

import com.checkapp.cekresiongkir.network.cekresi.ApiEndpoint;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.History;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BitshipResi implements MainContract.Presenter{
    CekResi cekresi = new CekResi();
    ApiEndpoint endpoint;
    MainContract.View view;

    String waybill = "";
    String courier_code;

    public BitshipResi(MainContract.View view){
        this.view = view;
        endpoint = ApiService.getUrl("https://mocki.io/","64bbb4fe97566962c80fc04d","a")
                .create(ApiEndpoint.class);
    }

    @Override
    public void getResi() {
            endpoint.getResia(waybill, courier_code)
                    .enqueue(new Callback<CekResi>() {
                        @Override
                        public void onResponse(Call<CekResi> call, Response<CekResi> response) {
                            cekresi = response.body();
                            if(response.body() == null){
                                view.onErrorResi();
                                view.showMessage("Data Tidak Di temukan");
                            }

                            Log.d("ini json", String.valueOf(response.body()));
                        }

                        @Override
                        public void onFailure(Call<CekResi> call, Throwable t) {
                            t.getMessage();
                            Log.d("ini json", String.valueOf(t));
                        }

                    });
    }

    @Override
    public void setupENV(String waybill_id, String courier_codea) {
        this.waybill = waybill_id;
        this.courier_code = courier_codea;

        view.onLoadingResi(true, 25);

        getResi();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.onLoadingResi(false, 100);
                view.onResultResi(cekresi);
            }
        }, 4000);
    }
}
