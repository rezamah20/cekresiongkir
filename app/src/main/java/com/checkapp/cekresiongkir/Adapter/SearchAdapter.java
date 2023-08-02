package com.checkapp.cekresiongkir.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.BoringLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.checkapp.cekresiongkir.R;
import com.checkapp.cekresiongkir.network.MainContract;
import com.checkapp.cekresiongkir.network.cekongkir.rajaongkir.RajaOngkirCity;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<RajaOngkirCity.RajaOngkirCekCity.result> data;
    MainContract.View mainview;
    boolean searchadapter;



    public SearchAdapter(Context context, ArrayList<RajaOngkirCity.RajaOngkirCekCity.result> data,  MainContract.View view, boolean searchadapter){
        this.context = context;
        this.data = data;
        this.mainview = view;
        this.searchadapter = searchadapter;
    }

    public void filterList(ArrayList<RajaOngkirCity.RajaOngkirCekCity.result> filterlist) {

        data = filterlist;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvCity.setText(data.get(position).getCity_name());
        holder.tvProvince.setText(data.get(position).getProvince());
        holder.tvNegara.setText("ID");

        holder.llCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityname = data.get(position).getCity_name();
                String provicename = data.get(position).getProvince();
                String idcity = data.get(position).getCity_id();
                String postalcode = data.get(position).getPostal_code();
                mainview.onItemClickListener(searchadapter,cityname, provicename, idcity, postalcode);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        TextView tvProvince;
        TextView tvNegara;
        LinearLayout llCity;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCity = itemView.findViewById(R.id.tvCity);
            tvProvince = itemView.findViewById(R.id.tvProvince);
            llCity = itemView.findViewById(R.id.llCity);
            tvNegara = itemView.findViewById(R.id.tvNegara);
        }
    }
}
