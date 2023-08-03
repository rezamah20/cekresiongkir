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
import com.checkapp.cekresiongkir.network.cekresi.rajaongkir.CekResiRajaOngkir;

import java.util.List;

public class ResiAdapter extends RecyclerView.Adapter<ResiAdapter.ResiViewHolder> {
    Activity activity;
    Context context;
    CekResi data;
    CekResiRajaOngkir cekResiRajaOngkir;

    public ResiAdapter(Activity activity, Context context, CekResi data, CekResiRajaOngkir cekResiRajaOngkir){
        this.activity = activity;
        this.context = context;
        this.data = data;
        this.cekResiRajaOngkir = cekResiRajaOngkir;
    }

    @NonNull
    @Override
    public ResiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resi, parent, false);
        return new ResiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResiViewHolder holder, int position) {


        if (cekResiRajaOngkir.getRajaongkir() != null){
            holder.waybill.setText(cekResiRajaOngkir.getRajaongkir().getQuery().waybill);
            holder.status.setText(cekResiRajaOngkir.getRajaongkir().getResult().getSummary().getStatus());
            holder.tujuan.setText(cekResiRajaOngkir.getRajaongkir().getResult().getSummary().getReceiver_name());
            holder.kurir.setText(cekResiRajaOngkir.getRajaongkir().getResult().getSummary().getCourier_name());
            holder.terahkirupdate.setText(cekResiRajaOngkir.getRajaongkir().getResult().getManifest().get(0).getManifest_date()+" "+cekResiRajaOngkir.getRajaongkir().getResult().getManifest().get(0).getManifest_time());
            holder.resiada.setVisibility(View.VISIBLE);
            holder.resitidakada.setVisibility(View.GONE);

        } else if (data != null) {
            holder.waybill.setText(data.getWaybill_id());
            holder.status.setText(data.getStatus());
            holder.tujuan.setText(data.getDestination().getContact_name());
            holder.kurir.setText(data.getCourier().getCompany());
            holder.terahkirupdate.setText(data.getHistory().get(data.getHistory().size() - 1).getUpdated_at());
            holder.resiada.setVisibility(View.VISIBLE);
            holder.resitidakada.setVisibility(View.GONE);

        }else {
           // Log.d("ini json", "Resi Tidak Di temukan = "+data);
            holder.resiada.setVisibility(View.GONE);
            holder.resitidakada.setVisibility(View.VISIBLE);
            holder.statusresi.setText("Resi Tidak Di Temukan, Coba Lagi Nanti");
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
