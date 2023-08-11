package com.cekresi.cekongkir.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cekresi.cekongkir.network.cekresi.CekResi;
import com.cekresi.cekongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel{

    private final MutableLiveData<String> mText;
    private MutableLiveData<CekResi> cekResiMutableLiveData;
    private MutableLiveData<CekResiRajaOngkir> cekResiRajaOngkirMutableLiveData;
    private MutableLiveData<ArrayList<Object>> arrayListObjectMutableLiveData;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        cekResiMutableLiveData = new MutableLiveData<>();
        cekResiRajaOngkirMutableLiveData = new MutableLiveData<>();
        arrayListObjectMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<CekResi> getCekResi(){
        if (cekResiMutableLiveData.getValue() != null){
            setCekResi(cekResiMutableLiveData.getValue());
        } else {
            setCekResi(cekResiMutableLiveData.getValue());
        }
        return cekResiMutableLiveData;
    }

    public void setCekResi(CekResi cekResi) {
        this.cekResiMutableLiveData.postValue(cekResi);
    }

    public MutableLiveData<CekResiRajaOngkir> getCekResiRajaOngkir(){
        if (cekResiMutableLiveData.getValue() != null){
            setCekResiRajaOngkir(cekResiRajaOngkirMutableLiveData.getValue());
        } else {
            setCekResiRajaOngkir(cekResiRajaOngkirMutableLiveData.getValue());
        }

        return cekResiRajaOngkirMutableLiveData;
    }

    public void setCekResiRajaOngkir(CekResiRajaOngkir cekResiRajaOngkir){
        this.cekResiRajaOngkirMutableLiveData.postValue(cekResiRajaOngkir);
    }

    public MutableLiveData<ArrayList<Object>> getObjectResiItem(){
        if (arrayListObjectMutableLiveData.getValue() != null){
            setObjectResiItem(arrayListObjectMutableLiveData.getValue());
        } else {
            setObjectResiItem(arrayListObjectMutableLiveData.getValue());
        }

        return arrayListObjectMutableLiveData;
    }

    public void setObjectResiItem(ArrayList<Object> objectsresiitem){
        this.arrayListObjectMutableLiveData.postValue(objectsresiitem);
    }

}