package com.cekresi.cekongkir.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cekresi.cekongkir.R;
import com.cekresi.cekongkir.databinding.FragmentNotificationsBinding;
import com.cekresi.cekongkir.network.BitshipResi;
import com.cekresi.cekongkir.network.MainContract;

public class NotificationsFragment extends Fragment{

    private FragmentNotificationsBinding binding;
    private BitshipResi presenter;
    private MainContract.View v;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}