package com.cekresi.cekongkir.network.cekongkir.rajaongkir;

public class CekOngkirRajaModel {

    String codekurir;
    String hargakurir;
    String deskripsi;
    String kodelayanan;
    String durasi;

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKodelayanan() {
        return kodelayanan;
    }

    public void setKodelayanan(String kodelayanan) {
        this.kodelayanan = kodelayanan;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getCodekurir() {
        return codekurir;
    }

    public void setCodekurir(String codekurir) {
        this.codekurir = codekurir;
    }

    public String getHargakurir() {
        return hargakurir;
    }

    public void setHargakurir(String hargakurir) {
        this.hargakurir = hargakurir;
    }

    @Override
    public String toString() {
        return "CekOngkirRajaModel{" +
                "codekurir='" + codekurir + '\'' +
                ", hargakurir='" + hargakurir + '\'' +
                '}';
    }
}
