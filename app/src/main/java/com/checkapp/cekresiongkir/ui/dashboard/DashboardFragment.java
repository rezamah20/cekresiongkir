package com.checkapp.cekresiongkir.ui.dashboard;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.checkapp.cekresiongkir.Adapter.OngkirAdapter;
import com.checkapp.cekresiongkir.Adapter.SearchAdapter;
import com.checkapp.cekresiongkir.database.ResiModel;
import com.checkapp.cekresiongkir.databinding.FragmentDashboardBinding;
import com.checkapp.cekresiongkir.network.Address;
import com.checkapp.cekresiongkir.network.BitshipResi;
import com.checkapp.cekresiongkir.network.MainContract;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRaja;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRajaModel;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.RajaOngkirCity;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements MainContract.View {

    private FragmentDashboardBinding binding;
    private BitshipResi presenter;
    private Address address;
    private ArrayAdapter<Address.areas> adapter;
    private TextView berat;
    private Button cekongkir;
    private String originid, destinationid, originpostal, destipostal;
    private RecyclerView rvsearchOrigin;
    private RecyclerView rvsearchdestination;
    ArrayList<RajaOngkirCity.RajaOngkirCekCity.result> datarajaongkircity;

    private SearchAdapter searchAdapter;
    MainContract.MainView mainView;
    MainContract.View v;

    CekOngkir cekOngkir;
    CekOngkirRaja cekOngkirRaja;
    ArrayList<CekOngkirRajaModel> cekOngkirRajaModel;
    RecyclerView recyclerViewpricing;
    OngkirAdapter ongkirAdapter;
    DashboardViewModel dashboardViewModel;


    private AutoCompleteTextView autoCompleteOrigin;
    private AutoCompleteTextView autoCompleteDestination;

    private static final int REQUEST_SOURCE = 1;
    private static final int REQUEST_DESTINATION = 2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


       // asalpengirim = binding.inputorigin;
        autoCompleteOrigin = binding.inputorigin;
        autoCompleteDestination = binding.inputDestination;
        berat = binding.inputBerat;
        cekongkir = binding.btnCekongkir;
        recyclerViewpricing = binding.rvpricing;
        rvsearchdestination = binding.rvsearchdestination;
        rvsearchOrigin = binding.rvsearchorigin;

        recyclerViewpricing.setVisibility(View.GONE);
        v = this;
        showOngkir();
        presenter = new BitshipResi(mainView,v);
        presenter.getCityRaja();
        datarajaongkircity = new ArrayList<>();

        autoCompleteOrigin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchRajaOngkirOrigin();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i >= 2){
                    rvsearchOrigin.setVisibility(View.VISIBLE);
                    filter(String.valueOf(charSequence));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        autoCompleteDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchRajaOngkirDestination();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i >= 2){
                    rvsearchdestination.setVisibility(View.VISIBLE);
                    filter(String.valueOf(charSequence));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        cekongkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                originid = dashboardViewModel.getIdidOrigin().getValue();
                destinationid = dashboardViewModel.getIdDestination().getValue();
                originpostal = dashboardViewModel.getPostalCodeOrigin().getValue();
                destipostal = dashboardViewModel.getPostalCodeDesti().getValue();
                presenter.setupOKR(originid, destinationid, originpostal, destipostal, String.valueOf(berat.getText()));
            }
        });

        return root;
    }

    private void filter(String text) {
        ArrayList<RajaOngkirCity.RajaOngkirCekCity.result> filteredlist = new ArrayList<RajaOngkirCity.RajaOngkirCekCity.result>();

        for (RajaOngkirCity.RajaOngkirCekCity.result item : datarajaongkircity) {
            if (item.getCity_name().toLowerCase().contains(text.toLowerCase()) || item.getProvince().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
        } else {
            searchAdapter.filterList(filteredlist);
        }
    }

    public void searchRajaOngkirOrigin(){
        searchAdapter = new SearchAdapter(getContext(), datarajaongkircity, v, true);

        rvsearchOrigin.setLayoutManager(new LinearLayoutManager(getContext()));
        rvsearchOrigin.setAdapter(searchAdapter);
    }

    public void searchRajaOngkirDestination(){
        searchAdapter = new SearchAdapter(getContext(), datarajaongkircity, v, false);

        rvsearchdestination.setLayoutManager(new LinearLayoutManager(getContext()));
        rvsearchdestination.setAdapter(searchAdapter);
    }

    private void showOngkir(){
        if (dashboardViewModel.getCekOngkirRaja().getValue() != null){
            dashboardViewModel.getCekOngkirRaja().observe(getViewLifecycleOwner(), new Observer<ArrayList<CekOngkirRajaModel>>() {
                @Override
                public void onChanged(ArrayList<CekOngkirRajaModel> cekOngkirRajaModel) {
                    ongkirAdapter = new OngkirAdapter(cekOngkirRajaModel, getActivity(), getContext(), cekOngkir);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerViewpricing.setLayoutManager(layoutManager);
                    recyclerViewpricing.setAdapter(ongkirAdapter);
                    recyclerViewpricing.setVisibility(View.VISIBLE);
                }
            });
        }else if (dashboardViewModel.getCekOngkir().getValue() != null) {
            dashboardViewModel.getCekOngkir().observe(getViewLifecycleOwner(), new Observer<CekOngkir>() {
                @Override
                public void onChanged(CekOngkir cekOngkir) {
                    Log.d("ini json", "onChanged cekOngkir");
                    ongkirAdapter = new OngkirAdapter(cekOngkirRajaModel, getActivity(), getContext(), cekOngkir);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerViewpricing.setLayoutManager(layoutManager);
                    recyclerViewpricing.setAdapter(ongkirAdapter);
                    recyclerViewpricing.setVisibility(View.VISIBLE);
                }
            });
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResultSearch(Address data) {
        //this.address = data;
        if (data != null){
            dashboardViewModel.getAddress().setValue(data);
        }
    }

    @Override
    public void onResultOngkir(ArrayList<CekOngkirRajaModel> cekOngkirRajaModel, CekOngkirRaja cekOngkirRaja, CekOngkir cekOngkirBitship) {
        this.cekOngkir = cekOngkirBitship;
        this.cekOngkirRaja = cekOngkirRaja;
        this.cekOngkirRajaModel = cekOngkirRajaModel;

        if (cekOngkirRajaModel != null){
            dashboardViewModel.getCekOngkirRaja().setValue(cekOngkirRajaModel);
            showOngkir();
        }else {
            dashboardViewModel.getCekOngkir().setValue(cekOngkir);
            showOngkir();
        }

    }

    @Override
    public void onLoadingOngki(boolean loadng, int progress) {

    }

    @Override
    public void onErrorOngkir(CekOngkir cekOngkir) {

    }

    @Override
    public void onResultSearchRaja(List<RajaOngkirCity.RajaOngkirCekCity.result> rajaOngkirCity) {
       // this.datarajaongkircity = rajaOngkirCity;

        if (rajaOngkirCity.size() != 0){
            datarajaongkircity.clear();
           datarajaongkircity.addAll(rajaOngkirCity);
        }
    }


    @Override
    public void onItemClickListener(boolean city, String cityname, String provincename, String city_id, String postalcode) {
        Log.d("ini json city", city_id);
        if (city){
            autoCompleteOrigin.setText(cityname+", "+provincename+", Indonesia");
            dashboardViewModel.getIdidOrigin().setValue(city_id);
            dashboardViewModel.getPostalCodeOrigin().setValue(postalcode);
            rvsearchOrigin.setVisibility(View.GONE);
        }else {
            autoCompleteDestination.setText(cityname+", "+provincename+", Indonesia");
            dashboardViewModel.getIdDestination().setValue(city_id);
            dashboardViewModel.getPostalCodeDesti().setValue(postalcode);
            rvsearchdestination.setVisibility(View.GONE);
        }
    }

}