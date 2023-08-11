package com.cekresi.cekongkir.ui.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cekresi.cekongkir.Adapter.OngkirAdapter;
import com.cekresi.cekongkir.Adapter.SearchAdapter;
import com.cekresi.cekongkir.ads.AdmobAdapter;
import com.cekresi.cekongkir.databinding.FragmentDashboardBinding;
import com.cekresi.cekongkir.network.Address;
import com.cekresi.cekongkir.network.BitshipResi;
import com.cekresi.cekongkir.network.MainContract;
import com.cekresi.cekongkir.network.cekongkir.CekOngkir;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.CekOngkirRajaModel;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.RajaOngkirCity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardFragment extends Fragment implements MainContract.View {

    private FragmentDashboardBinding binding;
    private BitshipResi presenter;
    private TextView berat;
    private Button cekongkir;
    private String originid, destinationid, originpostal, destipostal, berats;
    private RecyclerView rvsearchOrigin;
    private CardView cvsearchOrigin;
    private CardView cvsearchDesti;
    private RecyclerView rvsearchdestination;
    private ImageButton cleaninputorigin;
    private ImageButton cleaninputdesti;
    ArrayList<RajaOngkirCity.RajaOngkirCekCity.result> datarajaongkircity;
    private LinearLayout llMain;
    private LinearLayout rvrongkirtidakada;
    private ProgressBar progressBar;
    AdmobAdapter adapter;


    private SearchAdapter searchAdapter;
    MainContract.MainView mainView;
    MainContract.View v;

    CekOngkir cekOngkir;
    ArrayList<Object> objectsitem = new ArrayList<>();
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
        berat = binding.inputberat;
        cekongkir = binding.btnCekongkir;
        recyclerViewpricing = binding.rvpricing;
        rvsearchdestination = binding.rvsearchdestination;
        rvsearchOrigin = binding.rvsearchorigin;
        cvsearchOrigin = binding.cvsearchorigin;
        cvsearchDesti = binding.cvsearchdesti;
        cleaninputorigin = binding.cleaninputorigin;
        cleaninputdesti = binding.cleanDestiorigin;
        llMain = binding.llMain;
        rvrongkirtidakada = binding.rvrongkirtidakada;
        progressBar = binding.progressBar;

        recyclerViewpricing.setVisibility(View.GONE);
        v = this;
        showOngkir();
        presenter = new BitshipResi(getContext(), getActivity(), mainView, v);
        presenter.getCityRaja();
        datarajaongkircity = new ArrayList<>();
        adapter = new AdmobAdapter(getActivity(),getContext());
        adapter.loadInter();


        autoCompleteOrigin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchRajaOngkirOrigin();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0){
                    filter(String.valueOf(charSequence));
                    cvsearchOrigin.setVisibility(View.VISIBLE);
                    cleaninputorigin.setVisibility(View.VISIBLE);
                    cleaninputorigin.setOnClickListener(view -> {
                        autoCompleteOrigin.setText("");
                        dashboardViewModel.getIdidOrigin().setValue(null);
                        dashboardViewModel.getPostalCodeOrigin().setValue(null);
                    });
                }else {
                    cleaninputorigin.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 15){
                    cvsearchOrigin.setVisibility(View.GONE);
                }
            }
        });


        autoCompleteDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchRajaOngkirDestination();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0){
                    cvsearchDesti.setVisibility(View.VISIBLE);
                    filter(String.valueOf(charSequence));
                    cleaninputdesti.setVisibility(View.VISIBLE);
                    cleaninputdesti.setOnClickListener(view -> {
                        autoCompleteDestination.setText("");
                        dashboardViewModel.getIdDestination().setValue(null);
                        dashboardViewModel.getPostalCodeDesti().setValue(null);
                    });
                }else {
                    cleaninputdesti.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 15){
                    cvsearchDesti.setVisibility(View.GONE);
                }
            }
        });



        cekongkir.setOnClickListener(view -> {
            originid = dashboardViewModel.getIdidOrigin().getValue();
            destinationid = dashboardViewModel.getIdDestination().getValue();
            originpostal = dashboardViewModel.getPostalCodeOrigin().getValue();
            destipostal = dashboardViewModel.getPostalCodeDesti().getValue();
            berats = berat.getText().toString();
            Log.d("ini json", berats);

            if (Objects.equals(originid, null) || Objects.equals(destinationid, null ) || Objects.equals(originpostal, null) || Objects.equals(destipostal, null) || Objects.equals(berats, "")){
                Toast.makeText(getContext(), "Isikan Semua Data, Harus Memilih dari List", Toast.LENGTH_LONG).show();
            }else {
                presenter.setupOKR(originid, destinationid, originpostal, destipostal, berats);
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
        if (dashboardViewModel.getObjectitem().getValue() != null){
            dashboardViewModel.getObjectitem().observe(getViewLifecycleOwner(), new Observer<ArrayList<Object>>(){
                @Override
                public void onChanged(ArrayList<Object> objects) {
                    ongkirAdapter = new OngkirAdapter(getActivity(), getContext());
                    ongkirAdapter.setObject(objects);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerViewpricing.setLayoutManager(layoutManager);
                    recyclerViewpricing.setAdapter(ongkirAdapter);
                    recyclerViewpricing.setVisibility(View.VISIBLE);
                    llMain.setVisibility(View.GONE);
                    cvsearchOrigin.setVisibility(View.GONE);
                    cvsearchDesti.setVisibility(View.GONE);
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
    public void onResultOngkir(ArrayList<Object> objectsitem) {
        this.objectsitem = objectsitem;
        adapter.showInterstitial();
        if (objectsitem.size() > 0){
            dashboardViewModel.getObjectitem().setValue(objectsitem);
            showOngkir();
        }
    }

    @Override
    public void onLoadingOngki(boolean loadng, int progress) {
        if (loadng){
            llMain.setVisibility(View.VISIBLE);
            recyclerViewpricing.setVisibility(View.GONE);
            rvrongkirtidakada.setVisibility(View.GONE);
            progressBar.setProgress(progress);
        }else{
            progressBar.setProgress(progress);
        }

    }

    @Override
    public void onErrorOngkir() {
        adapter.showInterstitial();
        recyclerViewpricing.setVisibility(View.GONE);
        llMain.setVisibility(View.GONE);
        rvrongkirtidakada.setVisibility(View.VISIBLE);
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
            cvsearchOrigin.setVisibility(View.GONE);
        }else {
            autoCompleteDestination.setText(cityname+", "+provincename+", Indonesia");
            dashboardViewModel.getIdDestination().setValue(city_id);
            dashboardViewModel.getPostalCodeDesti().setValue(postalcode);
            cvsearchDesti.setVisibility(View.GONE);
        }
    }

}