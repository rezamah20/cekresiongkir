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
import android.widget.TextView;
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
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements MainContract.View {

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
    private ProgressBar progressBar;
    private LinearLayout llMain;
    private LinearLayout lnresidb;
    private FloatingActionButton floatingActionButton;
    private DatabaseHandler db;
    private ArrayList<ResiModel> list;
    private MainContract.View v;
    TextInputEditText txt;
    String kodeKurir;

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
        v = this;
        db = new DatabaseHandler(getContext());




        Spinner spinnerresi = binding.kurirDropdown;
        ArrayAdapter<String> spinnerresiadapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.resikurir_array));
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
                    presenter = new BitshipResi(v);
                   // presenter.setupENV(String.valueOf(txt.getText()), kodeKurir);
                    presenter.setupENV(String.valueOf(txt.getText()), kodeKurir);
                }
            }
        });
        return root;
    }


    private void showResi(){
        if (homeViewModel.getCekResi().getValue() != null){
            Log.d("ini json", "cek resi");
            homeViewModel.getCekResi().observe(getViewLifecycleOwner(), new Observer<CekResi>() {
                @Override
                public void onChanged(CekResi cekResi) {
                    if (cekResi != null) {
                        resiAdapter = new ResiAdapter(getActivity(), getContext(), cekResi);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(resiAdapter);
                        resiAdapter.notifyDataSetChanged();

                        //resihistoryadapter
                        resiHistoryAdapter = new ResiHistoryAdapter(getActivity(), getContext(), cekResi);
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
                }
            });
        }
    }

    public void showResierror(){
        resiAdapter = new ResiAdapter(getActivity(), getContext(), cekResi);
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
        String label = cekResi.getDestination().getContact_name();
        db.insert(label, cekResi.waybill_id, cekResi.getCourier().getCompany(), cekResi.getStatus());
    }
    private void showresidb(){
        resiAdapterDB = new ResiAdapterDB(getActivity(), getContext(), v);
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
    public void onResultResi(CekResi data) {
        this.cekResi = data;
        if (cekResi != null){
            homeViewModel.getCekResi().setValue(cekResi);
            saveResi();
            showResi();
        }
    }

    @Override
    public void onResultSearch(Address data) {

    }

    @Override
    public void onResultOngkir(CekOngkir data) {

    }

    @Override
    public void onErrorResi(CekResi cekResi) {
        homeViewModel.getCekResi().setValue(cekResi);
        showResierror();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
       // Log.d("ini json", msg);
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