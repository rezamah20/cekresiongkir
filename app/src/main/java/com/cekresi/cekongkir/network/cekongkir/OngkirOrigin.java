package com.cekresi.cekongkir.network.cekongkir;

import com.google.gson.annotations.SerializedName;

public class OngkirOrigin {

    @SerializedName("postal_code")
    public String postal_code;

    @SerializedName("country_name")
    public String country_name;

    @SerializedName("administrative_division_level_1_name")
    public String administrative_division_level_1_name;

    @SerializedName("administrative_division_level_1_type")
    public String administrative_division_level_1_type;

    @SerializedName("administrative_division_level_2_name")
    public String administrative_division_level_2_name;

    @SerializedName("administrative_division_level_2_type")
    public String administrative_division_level_2_type;

    @SerializedName("administrative_division_level_3_name")
    public String administrative_division_level_3_name;

    @SerializedName("administrative_division_level_3_type")
    public String administrative_division_level_3_type;

    @SerializedName("administrative_division_level_4_name")
    public String administrative_division_level_4_name;

    @SerializedName("administrative_division_level_4_type")
    public String administrative_division_level_4_type;

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getAdministrative_division_level_1_name() {
        return administrative_division_level_1_name;
    }

    public void setAdministrative_division_level_1_name(String administrative_division_level_1_name) {
        this.administrative_division_level_1_name = administrative_division_level_1_name;
    }

    public String getAdministrative_division_level_1_type() {
        return administrative_division_level_1_type;
    }

    public void setAdministrative_division_level_1_type(String administrative_division_level_1_type) {
        this.administrative_division_level_1_type = administrative_division_level_1_type;
    }

    public String getAdministrative_division_level_2_name() {
        return administrative_division_level_2_name;
    }

    public void setAdministrative_division_level_2_name(String administrative_division_level_2_name) {
        this.administrative_division_level_2_name = administrative_division_level_2_name;
    }

    public String getAdministrative_division_level_2_type() {
        return administrative_division_level_2_type;
    }

    public void setAdministrative_division_level_2_type(String administrative_division_level_2_type) {
        this.administrative_division_level_2_type = administrative_division_level_2_type;
    }

    public String getAdministrative_division_level_3_name() {
        return administrative_division_level_3_name;
    }

    public void setAdministrative_division_level_3_name(String administrative_division_level_3_name) {
        this.administrative_division_level_3_name = administrative_division_level_3_name;
    }

    public String getAdministrative_division_level_3_type() {
        return administrative_division_level_3_type;
    }

    public void setAdministrative_division_level_3_type(String administrative_division_level_3_type) {
        this.administrative_division_level_3_type = administrative_division_level_3_type;
    }

    public String getAdministrative_division_level_4_name() {
        return administrative_division_level_4_name;
    }

    public void setAdministrative_division_level_4_name(String administrative_division_level_4_name) {
        this.administrative_division_level_4_name = administrative_division_level_4_name;
    }

    public String getAdministrative_division_level_4_type() {
        return administrative_division_level_4_type;
    }

    public void setAdministrative_division_level_4_type(String administrative_division_level_4_type) {
        this.administrative_division_level_4_type = administrative_division_level_4_type;
    }

    @Override
    public String toString() {
        return "OngkirOrigin{" +
                "postal_code='" + postal_code + '\'' +
                ", country_name='" + country_name + '\'' +
                ", administrative_division_level_1_name='" + administrative_division_level_1_name + '\'' +
                ", administrative_division_level_1_type='" + administrative_division_level_1_type + '\'' +
                ", administrative_division_level_2_name='" + administrative_division_level_2_name + '\'' +
                ", administrative_division_level_2_type='" + administrative_division_level_2_type + '\'' +
                ", administrative_division_level_3_name='" + administrative_division_level_3_name + '\'' +
                ", administrative_division_level_3_type='" + administrative_division_level_3_type + '\'' +
                ", administrative_division_level_4_name='" + administrative_division_level_4_name + '\'' +
                ", administrative_division_level_4_type='" + administrative_division_level_4_type + '\'' +
                '}';
    }
}
