package com.checkapp.cekresiongkir.ui.home;

import android.annotation.SuppressLint;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.checkapp.cekresiongkir.Adapter.ResiAdapter;
import com.checkapp.cekresiongkir.Adapter.ResiHistoryAdapter;
import com.checkapp.cekresiongkir.Adapter.SpinnerKurirAdapter;
import com.checkapp.cekresiongkir.R;
import com.checkapp.cekresiongkir.databinding.FragmentHomeBinding;
import com.checkapp.cekresiongkir.network.BitshipResi;
import com.checkapp.cekresiongkir.network.MainContract;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.History;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment implements MainContract.View {

    private FragmentHomeBinding binding;
    private ResiAdapter resiAdapter;
    private ResiHistoryAdapter resiHistoryAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewHistory;
    private BitshipResi presenter;
    private Button cekresi;
    private CekResi cekResi;
    private ProgressBar progressBar;
    private LinearLayout llMain;
    private FloatingActionButton floatingActionButton;
    String kodeKurir;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextInputEditText txt = binding.inputresi;
        recyclerView = binding.rvresi;
        recyclerViewHistory = binding.rvhistory;

        floatingActionButton = binding.floatingbutton;

        Spinner spinnerresi = binding.kurirDropdown;
        ArrayAdapter<String> spinnerresiadapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.resikurir_array));
        spinnerresiadapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        progressBar = binding.progressBar;
        llMain = binding.llMain;
        llMain.setVisibility(View.GONE);

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
                Log.d("ini json", spinnerKurirAdapter.kodeKurir[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        presenter = new BitshipResi(this);


        cekresi = binding.btnCekresi;

        cekresi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(txt.getText()).equals("")){
                    Toast.makeText(getContext(), "Nomor Resi tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else {
                    presenter.setupENV("v1", "68ffba86-2f4e-4342-96a5-97c1f92bb2c8a");
                    //presenter.setupENV(String.valueOf(txt.getText()), kodeKurir);
                }
            }
        });

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onLoadingResi(boolean loadng, int progress) {
        if (loadng){
            Log.d("ini json", "on progress");
            llMain.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
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
            //resi adapter
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


            llMain.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerViewHistory.setVisibility(View.VISIBLE);
            floatingActionButton.setVisibility(View.VISIBLE);
        }
       //
//        Log.d("ini json", String.valueOf(cekResi.getHistory().get(data.getHistory().size() - 1).getNote()));
    }

    @Override
    public void onErrorResi() {
        llMain.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        recyclerViewHistory.setVisibility(View.GONE);
        floatingActionButton.setVisibility(View.GONE);
        Log.d("ini json", "error");
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        Log.d("ini json", msg);
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