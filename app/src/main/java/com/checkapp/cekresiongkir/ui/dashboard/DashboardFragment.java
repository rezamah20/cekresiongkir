package com.checkapp.cekresiongkir.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.checkapp.cekresiongkir.databinding.FragmentDashboardBinding;
import com.checkapp.cekresiongkir.network.BitshipResi;
import com.checkapp.cekresiongkir.network.MainContract;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DashboardFragment extends Fragment implements MainContract.View {

    private FragmentDashboardBinding binding;
    private BitshipResi presenter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        presenter = new BitshipResi(this);

        presenter.getKurir();
        return root;
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
    public void onErrorResi() {

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