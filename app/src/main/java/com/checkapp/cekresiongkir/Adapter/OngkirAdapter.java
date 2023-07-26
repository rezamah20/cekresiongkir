package com.checkapp.cekresiongkir.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.checkapp.cekresiongkir.R;
import com.checkapp.cekresiongkir.network.cekongkir.CekOngkir;

public class OngkirAdapter extends RecyclerView.Adapter<OngkirAdapter.OngkirViewHolder> {

    Activity activity;
    Context context;
    CekOngkir cekOngkir;

    public OngkirAdapter(Activity activity, Context context, CekOngkir cekOngkir){
        this.activity = activity;
        this.context = context;
        this.cekOngkir = cekOngkir;
    }

    @NonNull
    @Override
    public OngkirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cekongkir, parent, false);
        return new OngkirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OngkirViewHolder holder, int position) {

        Log.d("ini json", String.valueOf(cekOngkir.getPricing().size()));

        holder.namaexpedisi.setText(cekOngkir.getPricing().get(position).getCourier_name());
        holder.deskripsi.setText(cekOngkir.getPricing().get(position).getCourier_service_name());
        holder.kode_layanan.setText(cekOngkir.getPricing().get(position).getCourier_service_code());
        holder.harga.setText("Rp. "+cekOngkir.getPricing().get(position).getPrice());
        holder.durasi.setText(cekOngkir.getPricing().get(position).getShipment_duration_range()+ " Hari");

    }

    @Override
    public int getItemCount() {
        return cekOngkir.getPricing().size();
    }

    public class OngkirViewHolder extends RecyclerView.ViewHolder {
        TextView namaexpedisi, deskripsi, durasi, kode_layanan, harga;

        public OngkirViewHolder(@NonNull View itemView) {
            super(itemView);
            namaexpedisi = itemView.findViewById(R.id.namaexpedisi);
            deskripsi = itemView.findViewById(R.id.description);
            kode_layanan = itemView.findViewById(R.id.kodelayanan);
            durasi = itemView.findViewById(R.id.durasi);
            harga = itemView.findViewById(R.id.harga);

        }
    }
}
