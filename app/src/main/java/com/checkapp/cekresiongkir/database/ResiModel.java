package com.checkapp.cekresiongkir.database;

public class ResiModel {
    String id;
    String label;
    String resi;
    String kurir;
    String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getResi() {
        return resi;
    }

    public void setResi(String resi) {
        this.resi = resi;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResiModel{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", resi='" + resi + '\'' +
                ", kurir='" + kurir + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
