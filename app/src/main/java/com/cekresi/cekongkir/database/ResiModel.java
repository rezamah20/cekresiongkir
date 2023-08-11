package com.cekresi.cekongkir.database;

public class ResiModel {
    String id;
    String label;
    String resi;
    String kurir;
    String code_kurir;
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

    public String getCode_kurir() {
        return code_kurir;
    }

    public void setCode_kurir(String code_kurir) {
        this.code_kurir = code_kurir;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CekResiModel{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", resi='" + resi + '\'' +
                ", kurir='" + kurir + '\'' +
                ", code_kurir='" + code_kurir + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
