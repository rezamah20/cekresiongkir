package com.cekresi.cekongkir.network;

import com.cekresi.cekongkir.network.cekongkir.CekOngkir;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.CekOngkirRaja;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.CekOngkirRajaModel;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.RajaOngkirCity;
import com.cekresi.cekongkir.network.cekresi.CekResiModel;
import com.cekresi.cekongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;
import com.cekresi.cekongkir.network.cekresi.ApiEndpoint;
import com.cekresi.cekongkir.network.cekresi.CekResi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.checkapp.cekresiongkir.ads.AdClass;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    ArrayList<CekResiModel> resiModelArrayList = new ArrayList<>();
    Context context;
    Activity activity;


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

    public BitshipResi(Context context, Activity activity, MainContract.MainView view, MainContract.View v){
        this.MainView = view;
        this.view = v;
        this.context = context;
        this.activity = activity;
        endpointbitship = ApiService.getInstance().getUrlBiteship()
                .create(ApiEndpoint.getBitship.class);
        endpointrajaongkir = ApiService.getInstance().getUrlRajaOngkir()
                .create(ApiEndpoint.getRajaOngkir.class);
    }

    @Override
    public void getResi() {
        endpointbitship.getResia(waybill, courier_code)//
                    .enqueue(new Callback<CekResi>() {
                        @Override
                        public void onResponse(Call<CekResi> call, Response<CekResi> response) {
                            if(response.body() == null){
                                MainView.onErrorResi(null);
                            }else {
                                cekresi = response.body();
                                int lastI = cekresi.getHistory().size() - 1;
                                Log.d("ini json", String.valueOf(lastI));
                                resiModelArrayList.clear();
                                for (int i =0; i < cekresi.getHistory().size(); i++) {
                                    CekResiModel resiModel = new CekResiModel();
                                    resiModel.setLabel(cekresi.getDestination().getContact_name());
                                    resiModel.setPengirim(cekresi.getOrigin().getContact_name()+" ");
                                    resiModel.setTujuan(cekresi.getDestination().getContact_name()+" ");
                                    resiModel.setWaybill(cekresi.getWaybill_id());
                                    resiModel.setCompany(cekresi.getCourier().getCompany());
                                    resiModel.setTerahkirupdate(cekresi.getHistory().get(lastI).getUpdated_at());
                                    resiModel.setStatusterakhir(cekresi.getHistory().get(lastI).getStatus());
                                    resiModel.setStatus(cekresi.getHistory().get(i).getStatus());
                                    resiModel.setNote(cekresi.getHistory().get(i).getNote());
                                    resiModel.setUpdate(cekresi.getHistory().get(i).getUpdated_at());
                                    resiModelArrayList.add(resiModel);
                                }
                                getNative(true);
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
                            for (int i = 0; i < cekOngkir.getPricing().size() ; i++) {
                                    CekOngkirRajaModel cekOngkirRajaModels = new CekOngkirRajaModel();
                                    cekOngkirRajaModels.setDeskripsi(cekOngkir.getPricing().get(i).getCourier_service_name());
                                    cekOngkirRajaModels.setKodelayanan(cekOngkir.getPricing().get(i).getCourier_service_code());
                                    cekOngkirRajaModels.setDurasi(cekOngkir.getPricing().get(i).getShipment_duration_range());
                                    cekOngkirRajaModels.setCodekurir(cekOngkir.getPricing().get(i).courier_name);
                                    cekOngkirRajaModels.setHargakurir(cekOngkir.getPricing().get(i).getPrice());
                                    cekOngkirRajaModelsArray.add(cekOngkirRajaModels);
                                }
                                getNative(false);
                            }else {
                            view.onErrorOngkir();
                        }
                    }

                    @Override
                    public void onFailure(Call<CekOngkir> call, Throwable t) {
                        view.onErrorOngkir();
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
        endpointrajaongkir.getResiRajaOngkir(waybill, courier_code)
                .enqueue(new Callback<CekResiRajaOngkir>() {
                    @Override
                    public void onResponse(Call<CekResiRajaOngkir> call, Response<CekResiRajaOngkir> response) {
                        if (response.raw().code() != 200){
                            getResi();
                        }else {
                            cekResiRajaOngkir = response.body();
                            resiModelArrayList.clear();
                            for (int i =0; i < cekResiRajaOngkir.rajaongkir.result.manifest.size(); i++) {
                                CekResiModel resiModel = new CekResiModel();
                                resiModel.setLabel(cekResiRajaOngkir.rajaongkir.result.summary.receiver_name);
                                resiModel.setPengirim(cekResiRajaOngkir.rajaongkir.result.summary.shipper_name+" ");
                                resiModel.setTujuan(cekResiRajaOngkir.rajaongkir.result.summary.receiver_name+" ");
                                resiModel.setWaybill(cekResiRajaOngkir.rajaongkir.result.summary.waybill_number);
                                resiModel.setCompany(cekResiRajaOngkir.rajaongkir.result.summary.courier_name);
                                resiModel.setTerahkirupdate(cekResiRajaOngkir.rajaongkir.result.manifest.get(0).manifest_date+" "+cekResiRajaOngkir.rajaongkir.result.manifest.get(0).manifest_time);
                                resiModel.setStatusterakhir(cekResiRajaOngkir.rajaongkir.result.manifest.get(0).manifest_description);
                                resiModel.setStatus(cekResiRajaOngkir.rajaongkir.result.manifest.get(i).manifest_description);
                                resiModel.setNote(cekResiRajaOngkir.rajaongkir.result.manifest.get(i).city_name);
                                resiModel.setUpdate(cekResiRajaOngkir.rajaongkir.result.manifest.get(i).manifest_date+" "+cekResiRajaOngkir.rajaongkir.result.manifest.get(i).manifest_time);
                                resiModelArrayList.add(resiModel);
                            }
                            getNative(true);
                        }

                    }

                    @Override
                    public void onFailure(Call<CekResiRajaOngkir> call, Throwable t) {
                        getResi();
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
            String courier = "jne:pos:tiki:rpx:wahana:sicepat:jnt:sap:dse:ncs:ninja:lion:rex:ide:sentral:anteraja:pahala";
            endpointrajaongkir.getOngkirRaja(originid, "city", destinationid, "city", weight, courier)
                    .enqueue(new Callback<CekOngkirRaja>() {
                @Override
                public void onResponse(Call<CekOngkirRaja> call, Response<CekOngkirRaja> response) {

                    cekOngkirRaja = response.body();
                    if (cekOngkirRaja != null) {
                        ArrayList<CekOngkirRaja.RajaOngkirCost.Results> kurir = new ArrayList<>();
                        kurir.addAll(cekOngkirRaja.getRajaongkir().getResults());
                        cekOngkirRajaModelsArray.clear();
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
                        getNative(false);
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

    @Override
    public void getNative(boolean cek) {
            AdClass adClass = new AdClass();
            ArrayList<Object> objects;
            objects = new ArrayList<>();
            List<NativeAd> nativeAdList;
            nativeAdList = new ArrayList<>();
            boolean cekPre = cek;


            AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-9161313753252016/8675686150")
                    .forNativeAd(nativeAd -> {
                        if (activity.isDestroyed()) {
                            nativeAd.destroy();
                            return;
                        }
                        nativeAdList.add(nativeAd);
                        if (!adClass.getAdLoader().isLoading()){
                            if (cekPre) {
                                for (int i = 0; i < resiModelArrayList.size(); i++) {
                                    objects.add(resiModelArrayList.get(i));
                                    if (i == 0) {
                                        objects.add(nativeAdList.get(0));
                                    }
                                    if (i == 5) {
                                        objects.add(nativeAdList.get(1));
                                    }
                                    if (i == 11) {
                                        objects.add(nativeAdList.get(2));
                                    }
                                }
                                MainView.onResultResi(objects);
                            }else {
                               for (int i = 0; i < cekOngkirRajaModelsArray.size(); i++){
                                   objects.add(cekOngkirRajaModelsArray.get(i));
                                   if (i == 2) {
                                       objects.add(nativeAdList.get(0));
                                   }
                                   if (i == 10) {
                                       objects.add(nativeAdList.get(1));
                                   }
                                   if (i == 18) {
                                       objects.add(nativeAdList.get(2));
                                   }
                               }
                               view.onResultOngkir(objects);
                            }
                        }
                    })
                    .withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            Log.d("ini json", "Native Ad Failed To Load");

                            new CountDownTimer(10000, 1000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                    Log.d("ini json", "Timer : " + millisUntilFinished / 1000);
                                }

                                @Override
                                public void onFinish() {
                                    Log.d("ini json", "Reloading NativeAd");

                                    // loadNativeAd();
                                }
                            }.start();
                        }
                    })
                    .withNativeAdOptions(new NativeAdOptions.Builder().build()).build();

            adLoader.loadAds(new AdRequest.Builder().build(), 3);
            adClass.setAdLoader(adLoader);
    }
}