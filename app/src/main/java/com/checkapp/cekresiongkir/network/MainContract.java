package com.checkapp.cekresiongkir.network;

import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.History;

import java.util.List;

public interface MainContract {
    interface Presenter{
        void getResi();
        void setupENV(String waybill_id, String courier_code);
    }

    interface View{
        void onLoadingResi(boolean loadng, int progress);
        void onResultResi(CekResi data);
        void onErrorResi();

        void showMessage(String msg);
        String getOrigin();
        String getDestination();
    }
}
