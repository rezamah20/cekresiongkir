package com.checkapp.cekresiongkir.network.cekresi;

import com.checkapp.cekresiongkir.network.Address;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekongkir.PostKurir;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoint {

   // @GET("v1/trackings/:waybill_id/couriers/:courier_code")
   // Call<List<CekResi>> getResi();


   // @GET("users")
   // Call<List<CekResi>> getDataAddress();

    //use this for production
    //@GET("v1/trackings/{waybill_id}/couriers/{courier_code}")
  //  Call<CekResi> getResia(@Path("waybill_id") String waybill_id, @Path("courier_code") String courier_code);

   // @GET("{waybill_id}/{courier_code}")
  //  Call<CekResi> getResia();
    @GET("v1/trackings/{waybill_id}/couriers/{courier_code}")
    Call<CekResi> getResia(@Path("waybill_id") String waybill_id, @Path("courier_code") String courier_code);

    //@Headers({"Content-Type: application/json"})
    @POST("v1/rates/couriers")
    Call<CekOngkir> getKurir(@Body RequestBody postKurir);

    @GET("/v1/maps/areas?countries=ID")
    Call<Address> getAddress(@Query("input") String address, @Query("type") String type);
}
