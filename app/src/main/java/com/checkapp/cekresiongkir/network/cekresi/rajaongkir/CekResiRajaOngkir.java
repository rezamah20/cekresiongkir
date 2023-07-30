package com.checkapp.cekresiongkir.network.cekresi.rajaongkir;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CekResiRajaOngkir {

    @SerializedName("rajaongkir")
    public RajaOngkir rajaongkir;

    public RajaOngkir getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(RajaOngkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

    @Override
    public String toString() {
        return "CekResiRajaOngkir{" +
                "rajaongkir=" + rajaongkir +
                '}';
    }

    public static class RajaOngkir{

        @SerializedName("query")
        public Query query;

        @SerializedName("result")
        public Result result;

        @SerializedName("status")
        public Status status;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public Query getQuery() {
            return query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }


        public static class Result{

            @SerializedName("summary")
            public Summary summary;

            @SerializedName("manifest")
            public ArrayList<Manifest> manifest;

            public Summary getSummary() {
                return summary;
            }

            public void setSummary(Summary summary) {
                this.summary = summary;
            }

            public ArrayList<Manifest> getManifest() {
                return manifest;
            }

            public void setManifest(ArrayList<Manifest> manifest) {
                this.manifest = manifest;
            }

            public static class Summary{
                @SerializedName("courier_name")
                public String courier_name;

                @SerializedName("waybill_number")
                public String waybill_number;

                @SerializedName("shipper_name")
                public String shipper_name;

                @SerializedName("receiver_name")
                public String receiver_name;

                @SerializedName("status")
                public String status;

                public String getCourier_name() {
                    return courier_name;
                }

                public void setCourier_name(String courier_name) {
                    this.courier_name = courier_name;
                }

                public String getWaybill_number() {
                    return waybill_number;
                }

                public void setWaybill_number(String waybill_number) {
                    this.waybill_number = waybill_number;
                }

                public String getShipper_name() {
                    return shipper_name;
                }

                public void setShipper_name(String shipper_name) {
                    this.shipper_name = shipper_name;
                }

                public String getReceiver_name() {
                    return receiver_name;
                }

                public void setReceiver_name(String receiver_name) {
                    this.receiver_name = receiver_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                @Override
                public String toString() {
                    return "Summary{" +
                            "courier_name='" + courier_name + '\'' +
                            ", waybill_number='" + waybill_number + '\'' +
                            ", shipper_name='" + shipper_name + '\'' +
                            ", receiver_name='" + receiver_name + '\'' +
                            ", status='" + status + '\'' +
                            '}';
                }
            }

            public static class Manifest{
                @SerializedName("manifest_description")
                public String manifest_description;

                @SerializedName("manifest_date")
                public String manifest_date;

                @SerializedName("manifest_time")
                public String manifest_time;

                @SerializedName("city_name")
                public String city_name;

                public String getManifest_description() {
                    return manifest_description;
                }

                public void setManifest_description(String manifest_description) {
                    this.manifest_description = manifest_description;
                }

                public String getManifest_date() {
                    return manifest_date;
                }

                public void setManifest_date(String manifest_date) {
                    this.manifest_date = manifest_date;
                }

                public String getManifest_time() {
                    return manifest_time;
                }

                public void setManifest_time(String manifest_time) {
                    this.manifest_time = manifest_time;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                @Override
                public String toString() {
                    return "Manifest{" +
                            "manifest_description='" + manifest_description + '\'' +
                            ", manifest_date='" + manifest_date + '\'' +
                            ", manifest_time='" + manifest_time + '\'' +
                            ", city_name='" + city_name + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "Result{" +
                        "summary=" + summary +
                        ", manifest=" + manifest +
                        '}';
            }
        }

        public static class Status{
            @SerializedName("code")
            public String code;

            @SerializedName("description")
            public String description;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            @Override
            public String toString() {
                return "Status{" +
                        "code='" + code + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }
        }


        public static class Query{

            @SerializedName("waybill")
            public String waybill;

            @SerializedName("courier")
            public String courier;

            public String getWaybill() {
                return waybill;
            }

            public void setWaybill(String waybill) {
                this.waybill = waybill;
            }

            public String getCourier() {
                return courier;
            }

            public void setCourier(String courier) {
                this.courier = courier;
            }

            @Override
            public String toString() {
                return "Query{" +
                        "waybill='" + waybill + '\'' +
                        ", courier='" + courier + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "RajaOngkir{" +
                    "query=" + query +
                    ", result=" + result +
                    ", status=" + status +
                    '}';
        }
    }
}
