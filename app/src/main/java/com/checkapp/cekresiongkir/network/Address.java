package com.checkapp.cekresiongkir.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.namespace.QName;

public class Address {

    @SerializedName("areas")
    @Expose
    List<areas> areas= null;

    public List<Address.areas> getAreas() {
        return areas;
    }

    public void setAreas(List<Address.areas> areas) {
        this.areas = areas;
    }

    @Override
    public String toString() {
        return "Address{" +
                "areas=" + areas +
                '}';
    }

    public static class areas {

        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("id")
        @Expose
        public String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
