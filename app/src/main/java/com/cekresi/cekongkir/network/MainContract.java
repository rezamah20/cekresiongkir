package com.cekresi.cekongkir.network;

import com.cekresi.cekongkir.database.ResiModel;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.RajaOngkirCity;
import com.cekresi.cekongkir.network.cekresi.CekResi;

import java.util.ArrayList;
import java.util.List;

public interface MainContract {
    interface Presenter{
        //biteship
        void getResi();
        void getKurir();
        void getAddress(String add);
        void setupENV(String waybill_id, String courier_code);


        //rajaongkir
        void getResiRajaOngkir();
        void getCityRaja();
        void getOngkirRaja();
        void setupOKR(String originid, String destinationid, String originpostal, String destinationpostal, String weight);

        //ads
        void getNative(boolean cek);
    }

    interface View{
        void onResultSearch(Address data);
        void onItemClickListener(boolean city, String cityname, String provincename, String city_id, String postalcode);
        void onResultSearchRaja(List<RajaOngkirCity.RajaOngkirCekCity.result> rajaOngkirCity);
        void onResultOngkir(ArrayList<Object> objectsitem);
        void onLoadingOngki(boolean loadng, int progress);
        void onErrorOngkir();
    }

    interface PresentDB{
        void getResiDB();
    }

    interface MainView{
        void onLoadingResi(boolean loadng, int progress);
        void onResultResi(ArrayList<Object> objectsitem);
        void onErrorResi(CekResi data);
        void onUpdateDB(ArrayList<ResiModel> resiModel);
    }

}
