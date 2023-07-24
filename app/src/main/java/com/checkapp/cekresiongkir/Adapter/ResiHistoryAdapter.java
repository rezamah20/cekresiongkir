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
import com.checkapp.cekresiongkir.network.cekresi.CekResi;

public class ResiHistoryAdapter extends RecyclerView.Adapter<ResiHistoryAdapter.ResiHistoryViewHolder> {

    Activity activity;
    Context context;
    CekResi data;

    public ResiHistoryAdapter(Activity activity, Context context, CekResi data){
        this.activity = activity;
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public ResiHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resi_histori, parent, false);
        return new ResiHistoryAdapter.ResiHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResiHistoryViewHolder holder, int position) {
        holder.history_status.setText(data.getHistory().get(position).getStatus());
        holder.history_note.setText(data.getHistory().get(position).getNote());
        holder.history_update.setText(data.getHistory().get(position).getUpdated_at());
    }

    @Override
    public int getItemCount() {
        return data.getHistory().size();
    }

    public class ResiHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView history_status, history_note, history_update;

        public ResiHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            history_status =(TextView)itemView.findViewById(R.id.history_status);
            history_note =(TextView)itemView.findViewById(R.id.history_note);
            history_update =(TextView)itemView.findViewById(R.id.history_terakhirupdate);
        }
    }
}
