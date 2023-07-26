package com.checkapp.cekresiongkir.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        Log.d("ini json", "cek resi");
        if (data != null) {
            holder.waybill.setText(data.getWaybill_id());
            holder.status.setText(data.getStatus());
            holder.tujuan.setText(data.getDestination().getContact_name());
            holder.kurir.setText(data.getCourier().getCompany());
            holder.terahkirupdate.setText(data.getHistory().get(data.getHistory().size() - 1).getUpdated_at());
            holder.resiada.setVisibility(View.VISIBLE);
            holder.resitidakada.setVisibility(View.GONE);
            Log.d("ini json", "Resi di temukan");

        }else {
            Log.d("ini json", "Resi Tidak Di temukan");
            holder.resiada.setVisibility(View.GONE);
            holder.resitidakada.setVisibility(View.VISIBLE);
            holder.statusresi.setText("Resi Tidak Di temukan");
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ResiViewHolder extends RecyclerView.ViewHolder {
        TextView waybill, status, tujuan, kurir, terahkirupdate, statusresi;
        LinearLayout resiada;
        LinearLayout resitidakada;

        public ResiViewHolder(@NonNull View itemView) {
            super(itemView);
            waybill =(TextView)itemView.findViewById(R.id.waybill);
            status =(TextView)itemView.findViewById(R.id.status);
            tujuan =(TextView)itemView.findViewById(R.id.tujuan);
            kurir =(TextView)itemView.findViewById(R.id.kurir);
            terahkirupdate =(TextView)itemView.findViewById(R.id.terakhir_update);
            resiada = (LinearLayout) itemView.findViewById(R.id.rvresiada);
            resitidakada = (LinearLayout) itemView.findViewById(R.id.rvresitidakada);
            statusresi = (TextView) itemView.findViewById(R.id.statusresi);
        }
    }
}
