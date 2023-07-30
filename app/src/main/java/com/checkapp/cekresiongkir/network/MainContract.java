package com.checkapp.cekresiongkir.network;

import com.checkapp.cekresiongkir.database.ResiModel;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.History;
import com.checkapp.cekresiongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;

import java.util.ArrayList;
import java.util.List;

public interface MainContract {
    interface Presenter{
        //biteship
        void getResi();
        void getKurir(String originid, String destiid, String berat);
        void getAddress(String add);
        void setupENV(String waybill_id, String courier_code);

        //rajaongkir
        void getResiRajaOngkir();
    }

    interface View{
        void onLoadingResi(boolean loadng, int progress);
        void onResultResi(CekResi data, CekResiRajaOngkir cekResiRajaOngkir);
        void onResultSearch(Address data);
        void onResultOngkir(CekOngkir data);
        void onErrorResi(CekResi data);
        void onUpdateDB(ArrayList<ResiModel> resiModel);

        void showMessage(String msg);
        String getOrigin();
        String getDestination();
    }

    interface PresentDB{
        void getResiDB();
    }
}
