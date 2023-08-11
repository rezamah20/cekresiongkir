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
import com.cekresi.cekongkir.network.cekresi.CekResi;
import com.cekresi.cekongkir.network.cekresi.CekResiModel;
import com.cekresi.cekongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;

public class ResiHistoryAdapterTest extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int IS_AD = 0;
    private static final int NOT_Ad = 1;

    CekResiRajaOngkir cekResiRajaOngkir;
    Activity activity;
    Context context;
    Integer i;
    CekResi data;
    NativeAd nativeAd;
    private final ArrayList<Object> objects = new ArrayList<>();



    public ResiHistoryAdapterTest(Activity activity, Context context){
        this.activity = activity;
        this.context = context;
    }

    public void setData(CekResi data, CekResiRajaOngkir cekResiRajaOngkir, NativeAd nativeAd){
        this.data = data;
        this.cekResiRajaOngkir =cekResiRajaOngkir;
        this.nativeAd = nativeAd;
        if (cekResiRajaOngkir.getRajaongkir() != null){
            i = cekResiRajaOngkir.getRajaongkir().getResult().getManifest().size();
        }else {
            i = data.getHistory().size();
        }
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
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resi_histori,parent,false);
            return new ResiHistoryViewHolderTest(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position)==IS_AD){
            AdViewHolder adv =  (AdViewHolder) holder;
            adv.setNativeAd((NativeAd) objects.get(position));
        }else{
            CekResiModel itemClass = (CekResiModel) objects.get(position);
            ResiHistoryViewHolderTest rvh = (ResiHistoryViewHolderTest) holder;
            rvh.history_status.setText(itemClass.getStatus());
            rvh.history_note.setText(itemClass.getNote());
            rvh.history_update.setText(itemClass.getUpdate());
        }
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

    public class ResiHistoryViewHolderTest extends RecyclerView.ViewHolder {
        TextView history_status, history_note, history_update;

        public ResiHistoryViewHolderTest(@NonNull View itemView) {
            super(itemView);

            history_status =(TextView)itemView.findViewById(R.id.history_status);
            history_note =(TextView)itemView.findViewById(R.id.history_note);
            history_update =(TextView)itemView.findViewById(R.id.history_terakhirupdate);
        }
    }
}
