package com.cekresi.cekongkir.network.cekongkir;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CekOngkir implements Serializable {

    @SerializedName("message")
    public String message;

    @SerializedName("error")
    public String error;

    @SerializedName("success")
    public Boolean success;

    @SerializedName("object")
    public String object;

    @SerializedName("code")
    public String code;


    @SerializedName("origin")
    private OngkirOrigin origin;

    @SerializedName("destination")
    private OngkirOrigin destination;


    @SerializedName("pricing")
    private List<OngkirPrincing> pricing;

    public OngkirOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(OngkirOrigin origin) {
        this.origin = origin;
    }

    public OngkirOrigin getDestination() {
        return destination;
    }

    public void setDestination(OngkirOrigin destination) {
        this.destination = destination;
    }

    public List<OngkirPrincing> getPricing() {
        return pricing;
    }

    public void setPricing(List<OngkirPrincing> pricing) {
        this.pricing = pricing;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CekOngkir{" +
                "message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", success=" + success +
                ", object='" + object + '\'' +
                ", code='" + code + '\'' +
                ", origin=" + origin +
                ", destination=" + destination +
                ", pricing=" + pricing +
                '}';
    }
}
