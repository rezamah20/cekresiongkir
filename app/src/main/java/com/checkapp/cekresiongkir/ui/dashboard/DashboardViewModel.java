package com.checkapp.cekresiongkir.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.checkapp.cekresiongkir.network.Address;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private MutableLiveData<CekOngkir> cekOngkirMutableLiveData;
    private MutableLiveData<Address> addressMutableLiveData;
    private MutableLiveData<String> idOrigin;
    private MutableLiveData<String> idDestination;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        cekOngkirMutableLiveData = new MutableLiveData<>();
        addressMutableLiveData = new MutableLiveData<>();
        idOrigin = new MutableLiveData<>();
        idDestination = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }


    public MutableLiveData<CekOngkir> getCekOngkir(){
        if (cekOngkirMutableLiveData.getValue() != null){
            setCekOngkir(cekOngkirMutableLiveData.getValue());
        }
        return cekOngkirMutableLiveData;
    }

    public void setCekOngkir(CekOngkir cekOngkir){
        this.cekOngkirMutableLiveData.setValue(cekOngkir);
    }

    public MutableLiveData<Address> getAddress(){
        if (addressMutableLiveData.getValue() != null){
            setAddress(addressMutableLiveData.getValue());
        }
        return addressMutableLiveData;
    }

    public void setAddress(Address address){
        this.addressMutableLiveData.setValue(address);
    }

    public MutableLiveData<String> getIdidOrigin(){
        if (idOrigin.getValue() != null){
            setIdidOrigin(idOrigin.getValue());
        }
        return idOrigin;
    }

    public void setIdidOrigin(String id){
        this.idOrigin.setValue(id);
    }

    public MutableLiveData<String> getIdDestination(){
        if (idDestination.getValue() != null){
            setIdDestination(idDestination.getValue());
        }
        return idDestination;
    }

    public void setIdDestination(String idori){
        this.idDestination.setValue(idori);
    }

}