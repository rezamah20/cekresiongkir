package com.checkapp.cekresiongkir.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.checkapp.cekresiongkir.Adapter.ResiAdapter;
import com.checkapp.cekresiongkir.Adapter.ResiAdapterDB;
import com.checkapp.cekresiongkir.Adapter.ResiHistoryAdapter;
import com.checkapp.cekresiongkir.Adapter.SpinnerKurirAdapter;
import com.checkapp.cekresiongkir.R;
import com.checkapp.cekresiongkir.database.DatabaseHandler;
import com.checkapp.cekresiongkir.database.ResiModel;
import com.checkapp.cekresiongkir.databinding.FragmentHomeBinding;
import com.checkapp.cekresiongkir.network.Address;
import com.checkapp.cekresiongkir.network.BitshipResi;
import com.checkapp.cekresiongkir.network.MainContract;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRaja;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.CekOngkirRajaModel;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.RajaOngkirCity;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MainContract.MainView {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ResiAdapter resiAdapter;
    private ResiAdapterDB resiAdapterDB;
    private ResiHistoryAdapter resiHistoryAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewHistory;
    private RecyclerView recyclerViewdb;
    private BitshipResi presenter;
    private Button cekresi;
    private CekResi cekResi;
    private CekResiRajaOngkir cekResiRajaOngkir;
    private ProgressBar progressBar;
    private LinearLayout llMain;
    private LinearLayout lnresidb;
    private FloatingActionButton floatingActionButton;
    private DatabaseHandler db;
    private ArrayList<ResiModel> list;
    private MainContract.View v;
    private MainContract.MainView mainView;
    TextInputEditText txt;
    String kodeKurir, label, waybill, company, status;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txt = binding.inputresi;
        recyclerView = binding.rvresi;
        recyclerViewHistory = binding.rvhistory;
        recyclerViewdb = binding.rvresidatadb;
        progressBar = binding.progressBar;
        floatingActionButton = binding.floatingbutton;
        cekresi = binding.btnCekresi;
        llMain = binding.llMain;
        lnresidb = binding.lnresidb;
        mainView = this;
        db = new DatabaseHandler(getContext());


        Spinner spinnerresi = binding.kurirDropdown;
        ArrayAdapter<String> spinnerresiadapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.resikurir_array));
        spinnerresiadapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        showResi();
        showresidb();

        // spinnerresi.
        SpinnerKurirAdapter spinnerKurirAdapter = new SpinnerKurirAdapter(getContext());
        spinnerresi.setAdapter(spinnerKurirAdapter);
        spinnerresi.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txt.getWindowToken(), 0);
                return false;
            }
        });
        spinnerresi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kodeKurir = spinnerKurirAdapter.kodeKurir[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showresidb();
                lnresidb.setVisibility(View.VISIBLE);
                hideResi();
                recyclerView.setVisibility(View.GONE);
            }
        });

        cekresi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(txt.getText()).equals("")){
                    Toast.makeText(getContext(), "Nomor Resi tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else {
                    presenter = new BitshipResi(mainView, v);
                   // presenter.setupENV(String.valueOf(txt.getText()), kodeKurir);
                    presenter.setupENV(String.valueOf(txt.getText()), kodeKurir);
                }
            }
        });
        return root;
    }


    private void showResi(){

        if (homeViewModel.getCekResiRajaOngkir().getValue() != null) {
            homeViewModel.getCekResiRajaOngkir().observe(getViewLifecycleOwner(), new Observer<CekResiRajaOngkir>() {
                @Override
                public void onChanged(CekResiRajaOngkir cekResiRajaOngkir) {
                    resiAdapter = new ResiAdapter(getActivity(), getContext(), cekResi, cekResiRajaOngkir);
                    resiHistoryAdapter = new ResiHistoryAdapter(getActivity(), getContext(), cekResi, cekResiRajaOngkir);
                    showResiadapter();
                }
            });
        }else if (homeViewModel.getCekResi().getValue() != null){
            homeViewModel.getCekResi().observe(getViewLifecycleOwner(), new Observer<CekResi>() {
                @Override
                public void onChanged(CekResi cekResi) {
                    resiAdapter = new ResiAdapter(getActivity(), getContext(), cekResi, cekResiRajaOngkir);
                    resiHistoryAdapter = new ResiHistoryAdapter(getActivity(), getContext(), cekResi, cekResiRajaOngkir);
                    showResiadapter();
                }
            });
        }

    }

    public void showResiadapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resiAdapter);
        resiAdapter.notifyDataSetChanged();


        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerViewHistory.setLayoutManager(layoutManager2);
        recyclerViewHistory.setAdapter(resiHistoryAdapter);
        resiHistoryAdapter.notifyDataSetChanged();


        lnresidb.setVisibility(View.GONE);
        llMain.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerViewHistory.setVisibility(View.VISIBLE);
        floatingActionButton.setVisibility(View.VISIBLE);
    }

    public void showResierror(){
        resiAdapter = new ResiAdapter(getActivity(), getContext(), cekResi, cekResiRajaOngkir);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resiAdapter);

        recyclerView.setVisibility(View.VISIBLE);
        hideResi();
    }

    private void hideResi(){
        llMain.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerViewHistory.setVisibility(View.GONE);
        floatingActionButton.setVisibility(View.GONE);
    }

    public void saveResi(){
        db.insert(label, waybill, company, status);
    }

    private void showresidb(){
        resiAdapterDB = new ResiAdapterDB(getActivity(), getContext(), mainView);
        list = db.getResi();
        resiAdapterDB.setList(list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewdb.setLayoutManager(layoutManager);
            recyclerViewdb.setAdapter(resiAdapterDB);
            if (list.size() == 0){
                lnresidb.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onLoadingResi(boolean loadng, int progress) {
        if (loadng){
            llMain.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            lnresidb.setVisibility(View.GONE);
            recyclerViewHistory.setVisibility(View.GONE);
            floatingActionButton.setVisibility(View.GONE);
            progressBar.setProgress(progress);
        }else{
            progressBar.setProgress(progress);
        }
    }

    @Override
    public void onResultResi(CekResi data, CekResiRajaOngkir cekResiRajaOngkir) {
        this.cekResi = data;
        this.cekResiRajaOngkir = cekResiRajaOngkir;

        if (cekResiRajaOngkir.getRajaongkir() != null){
            homeViewModel.getCekResi().setValue(null);
            label = cekResiRajaOngkir.getRajaongkir().getResult().getSummary().getReceiver_name();
            waybill = cekResiRajaOngkir.getRajaongkir().getResult().getSummary().getWaybill_number();
            company = cekResiRajaOngkir.getRajaongkir().getResult().getSummary().getCourier_name();
            status = cekResiRajaOngkir.getRajaongkir().getResult().getSummary().getStatus();
            homeViewModel.getCekResiRajaOngkir().setValue(cekResiRajaOngkir);
            saveResi();
            showResi();
        }else{
            homeViewModel.getCekResiRajaOngkir().setValue(null);
            label = data.getDestination().getContact_name();
            waybill = data.getWaybill_id();
            company = data.getCourier().getCompany();
            status = data.getStatus();
            homeViewModel.getCekResi().setValue(cekResi);
            saveResi();
            showResi();
        }

    }

    @Override
    public void onErrorResi(CekResi cekResi) {
        homeViewModel.getCekResi().setValue(cekResi);
        showResierror();
    }

    @Override
    public void onUpdateDB(ArrayList<ResiModel> resiModel) {
        list.clear();
        list = resiModel;
        resiAdapterDB.setList(list);
        Log.d("ini json", resiModel.toString());
    }

}