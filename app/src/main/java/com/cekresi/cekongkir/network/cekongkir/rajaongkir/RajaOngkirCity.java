package com.cekresi.cekongkir.network.cekongkir.rajaongkir;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RajaOngkirCity {

    @SerializedName("rajaongkir")
    public RajaOngkirCekCity rajaongkir;

    public RajaOngkirCekCity getRajaongkirCekCity() {
        return rajaongkir;
    }

    public void setRajaongkirCekCity(RajaOngkirCekCity rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

    @Override
    public String toString() {
        return "RajaOngkirCity{" +
                "rajaongkir=" + rajaongkir +
                '}';
    }

    public static class RajaOngkirCekCity{

        @SerializedName("results")
        public List<result> results;

        public List<result> getResults() {
            return results;
        }

        public void setResults(List<result> results) {
            this.results = results;
        }

        @Override
        public String toString() {
            return "RajaOngkirCekCity{" +
                    "results=" + results +
                    '}';
        }

        public static class result{

            @SerializedName("city_id")
            public String city_id;

            @SerializedName("province_id")
            public String province_id;

            @SerializedName("province")
            public String province;

            @SerializedName("type")
            public String type;

            @SerializedName("city_name")
            public String city_name;

            @SerializedName("postal_code")
            public String postal_code;

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getPostal_code() {
                return postal_code;
            }

            public void setPostal_code(String postal_code) {
                this.postal_code = postal_code;
            }

            @Override
            public String toString() {
                return   city_name +", "+ province+", "+ postal_code ;
            }
        }

    }

}
