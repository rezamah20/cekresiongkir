package com.checkapp.cekresiongkir.network.cekresi.rajaongkir;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CekResiRajaOngkir {

    @SerializedName("rajaongkir")
    public RajaOngkir rajaongkir;

    public RajaOngkir getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(RajaOngkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

    @Override
    public String toString() {
        return "CekResiRajaOngkir{" +
                "rajaongkir=" + rajaongkir +
                '}';
    }

    public static class RajaOngkir{

        @SerializedName("query")
        public Query query;

        public Query getQuery() {
            return query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return "RajaOngkir{" +
                    "query=" + query +
                    '}';
        }

        public static class Query{

            @SerializedName("waybill")
            public String waybill;

            @SerializedName("courier")
            public String courier;

            public String getWaybill() {
                return waybill;
            }

            public void setWaybill(String waybill) {
                this.waybill = waybill;
            }

            public String getCourier() {
                return courier;
            }

            public void setCourier(String courier) {
                this.courier = courier;
            }

            @Override
            public String toString() {
                return "Query{" +
                        "waybill='" + waybill + '\'' +
                        ", courier='" + courier + '\'' +
                        '}';
            }
        }
    }
}
