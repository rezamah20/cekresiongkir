package com.checkapp.cekresiongkir.network;

import com.checkapp.cekresiongkir.database.ResiModel;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRaja;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRajaModel;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.RajaOngkirCity;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.History;
import com.checkapp.cekresiongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;

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

    }

    interface View{
        void onResultSearch(Address data);
        void onItemClickListener(boolean city, String cityname, String provincename, String city_id, String postalcode);
        void onResultSearchRaja(List<RajaOngkirCity.RajaOngkirCekCity.result> rajaOngkirCity);
        void onResultOngkir(ArrayList<CekOngkirRajaModel> cekOngkirRajaModel, CekOngkirRaja cekOngkirRaja, CekOngkir cekOngkirBitship);
        void onLoadingOngki(boolean loadng, int progress);
        void onErrorOngkir(CekOngkir cekOngkir);
    }

    interface PresentDB{
        void getResiDB();
    }

    interface MainView{
        void onLoadingResi(boolean loadng, int progress);
        void onResultResi(CekResi data, CekResiRajaOngkir cekResiRajaOngkir);
        void onErrorResi(CekResi data);
        void onUpdateDB(ArrayList<ResiModel> resiModel);
    }

}
