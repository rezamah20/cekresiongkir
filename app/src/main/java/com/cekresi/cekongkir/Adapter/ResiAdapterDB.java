package com.cekresi.cekongkir.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cekresi.cekongkir.R;
import com.cekresi.cekongkir.database.DatabaseHandler;
import com.cekresi.cekongkir.database.ResiModel;
import com.cekresi.cekongkir.network.BitshipResi;
import com.cekresi.cekongkir.network.MainContract;
import com.cekresi.cekongkir.ui.home.DialogFragEditResi;

import java.util.ArrayList;

public class ResiAdapterDB extends RecyclerView.Adapter<ResiAdapterDB.ResiDBViewHolder> {

    Activity activity;
    Context context;
    MainContract.MainView mainView;
    MainContract.View vv;
    private BitshipResi presenter;
    DatabaseHandler db;

    private ArrayList<ResiModel> list = new ArrayList<>();

    public ResiAdapterDB(Activity activity, Context context, MainContract.MainView view){
        this.activity = activity;
        this.context = context;
        this.mainView =view;
        db = new DatabaseHandler(context);
    }

    public void setList(ArrayList<ResiModel> listResiDB){
        this.list.clear();
        this.list.addAll(listResiDB);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResiDBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_residb, parent, false);
        return new ResiDBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResiDBViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String waybill = list.get(position).getResi();
        String kurirkode = list.get(position).getKurir();
        String label = list.get(position).getLabel();
        String status = list.get(position).getStatus();
        String code_kurir = list.get(position).getCode_kurir();


        holder.labeldb.setText(label);
        holder.waybilldb.setText(waybill);
        holder.statusdb.setText(status);
        holder.kurirdb.setText(kurirkode);

        holder.cv_item_residb.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(context, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.option_residb, popup.getMenu());
                popup.setGravity(Gravity.END);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.btn_ubah:

                                FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                                DialogFragEditResi dialogFragment=new DialogFragEditResi(mainView);

                                dialogFragment.show(fm,"Edit  Fragment");

                                Bundle residata = new Bundle();
                                residata.putString("label", label);
                                residata.putString("waybill", waybill);
                                residata.putString("kurirkode",kurirkode);
                                residata.putString("codekurir", code_kurir);
                                residata.putString("status", status);
                                dialogFragment.setArguments(residata);

                                return true;
                            case R.id.btn_hapus:
                                    Log.d("ini json", "btn_hapus");
                                    db.delete(list.get(position).getId());
                                    list.clear();
                                    db.getResi();
                                    list = db.getResi();
                                notifyDataSetChanged();

                                return true;
                            case R.id.btn_cekresi:

                                 presenter = new BitshipResi(context, activity, mainView, vv);
                                 presenter.setupENV(waybill, code_kurir);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ResiDBViewHolder extends RecyclerView.ViewHolder {
        TextView labeldb, waybilldb, statusdb, kurirdb;
        CardView cv_item_residb;
        public ResiDBViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_item_residb = (CardView)itemView.findViewById(R.id.cv_item_residb);
            labeldb =(TextView)itemView.findViewById(R.id.labeldb);
            waybilldb =(TextView)itemView.findViewById(R.id.waybilldb);
            statusdb =(TextView)itemView.findViewById(R.id.statusdb);
            kurirdb =(TextView)itemView.findViewById(R.id.kurirdb);

        }
    }
}
