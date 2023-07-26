package com.checkapp.cekresiongkir.network.cekongkir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostKurir {

    @SerializedName("origin_area_id")
    @Expose
    private String origin_area_id;

    @SerializedName("destination_area_id")
    @Expose
    private String destination_area_id;

    @SerializedName("couriers")
    @Expose
    private String couriers;


    public String getOrigin_area_id() {
        return origin_area_id;
    }

    public void setOrigin_area_id(String origin_area_id) {
        this.origin_area_id = origin_area_id;
    }

    public String getDestination_area_id() {
        return destination_area_id;
    }

    public void setDestination_area_id(String destination_area_id) {
        this.destination_area_id = destination_area_id;
    }

    public String getCouriers() {
        return couriers;
    }

    public void setCouriers(String couriers) {
        this.couriers = couriers;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @SerializedName("items")
    @Expose
    private List<Items> items = null;




    public static class Items {
        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("value")
        @Expose
        private Integer value;

        @SerializedName("length")
        @Expose
        private Integer length;

        @SerializedName("width")
        @Expose
        private Integer width;

        @SerializedName("height")
        @Expose
        private Integer height;

        @SerializedName("weight")
        @Expose
        private Integer weight;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        @SerializedName("quantity")
        @Expose
        private Integer quantity;

    }

}
