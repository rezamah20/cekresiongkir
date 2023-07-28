package com.checkapp.cekresiongkir.network;

import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.History;

import java.util.List;

public interface MainContract {
    interface Presenter{
        void getResi();
        void getKurir(String originid, String destiid, String berat);
        void getAddress(String add);
        void setupENV(String waybill_id, String courier_code);
    }

    interface View{
        void onLoadingResi(boolean loadng, int progress);
        void onResultResi(CekResi data);
        void onResultSearch(Address data);
        void onResultOngkir(CekOngkir data);
        void onErrorResi(CekResi data);

        void showMessage(String msg);
        String getOrigin();
        String getDestination();
    }
}
