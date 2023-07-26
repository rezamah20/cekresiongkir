package com.checkapp.cekresiongkir.network.cekongkir;

import com.google.gson.annotations.SerializedName;

public class OngkirPrincing {

    @SerializedName("courier_name")
    public String courier_name;

    @SerializedName("courier_service_name")
    public String courier_service_name;

    @SerializedName("shipment_duration_range")
    public String shipment_duration_range;

    @SerializedName("shipment_duration_unit")
    public String shipment_duration_unit;

    @SerializedName("courier_service_code")
    public String courier_service_code;


    public String getCourier_service_code() {
        return courier_service_code;
    }

    public void setCourier_service_code(String courier_service_code) {
        this.courier_service_code = courier_service_code;
    }

    @SerializedName("price")
    public String price;

    public String getCourier_name() {
        return courier_name;
    }

    public void setCourier_name(String courier_name) {
        this.courier_name = courier_name;
    }

    public String getCourier_service_name() {
        return courier_service_name;
    }

    public void setCourier_service_name(String courier_service_name) {
        this.courier_service_name = courier_service_name;
    }

    public String getShipment_duration_range() {
        return shipment_duration_range;
    }

    public void setShipment_duration_range(String shipment_duration_range) {
        this.shipment_duration_range = shipment_duration_range;
    }

    public String getShipment_duration_unit() {
        return shipment_duration_unit;
    }

    public void setShipment_duration_unit(String shipment_duration_unit) {
        this.shipment_duration_unit = shipment_duration_unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OngkirPrincing{" +
                "courier_name='" + courier_name + '\'' +
                ", courier_service_name='" + courier_service_name + '\'' +
                ", shipment_duration_range='" + shipment_duration_range + '\'' +
                ", shipment_duration_unit='" + shipment_duration_unit + '\'' +
                ", courier_service_code='" + courier_service_code + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
