package com.cekresi.cekongkir.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cekresi.cekongkir.R;
import com.cekresi.cekongkir.ads.AdViewHolder;
import com.cekresi.cekongkir.network.cekongkir.rajaongkir.CekOngkirRajaModel;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;

public class OngkirAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int IS_AD = 0;
    private static final int NOT_Ad = 1;

    Activity activity;
    Context context;
    private final ArrayList<Object> objects = new ArrayList<>();
    int i;


    public OngkirAdapter(Activity activity, Context context){
        this.activity = activity;
        this.context = context;
    }

    public void setObject (ArrayList<Object> object){
        this.objects.clear();
        this.objects.addAll(object);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == IS_AD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_admob_cardview,parent,false);
            return new AdViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cekongkir, parent, false);
            return new OngkirViewHolder(view);
        }
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cekongkir, parent, false);
        //return new OngkirViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position)==IS_AD){
            AdViewHolder adv =  (AdViewHolder) holder;
            adv.setNativeAd((NativeAd) objects.get(position));
        }else{
            CekOngkirRajaModel itemClass = (CekOngkirRajaModel) objects.get(position);
            OngkirViewHolder rvh = (OngkirViewHolder) holder;
            rvh.namaexpedisi.setText(itemClass.getCodekurir());
            rvh.deskripsi.setText(itemClass.getDeskripsi());
            rvh.kode_layanan.setText(itemClass.getKodelayanan());
            rvh.harga.setText("Rp. " + itemClass.getHargakurir());
            rvh.durasi.setText(itemClass.getDurasi()+ " Hari");

        }

        /*
       if (cekOngkirRaja.size() != 0){
           holder.namaexpedisi.setText(cekOngkirRaja.get(position).getCodekurir());
           holder.deskripsi.setText(cekOngkirRaja.get(position).getDeskripsi());
           holder.kode_layanan.setText(cekOngkirRaja.get(position).getKodelayanan());
           holder.harga.setText("Rp. " + cekOngkirRaja.get(position).getHargakurir());
           holder.durasi.setText(cekOngkirRaja.get(position).getDurasi()+ " Hari");

          } else if (cekOngkir != null) {
            holder.namaexpedisi.setText(cekOngkir.getPricing().get(position).getCourier_name());
            holder.deskripsi.setText(cekOngkir.getPricing().get(position).getCourier_service_name());
            holder.kode_layanan.setText(cekOngkir.getPricing().get(position).getCourier_service_code());
            holder.harga.setText("Rp. " + cekOngkir.getPricing().get(position).getPrice());
            holder.durasi.setText(cekOngkir.getPricing().get(position).getShipment_duration_range() + " Hari");
       }

         */
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(objects.get(position) instanceof NativeAd){
            return IS_AD;
        }else{
            return NOT_Ad;
        }
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
