package com.checkapp.cekresiongkir.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.checkapp.cekresiongkir.database.ResiModel;
import com.checkapp.cekresiongkir.databinding.FragmentNotificationsBinding;
import com.checkapp.cekresiongkir.network.Address;
import com.checkapp.cekresiongkir.network.BitshipResi;
import com.checkapp.cekresiongkir.network.MainContract;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements MainContract.View{

    private FragmentNotificationsBinding binding;
    private BitshipResi presenter;
    private MainContract.View v;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        v = this;

        presenter = new BitshipResi(v);
        presenter.getResiRajaOngkir();


        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
    public void onResultSearch(Address data) {

    }

    @Override
    public void onResultOngkir(CekOngkir data) {

    }

    @Override
    public void onErrorResi(CekResi data) {

    }

    @Override
    public void onUpdateDB(ArrayList<ResiModel> resiModel) {

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