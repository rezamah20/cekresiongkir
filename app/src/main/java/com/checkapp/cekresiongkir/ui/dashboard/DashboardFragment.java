package com.checkapp.cekresiongkir.ui.dashboard;

import android.content.Context;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.checkapp.cekresiongkir.Adapter.OngkirAdapter;
import com.checkapp.cekresiongkir.Adapter.ResiAdapter;
import com.checkapp.cekresiongkir.databinding.FragmentDashboardBinding;
import com.checkapp.cekresiongkir.network.Address;
import com.checkapp.cekresiongkir.network.BitshipResi;
import com.checkapp.cekresiongkir.network.MainContract;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements MainContract.View {

    private FragmentDashboardBinding binding;
    private BitshipResi presenter;
    private Address address;
    private ArrayAdapter<Address.areas> adapter;
    List<Address.areas> countries;
    private TextView berat;
    private Button cekongkir;
    private String originid, destinationid;
    CekOngkir cekOngkir;
    RecyclerView recyclerViewpricing;
    OngkirAdapter ongkirAdapter;

    private AutoCompleteTextView autoCompleteOrigin;
    private AutoCompleteTextView autoCompleteDestination;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        presenter = new BitshipResi(this);

       // asalpengirim = binding.inputorigin;
        autoCompleteOrigin = binding.inputorigin;
        autoCompleteDestination = binding.inputDestination;
        berat = binding.inputBerat;
        cekongkir = binding.btnCekongkir;
        recyclerViewpricing = binding.rvpricing;

        recyclerViewpricing.setVisibility(View.GONE);
        MainContract.View v = this;


        autoCompleteOrigin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // Log.d("ini json before", String.valueOf(charSequence));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (i >= 2){
                        presenter.getAddress(String.valueOf(charSequence));
                        searchsuggest();
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        autoCompleteOrigin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                originid = address.getAreas().get(i).getId();
            }
        });

        autoCompleteDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i >= 2){
                    presenter.getAddress(String.valueOf(charSequence));
                    searchsuggest();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        autoCompleteDestination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                destinationid = address.getAreas().get(i).getId();
            }
        });


        cekongkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getKurir(originid, destinationid, String.valueOf(berat.getText()));
            }
        });

        return root;
    }

    private void searchsuggest(){
        if (address != null) {
            adapter=new ArrayAdapter<Address.areas>(getContext(), android.R.layout.simple_list_item_1, address.getAreas());
            autoCompleteOrigin.setThreshold(1);
            autoCompleteOrigin.setAdapter(adapter);
            autoCompleteDestination.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onLoadingResi(boolean loadng, int progress) {

    }

    @Override
    public void onResultResi(CekResi data) {
    }

    @Override
    public void onResultSearch(Address data) {
        this.address = data;
        searchsuggest();
    }

    @Override
    public void onResultOngkir(CekOngkir data) {
        this.cekOngkir = data;
        if ( cekOngkir != null ){
            ongkirAdapter = new OngkirAdapter(getActivity(), getContext(), cekOngkir);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewpricing.setLayoutManager(layoutManager);
            recyclerViewpricing.setAdapter(ongkirAdapter);
            ongkirAdapter.notifyDataSetChanged();
            recyclerViewpricing.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onErrorResi(CekResi data) {

    }


    @Override
    public void showMessage(String msg) {

    }

    @Override
    public String getOrigin() {
        return null;
    }

    @Override
    public String getDestination() {
        return null;
    }
}