package com.cekresi.cekongkir.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cekresi.cekongkir.R;
import com.cekresi.cekongkir.network.cekresi.CekResi;
import com.cekresi.cekongkir.network.cekresi.CekResiModel;

import java.util.ArrayList;

public class ResiAdapter extends RecyclerView.Adapter<ResiAdapter.ResiViewHolder> {
    Activity activity;
    Context context;
    CekResi data;
    private ArrayList<Object> objects = new ArrayList<>();


    public ResiAdapter(Activity activity, Context context, ArrayList<Object> objects){
        this.activity = activity;
        this.context = context;
        this.data = data;
        this.objects = objects;
    }

    @NonNull
    @Override
    public ResiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resi, parent, false);
        return new ResiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResiViewHolder holder, int position) {

        CekResiModel itemClass = (CekResiModel) objects.get(0);

        if (objects.size() > 0){
            holder.waybill.setText(itemClass.getWaybill());
            holder.status.setText(itemClass.getStatusterakhir());
            holder.pengirim.setText(itemClass.getPengirim());
            holder.tujuan.setText(itemClass.getTujuan());
            holder.kurir.setText(itemClass.getCompany());
            holder.terahkirupdate.setText(itemClass.getTerahkirupdate());
            holder.resiada.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ResiViewHolder extends RecyclerView.ViewHolder {
        TextView waybill, status, pengirim, tujuan, kurir, terahkirupdate;
        LinearLayout resiada;

        public ResiViewHolder(@NonNull View itemView) {
            super(itemView);
            waybill =(TextView)itemView.findViewById(R.id.waybill);
            status =(TextView)itemView.findViewById(R.id.status);
            pengirim = (TextView)itemView.findViewById(R.id.pengirim);
            tujuan =(TextView)itemView.findViewById(R.id.tujuan);
            kurir =(TextView)itemView.findViewById(R.id.kurir);
            terahkirupdate =(TextView)itemView.findViewById(R.id.terakhir_update);
            resiada = (LinearLayout) itemView.findViewById(R.id.rvresiada);
        }
    }
}
