package com.checkapp.cekresiongkir.network.cekresi;

public class Origin {

    private String contact_name;

    private String address;

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "contact_name='" + contact_name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
