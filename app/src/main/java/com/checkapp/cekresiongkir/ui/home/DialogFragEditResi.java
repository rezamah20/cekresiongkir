package com.checkapp.cekresiongkir.ui.home;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.checkapp.cekresiongkir.R;
import com.checkapp.cekresiongkir.database.DatabaseHandler;
import com.checkapp.cekresiongkir.database.ResiModel;
import com.checkapp.cekresiongkir.network.ApiService;
import com.checkapp.cekresiongkir.network.MainContract;
import com.checkapp.cekresiongkir.network.cekresi.ApiEndpoint;
import com.google.android.material.textfield.TextInputEditText;

public class DialogFragEditResi extends DialogFragment implements MainContract.PresentDB{

    private TextInputEditText label, waybill, kurirkode, status;
    private Button batal, simpan;
    private DatabaseHandler db;
    MainContract.View v;


    public DialogFragEditResi(MainContract.View view){
        this.v = view;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.dialog_frag_editresi,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        label = (TextInputEditText) view.findViewById(R.id.editlabelresi);
        waybill = (TextInputEditText) view.findViewById(R.id.edit_waybill);
        kurirkode = (TextInputEditText) view.findViewById(R.id.edit_kurirkode);
        status = (TextInputEditText) view.findViewById(R.id.edit_status);
        batal = (Button) view.findViewById(R.id.btn_batal);
        simpan = (Button) view.findViewById(R.id.btn_simpan);
        db = new DatabaseHandler(getContext());


        label.setText(getArguments().getString("label",""));
        waybill.setText(getArguments().getString("waybill",""));
        kurirkode.setText(getArguments().getString("kurirkode",""));
        status.setText(getArguments().getString("status",""));

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.update(String.valueOf(label.getText()),String.valueOf(waybill.getText()), String.valueOf(kurirkode.getText()), String.valueOf(status.getText()));
                getResiDB();
                getDialog().dismiss();
            }
        });

    }


    @Override
    public void getResiDB() {
        v.onUpdateDB(db.getResi());
    }
}
