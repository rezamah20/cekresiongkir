package com.cekresi.cekongkir.network.cekresi;

public class CekResiModel {

    String pengirim;
    String tujuan;
    String terahkirupdate;
    String waybill;
    String statusterakhir;


    String label;
    String company;
    String status;
    String note;
    String update;

    public String getStatusterakhir() {
        return statusterakhir;
    }

    public void setStatusterakhir(String statusterakhir) {
        this.statusterakhir = statusterakhir;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTerahkirupdate() {
        return terahkirupdate;
    }

    public void setTerahkirupdate(String terahkirupdate) {
        this.terahkirupdate = terahkirupdate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "CekResiModel{" +
                "pengirim='" + pengirim + '\'' +
                "tujuan='" + tujuan + '\'' +
                ", terahkirupdate='" + terahkirupdate + '\'' +
                ", status trakhir='" + statusterakhir + '\'' +
                ", waybill='" + waybill + '\'' +
                ", label='" + label + '\'' +
                ", company='" + company + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                ", update='" + update + '\'' +
                '}';
    }
}
