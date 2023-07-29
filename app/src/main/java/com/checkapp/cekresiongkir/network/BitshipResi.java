package com.checkapp.cekresiongkir.network;

import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekongkir.PostKurir;
import com.checkapp.cekresiongkir.network.cekresi.ApiEndpoint;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.History;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BitshipResi implements MainContract.Presenter{
    CekResi cekresi = new CekResi();
    CekOngkir cekOngkir = new CekOngkir();
    Address address = new Address();

    ApiEndpoint endpoint;
    MainContract.View view;

    String waybill = "";
    String courier_code;

    public BitshipResi(MainContract.View view){
        this.view = view;
        endpoint = ApiService.getUrl("https://mocki.io/","1","")
                .create(ApiEndpoint.class);
    }

    @Override
    public void getResi() {
            endpoint.getResia()//waybill, courier_code
                    .enqueue(new Callback<CekResi>() {
                        @Override
                        public void onResponse(Call<CekResi> call, Response<CekResi> response) {
                            cekresi = response.body();
                            if(response.body() == null){
                                view.onErrorResi(null);
                                 Log.d("ini json", "null");
                                //   view.showMessage("Data Tidak Di temukan");
                            }

                        }

                        @Override
                        public void onFailure(Call<CekResi> call, Throwable t) {
                            t.getMessage();
                            view.onErrorResi(null);
                          //  Log.d("ini json", "null");

                        }

                    });
    }

    @Override
    public void getKurir(String originid, String destiid, String berat) {

        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            JSONObject internalObject = new JSONObject();
            internalObject.put("name","Shoes");
            internalObject.put("name","Shoes");
            internalObject.put("value",199000);
            internalObject.put("length",30);
            internalObject.put("width",15);
            internalObject.put("height",30);
            internalObject.put("weight",berat);
            internalObject.put("quantity",1);
            array.put(internalObject);

            jsonObject.put("origin_area_id", originid);
            jsonObject.put("destination_area_id", destiid);
            jsonObject.put("couriers", "jnt,jne,anteraja");
            jsonObject.put("items", array);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonStr = jsonObject.toString();
        Log.d("ini json respon", jsonStr);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());


        endpoint.getKurir(requestBody)
                .enqueue(new Callback<CekOngkir>() {
                    @Override
                    public void onResponse(Call<CekOngkir> call, Response<CekOngkir> response) {
                        cekOngkir = response.body();

                       // Log.d("ini json respon", String.valueOf(response.body()));

                        view.onResultOngkir(cekOngkir);
                    }

                    @Override
                    public void onFailure(Call<CekOngkir> call, Throwable t) {
                        t.getMessage();
                        Log.d("ini json failed", String.valueOf(t));
                    }

                });
    }

    @Override
    public void getAddress(String add){
     //   Log.d("ini json address", "getaddress");
        endpoint.getAddress(add,"single")
                .enqueue(new Callback<Address>() {
                    @Override
                    public void onResponse(Call<Address> call, Response<Address> response) {
                        address = response.body();

                        view.onResultSearch(address);

                    }

                    @Override
                    public void onFailure(Call<Address> call, Throwable t) {

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