package com.checkapp.cekresiongkir.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;

public class HomeViewModel extends ViewModel{

    private final MutableLiveData<String> mText;
    private MutableLiveData<CekResi> cekResiMutableLiveData;
    private MutableLiveData<CekResiRajaOngkir> cekResiRajaOngkirMutableLiveData;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        cekResiMutableLiveData = new MutableLiveData<>();
        cekResiRajaOngkirMutableLiveData = new MutableLiveData<>();
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

}