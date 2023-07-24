package com.checkapp.cekresiongkir.network.cekresi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {

    @GET("v1/trackings/:waybill_id/couriers/:courier_code")
    Call<List<CekResi>> getResi();


    @GET("users")
    Call<List<CekResi>> getDataAddress();

    //use this for production
    //@GET("v1/trackings/{waybill_id}/couriers/{courier_code}")
  //  Call<CekResi> getResia(@Path("waybill_id") String waybill_id, @Path("courier_code") String courier_code);

    @GET("{waybill_id}/{courier_code}")
  //  Call<CekResi> getResia();
    Call<CekResi> getResia(@Path("waybill_id") String waybill_id, @Path("courier_code") String courier_code);
}
