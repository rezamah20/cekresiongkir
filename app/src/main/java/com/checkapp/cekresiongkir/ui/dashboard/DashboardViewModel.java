package com.checkapp.cekresiongkir.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.checkapp.cekresiongkir.network.Address;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRaja;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRajaModel;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<CekOngkir> cekOngkirMutableLiveData;
    private final MutableLiveData<Address> addressMutableLiveData;
    private final MutableLiveData<String> idOrigin;
    private final MutableLiveData<String> idDestination;
    private final MutableLiveData<String> postalcodeorigin;
    private final MutableLiveData<String> postalcodeodesti;
    private final MutableLiveData<ArrayList<CekOngkirRajaModel>> cekOngkirRajaMutableLiveData;
   // private MutableLiveData<RajaOngkirCity.RajaOngkirCekCity.result> rajaOngkirCityMutableLiveData;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        cekOngkirMutableLiveData = new MutableLiveData<>();
        addressMutableLiveData = new MutableLiveData<>();
        cekOngkirRajaMutableLiveData = new MutableLiveData<>();
        idOrigin = new MutableLiveData<>();
        idDestination = new MutableLiveData<>();
        postalcodeorigin = new MutableLiveData<>();
        postalcodeodesti = new MutableLiveData<>();
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


    public MutableLiveData<ArrayList<CekOngkirRajaModel>> getCekOngkirRaja(){
        if (cekOngkirRajaMutableLiveData.getValue() != null){
            setCekOngkirRaja(cekOngkirRajaMutableLiveData.getValue());
        }
        return cekOngkirRajaMutableLiveData;
    }

    public void setCekOngkirRaja(ArrayList<CekOngkirRajaModel> cekOngkirRajaModel){
        this.cekOngkirRajaMutableLiveData.setValue(cekOngkirRajaModel);
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

    public MutableLiveData<String> getPostalCodeOrigin(){
        if (postalcodeorigin.getValue() != null){
            setPostalCodeOrigin(postalcodeorigin.getValue());
        }
        return postalcodeorigin;
    }

    public void setPostalCodeOrigin(String postalCode){
        this.postalcodeorigin.setValue(postalCode);
    }

    public MutableLiveData<String> getPostalCodeDesti(){
        if (postalcodeodesti.getValue() != null){
            setPostalcodeoDesti(postalcodeodesti.getValue());
        }
        return postalcodeodesti;
    }

    public void setPostalcodeoDesti(String postalCode){
        this.postalcodeodesti.setValue(postalCode);
    }


}