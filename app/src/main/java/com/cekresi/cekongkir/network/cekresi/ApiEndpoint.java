package com.cekresi.cekongkir.network.cekresi;

import com.cekresi.cekongkir.network.Address;
import com.cekresi.cekongkir.network.cekongkir.CekOngkir;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.CekOngkirRaja;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.RajaOngkirCity;
import com.cekresi.cekongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoint {

    interface getBitship{
      //  @GET("v1/68ffba86-2f4e-4342-96a5-97c1f92bb2c8")
      //  Call<CekResi> getResia();


        @GET("v1/trackings/{waybill_id}/couriers/{courier_code}")
        Call<CekResi> getResia(@Path("waybill_id") String waybill_id, @Path("courier_code") String courier_code);

        @POST("v1/rates/couriers")
        Call<CekOngkir> getKurir(@Body RequestBody postKurir);

        @GET("/v1/maps/areas?countries=ID")
        Call<Address> getAddress(@Query("input") String address, @Query("type") String type);
    }

    interface getRajaOngkir{

        @FormUrlEncoded
        @POST("waybill")
        Call<CekResiRajaOngkir> getResiRajaOngkir(@Field("waybill") String waybill, @Field("courier") String courier);


        @GET("city")
        Call<RajaOngkirCity> getSearchCityRaja();

        @FormUrlEncoded
        @POST("cost")
        Call<CekOngkirRaja> getOngkirRaja(@Field("origin") String origin,
                                          @Field("originType") String originType,
                                          @Field("destination") String destination,
                                          @Field("destinationType") String destinationType,
                                          @Field("weight") String weight,
                                          @Field("courier") String courier);

    }

}
