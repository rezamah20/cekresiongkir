package com.checkapp.cekresiongkir.network;

import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRaja;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRajaModel;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.RajaOngkirCity;
import com.checkapp.cekresiongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;
import com.checkapp.cekresiongkir.network.cekresi.ApiEndpoint;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;

import android.database.Cursor;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BitshipResi implements MainContract.Presenter{
    CekResi cekresi = new CekResi();
    CekResiRajaOngkir cekResiRajaOngkir = new CekResiRajaOngkir();
    CekOngkir cekOngkir = new CekOngkir();
    Address address = new Address();
    RajaOngkirCity rajaOngkirCity = new RajaOngkirCity();
    CekOngkirRaja cekOngkirRaja = new CekOngkirRaja();
    ArrayList<CekOngkirRajaModel> cekOngkirRajaModelsArray = new ArrayList<>();


    ApiEndpoint.getBitship endpointbitship;
    ApiEndpoint.getRajaOngkir endpointrajaongkir;

    MainContract.View view;
    MainContract.MainView MainView;


    String waybill = "";
    String courier_code;
    String originid;
    String destinationid;
    String originpostal;
    String destinationpostal;
    String weight;

    public BitshipResi(MainContract.MainView view, MainContract.View v){
        this.MainView = view;
        this.view = v;
        endpointbitship = ApiService.getInstance().getUrlBiteship()
                .create(ApiEndpoint.getBitship.class);
        endpointrajaongkir = ApiService.getInstance().getUrlRajaOngkir()
                .create(ApiEndpoint.getRajaOngkir.class);
    }

    @Override
    public void getResi() {
        endpointbitship.getResia()//waybill, courier_code
                    .enqueue(new Callback<CekResi>() {
                        @Override
                        public void onResponse(Call<CekResi> call, Response<CekResi> response) {
                            cekresi = response.body();
                            if(response.body() == null){
                                MainView.onErrorResi(null);
                                //   view.showMessage("Data Tidak Di temukan");
                            }

                        }

                        @Override
                        public void onFailure(Call<CekResi> call, Throwable t) {
                            t.getMessage();
                            MainView.onErrorResi(null);
                          //  Log.d("ini json", "null");

                        }

                    });
    }

    @Override
    public void getKurir() {
        Log.d("ini json", "getKurir");
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
            internalObject.put("weight",weight);
            internalObject.put("quantity",1);
            array.put(internalObject);

            jsonObject.put("origin_postal_code", originpostal);
            jsonObject.put("destination_postal_code", destinationpostal);
            jsonObject.put("couriers", "jnt,jne,anteraja,deliveree,tiki,ninja,lion,sicepat,idexpress,rpx,pos,sap,paxel,lalamove");
            jsonObject.put("items", array);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonStr = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());

        endpointbitship.getKurir(requestBody)
                .enqueue(new Callback<CekOngkir>() {
                    @Override
                    public void onResponse(Call<CekOngkir> call, Response<CekOngkir> response) {
                        cekOngkir = response.body();
                        if (cekOngkir != null) {
                            view.onResultOngkir(cekOngkirRajaModelsArray, cekOngkirRaja, cekOngkir);
                        }else {
                            view.onErrorOngkir(cekOngkir);
                        }
                    }

                    @Override
                    public void onFailure(Call<CekOngkir> call, Throwable t) {
                        view.onErrorOngkir(cekOngkir);
                    }

                });
    }

    @Override
    public void getAddress(String add){
     //   Log.d("ini json address", "getaddress");
        endpointbitship.getAddress(add,"single")
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
    public void getResiRajaOngkir() {
        endpointrajaongkir.getResiRajaOngkir("10007276206862", "anteraja")
                .enqueue(new Callback<CekResiRajaOngkir>() {
                    @Override
                    public void onResponse(Call<CekResiRajaOngkir> call, Response<CekResiRajaOngkir> response) {

                        if (response.raw().code() != 200){
                            getResi();
                            Log.d("ini json", "Resi Tidak Ditemukan");
                        }else {
                            cekResiRajaOngkir = response.body();
                        }

                    }

                    @Override
                    public void onFailure(Call<CekResiRajaOngkir> call, Throwable t) {
                        Log.d("ini json onFailure", String.valueOf(t));

                    }
                });

    }

    @Override
    public void getCityRaja() {
        endpointrajaongkir.getSearchCityRaja().enqueue(new Callback<RajaOngkirCity>() {
            @Override
            public void onResponse(Call<RajaOngkirCity> call, Response<RajaOngkirCity> response) {
                rajaOngkirCity = response.body();
                if (rajaOngkirCity != null) {
                    view.onResultSearchRaja(rajaOngkirCity.getRajaongkirCekCity().results);
                }else {
                    getCityRaja();
                }
            }

            @Override
            public void onFailure(Call<RajaOngkirCity> call, Throwable t) {
                getCityRaja();
            }
        });

    }

    @Override
    public void getOngkirRaja() {
            String courier = "jne:pos:tiki:rpx:wahana:sicepat:jnt:sap:dse:ncs:ninja:lion:rex:ide:sentral:anteraja:pahala:first:jtl";
            endpointrajaongkir.getOngkirRaja(originid, "citya", destinationid, "citya", weight, courier)
                    .enqueue(new Callback<CekOngkirRaja>() {
                @Override
                public void onResponse(Call<CekOngkirRaja> call, Response<CekOngkirRaja> response) {

                    cekOngkirRaja = response.body();
                    if (cekOngkirRaja != null) {
                        ArrayList<CekOngkirRaja.RajaOngkirCost.Results> kurir = new ArrayList<>();
                        kurir.addAll(cekOngkirRaja.getRajaongkir().getResults());

                        for (int i = 0; i < kurir.size(); i++) {
                            for (int ii = 0; ii < kurir.get(i).getCosts().size(); ii++) {
                                CekOngkirRajaModel cekOngkirRajaModels = new CekOngkirRajaModel();
                                cekOngkirRajaModels.setDeskripsi(kurir.get(i).getCosts().get(ii).description);
                                for (int iii = 0; iii < kurir.get(i).getCosts().get(ii).getCost().size(); iii++) {
                                    cekOngkirRajaModels.setKodelayanan(kurir.get(i).getCosts().get(ii).service);
                                    cekOngkirRajaModels.setDurasi(kurir.get(i).getCosts().get(ii).getCost().get(iii).etd);
                                    cekOngkirRajaModels.setCodekurir(kurir.get(i).getCode());
                                    cekOngkirRajaModels.setHargakurir(kurir.get(i).getCosts().get(ii).getCost().get(iii).value);
                                    cekOngkirRajaModelsArray.add(cekOngkirRajaModels);
                                }
                            }
                        }
                        view.onLoadingOngki(false, 100);
                        view.onResultOngkir(cekOngkirRajaModelsArray, cekOngkirRaja, cekOngkir);
                    }else {
                        getKurir();
                    }

                }

                @Override
                public void onFailure(Call<CekOngkirRaja> call, Throwable t) {
                    getKurir();
                }
            });
    }

    @Override
    public void setupENV(String waybill_id, String courier_codea) {
        this.waybill = waybill_id;
        this.courier_code = courier_codea;

        MainView.onLoadingResi(true, 25);
        getResiRajaOngkir();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainView.onLoadingResi(false, 100);
                MainView.onResultResi(cekresi, cekResiRajaOngkir);
            }
        }, 4000);
    }

    @Override
    public void setupOKR(String originid, String destinationid, String originpostal, String destinationpostal, String weight) {
        this.originid = originid;
        this.destinationid = destinationid;
        this.originpostal = originpostal;
        this.destinationpostal = destinationpostal;
        this.weight = weight;

        view.onLoadingOngki(true, 25);
        getOngkirRaja();

    }


}