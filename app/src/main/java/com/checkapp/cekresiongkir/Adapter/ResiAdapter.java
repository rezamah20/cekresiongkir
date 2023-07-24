package com.checkapp.cekresiongkir.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.checkapp.cekresiongkir.R;
import com.checkapp.cekresiongkir.network.BitshipResi;
import com.checkapp.cekresiongkir.network.cekresi.CekResi;
import com.checkapp.cekresiongkir.network.cekresi.History;

import java.util.List;

public class ResiAdapter extends RecyclerView.Adapter<ResiAdapter.ResiViewHolder> {
    Activity activity;
    Context context;
    CekResi data;
    List<String> history;


    public ResiAdapter(Activity activity, Context context, CekResi data){
        this.activity = activity;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ResiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resi, parent, false);
        return new ResiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResiViewHolder holder, int position) {
            holder.waybill.setText(data.getWaybill_id());
            holder.status.setText(data.getStatus());
            holder.tujuan.setText(data.getDestination().getContact_name());
            holder.kurir.setText(data.getCourier().getCompany());
            holder.terahkirupdate.setText(data.getHistory().get(data.getHistory().size() - 1).getUpdated_at());

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ResiViewHolder extends RecyclerView.ViewHolder {
        TextView waybill, status, tujuan, kurir, terahkirupdate;
        public ResiViewHolder(@NonNull View itemView) {
            super(itemView);
            waybill =(TextView)itemView.findViewById(R.id.waybill);
            status =(TextView)itemView.findViewById(R.id.status);
            tujuan =(TextView)itemView.findViewById(R.id.tujuan);
            kurir =(TextView)itemView.findViewById(R.id.kurir);
            terahkirupdate =(TextView)itemView.findViewById(R.id.terakhir_update);


        }
    }
}
