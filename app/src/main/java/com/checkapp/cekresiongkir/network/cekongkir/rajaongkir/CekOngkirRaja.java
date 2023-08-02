package com.checkapp.cekresiongkir.network.cekongkir.rajaongkir;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CekOngkirRaja {
    @SerializedName("rajaongkir")
    public RajaOngkirCost rajaongkir;

    public RajaOngkirCost getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(RajaOngkirCost rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

    @Override
    public String toString() {
        return "CekOngkirRaja{" +
                "rajaongkir=" + rajaongkir +
                '}';
    }

    public static class RajaOngkirCost{

        @SerializedName("origin_details")
        public OriginDetails origin_details;


        @SerializedName("destination_details")
        public DestinationDetails destination_details;


        @SerializedName("results")
        public List<Results> results;

        public OriginDetails getOrigin_details() {
            return origin_details;
        }

        public void setOrigin_details(OriginDetails origin_details) {
            this.origin_details = origin_details;
        }

        public DestinationDetails getDestination_details() {
            return destination_details;
        }

        public void setDestination_details(DestinationDetails destination_details) {
            this.destination_details = destination_details;
        }

        public List<Results> getResults() {
            return results;
        }

        public void setResults(List<Results> results) {
            this.results = results;
        }

        @Override
        public String toString() {
            return "RajaOngkirCost{" +
                    "origin_details=" + origin_details +
                    ", destination_details=" + destination_details +
                    ", results=" + results +
                    '}';
        }

        public static class OriginDetails{

                @SerializedName("province")
                public String province;

                @SerializedName("city_name")
                public String city_name;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            @Override
            public String toString() {
                return "OriginDetails{" +
                        "province='" + province + '\'' +
                        ", city_name='" + city_name + '\'' +
                        '}';
            }
        }

            public static class DestinationDetails{
                @SerializedName("province")
                public String province;

                @SerializedName("city_name")
                public String city_name;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                @Override
                public String toString() {
                    return "DestinationDetails{" +
                            "province='" + province + '\'' +
                            ", city_name='" + city_name + '\'' +
                            '}';
                }
            }
            public static class Results{
                @SerializedName("code")
                public String code;

                @SerializedName("name")
                public String name;

                @SerializedName("costs")
                public List<Costs> costs;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<Costs> getCosts() {
                    return costs;
                }

                public void setCosts(List<Costs> costs) {
                    this.costs = costs;
                }

                @Override
                public String toString() {
                    return "Results{" +
                            "code='" + code + '\'' +
                            ", name='" + name + '\'' +
                            ", costs=" + costs +
                            '}';
                }

                public static class Costs{
                    @SerializedName("service")
                    public String service;

                    @SerializedName("description")
                    public String description;

                    @SerializedName("cost")
                    public List<Cost> cost;

                    public String getService() {
                        return service;
                    }

                    public void setService(String service) {
                        this.service = service;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public List<Cost> getCost() {
                        return cost;
                    }

                    public void setCost(List<Cost> cost) {
                        this.cost = cost;
                    }

                    @Override
                    public String toString() {
                        return "Costs{" +
                                "service='" + service + '\'' +
                                ", description='" + description + '\'' +
                                ", cost=" + cost +
                                '}';
                    }

                    public static class Cost{

                        @SerializedName("value")
                        public String value;

                        @SerializedName("etd")
                        public String etd;

                        public String getValue() {
                            return value;
                        }

                        public void setValue(String value) {
                            this.value = value;
                        }

                        public String getEtd() {
                            return etd;
                        }

                        public void setEtd(String etd) {
                            this.etd = etd;
                        }

                        @Override
                        public String toString() {
                            return "cost{" +
                                    "value='" + value + '\'' +
                                    ", etd='" + etd + '\'' +
                                    '}';
                        }
                    }
                }
            }
    }

}

