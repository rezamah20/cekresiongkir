package com.checkapp.cekresiongkir.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.checkapp.cekresiongkir.R;
import com.checkapp.cekresiongkir.database.DatabaseHandler;
import com.checkapp.cekresiongkir.database.ResiModel;
import com.checkapp.cekresiongkir.network.BitshipResi;
import com.checkapp.cekresiongkir.network.MainContract;

import java.util.ArrayList;

public class ResiAdapterDB extends RecyclerView.Adapter<ResiAdapterDB.ResiDBViewHolder> {

    Activity activity;
    Context context;
    MainContract.View v;
    private BitshipResi presenter;
    DatabaseHandler db;

    private ArrayList<ResiModel> list = new ArrayList<>();

    public ResiAdapterDB(Activity activity, Context context, MainContract.View view){
        this.activity = activity;
        this.context = context;
        this.v =view;
        db = new DatabaseHandler(context);
    }

    public void setList(ArrayList<ResiModel> listResiDB){
        if (listResiDB.size() > 0){
            this.list.clear();
        }
        this.list.addAll(listResiDB);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResiDBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_residb, parent, false);
        return new ResiDBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResiDBViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("ini json", "onBindViewHolder");
        String waybill = list.get(position).getResi();
        String kurirkode = list.get(position).getKurir();


        holder.labeldb.setText(list.get(position).getLabel());
        holder.waybilldb.setText(waybill);
        holder.statusdb.setText(list.get(position).getStatus());
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
                                Log.d("ini json", "btn_ubah");

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

                                 presenter = new BitshipResi(v);
                                 presenter.setupENV(waybill, kurirkode);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();

                Log.d("ini json", "carviewclick");
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
