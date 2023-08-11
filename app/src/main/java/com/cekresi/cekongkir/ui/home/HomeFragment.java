package com.cekresi.cekongkir.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cekresi.cekongkir.Adapter.MaxSpinner;
import com.cekresi.cekongkir.Adapter.ResiAdapter;
import com.cekresi.cekongkir.Adapter.ResiAdapterDB;
import com.cekresi.cekongkir.Adapter.ResiHistoryAdapter;
import com.cekresi.cekongkir.Adapter.ResiHistoryAdapterTest;
import com.cekresi.cekongkir.Adapter.SpinnerKurirAdapter;
import com.cekresi.cekongkir.R;
import com.cekresi.cekongkir.ads.AdmobAdapter;
import com.cekresi.cekongkir.database.DatabaseHandler;
import com.cekresi.cekongkir.database.ResiModel;
import com.cekresi.cekongkir.databinding.FragmentHomeBinding;
import com.cekresi.cekongkir.network.BitshipResi;
import com.cekresi.cekongkir.network.MainContract;
import com.cekresi.cekongkir.network.cekresi.CekResi;
import com.cekresi.cekongkir.network.cekresi.CekResiModel;
import com.cekresi.cekongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment implements MainContract.MainView {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ResiAdapter resiAdapter;
    private ResiAdapterDB resiAdapterDB;
    private ResiHistoryAdapter resiHistoryAdapter;

    //testadapter
    private ResiHistoryAdapterTest resiHistoryAdapterTest;

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
    MaxSpinner spinnerresi;
    ImageView buttonscan;
    LinearLayout resitidakada;
    TextView statusresi;
    TextView statusresiket;
    ArrayList<Object> objectsitem = new ArrayList<>();
    AdmobAdapter adapter;

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
        spinnerresi = binding.kurirDropdown;
        buttonscan = binding.scanbarcode;
        mainView = this;
        db = new DatabaseHandler(getContext());
        resitidakada = binding.rvresitidakada;
        statusresi = binding.statusresi;
        statusresiket = binding.statusresiket;


        showResi();
        showresidb();
        spinnerset();

        adapter = new AdmobAdapter(getActivity(),getContext());
        adapter.initizeadmob();

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
                    presenter = new BitshipResi(getContext(), getActivity(),mainView, v);
                   // presenter.setupENV(String.valueOf(txt.getText()), kodeKurir);
                    presenter.setupENV(String.valueOf(txt.getText()), kodeKurir);
                }
            }
        });



        buttonscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanOptions options = new ScanOptions();
                options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES);
                options.setPrompt("Scan No Resi");
                options.setCameraId(0);
                options.setBeepEnabled(true);
                options.setOrientationLocked(false);
                options.setBarcodeImageEnabled(true);
                barcodeLauncher.launch(options);
            }
        });

        return root;
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(getActivity(), "Batal", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Berhasil: " + result.getContents(), Toast.LENGTH_LONG).show();
                    txt.setText(result.getContents());
                }
            });

    private void showResi(){
        resitidakada.setVisibility(View.GONE);

        if (homeViewModel.getObjectResiItem().getValue() != null){
            homeViewModel.getObjectResiItem().observe(getViewLifecycleOwner(), new Observer<ArrayList<Object>>() {
                @Override
                public void onChanged(ArrayList<Object> objects) {
                    resiAdapter = new ResiAdapter(getActivity(), getContext(),objects);
                    resiHistoryAdapterTest = new ResiHistoryAdapterTest(getActivity(), getContext());
                    resiHistoryAdapterTest.setObject(objects);
                    showResiadapter();
                }
            });
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void spinnerset(){
        SpinnerKurirAdapter spinnerKurirAdapter = new SpinnerKurirAdapter(getContext());
        spinnerresi.setAdapter(spinnerKurirAdapter);

        spinnerresi.setOnTouchListener((view, motionEvent) -> {
            InputMethodManager imm;
            getContext();
            imm = (InputMethodManager) Objects.requireNonNull(getContext()).getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(txt.getWindowToken(), 0);

            return false;
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

    }

    public void showResiadapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resiAdapter);
        resiAdapter.notifyDataSetChanged();


        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerViewHistory.setLayoutManager(layoutManager2);
        recyclerViewHistory.setAdapter(resiHistoryAdapterTest);
        resiHistoryAdapterTest.notifyDataSetChanged();


        lnresidb.setVisibility(View.GONE);
        llMain.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerViewHistory.setVisibility(View.VISIBLE);
        floatingActionButton.setVisibility(View.VISIBLE);
    }

    private void hideResi(){
        llMain.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        recyclerViewHistory.setVisibility(View.GONE);
    }

    public void saveResi(){
        if (Objects.equals(label, "")){
            label = "-";
        }
        db.insert(label, waybill, company, kodeKurir, status);
    }

    private void showresidb(){
        resiAdapterDB = new ResiAdapterDB(getActivity(), getContext(), mainView);
        list = db.getResi();
        resiAdapterDB.setList(list);
        floatingActionButton.setVisibility(View.GONE);

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
            resitidakada.setVisibility(View.GONE);
            progressBar.setProgress(progress);
        }else{
            progressBar.setProgress(progress);
        }
    }

    @Override
    public void onResultResi(ArrayList<Object> objectsitem) {
        this.objectsitem = objectsitem;

        if (objectsitem.size() >0 ){
            adapter.showInterstitial();
            CekResiModel itemClass = (CekResiModel) objectsitem.get(0);
            try {
                label = itemClass.getLabel();
            }catch (Throwable e){
                label = "-";
            }
            homeViewModel.getObjectResiItem().setValue(objectsitem);
            waybill = itemClass.getWaybill();
            company = itemClass.getCompany();
            status = itemClass.getStatus();
            saveResi();
            showResi();
        }
    }

    @Override
    public void onErrorResi(CekResi cekResi) {
        //homeViewModel.getCekResi().setValue(cekResi);
        adapter.showInterstitial();
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.LN1);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        floatingActionButton.setLayoutParams(params);
        floatingActionButton.setVisibility(View.VISIBLE);
        llMain.setVisibility(View.GONE);
        resitidakada.setVisibility(View.VISIBLE);
        statusresi.setText("Resi Tidak Di Temukan, beberapa kemungkinan :");
        statusresiket.setText("1. Resi salah, pastikan no resi yang anda masukan sudah benar.\n2. Data Resi belum di update oleh pihak kurir.\n3. Terjadi kesalahan pada saat pengambilan data ke kurir.");
    }

    @Override
    public void onUpdateDB(ArrayList<ResiModel> resiModel) {
        list.clear();
        list = resiModel;
        resiAdapterDB.setList(list);
        Log.d("ini json", resiModel.toString());
    }
}