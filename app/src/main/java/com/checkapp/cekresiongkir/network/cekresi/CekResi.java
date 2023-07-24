package com.checkapp.cekresiongkir.network.cekresi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CekResi implements Serializable {

    @SerializedName("success")
    public Boolean success;

    @SerializedName("messsage")
    public String messsage;

    @SerializedName("object")
    public String object;

    @SerializedName("id")
    public String id;

    @SerializedName("waybill_id")
    public String waybill_id;

    @SerializedName("link")
    public String link;

    @SerializedName("order_id")
    public String order_id;

    @SerializedName("status")
    public String status;

    @Override
    public String toString() {
        return "CekResi{" +
                "success=" + success +
                ", messsage='" + messsage + '\'' +
                ", object='" + object + '\'' +
                ", id='" + id + '\'' +
                ", waybill_id='" + waybill_id + '\'' +
                ", link='" + link + '\'' +
                ", order_id='" + order_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

        //object class
    @SerializedName("courier")
    private Courier courier;

    @SerializedName("origin")
    private Origin origin;

    @SerializedName("destination")
    private Destination destination;

    @SerializedName("history")
    private List<History> history;


    //get
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWaybill_id() {
        return waybill_id;
    }

    public void setWaybill_id(String waybill_id) {
        this.waybill_id = waybill_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
/*
    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }*/
}
