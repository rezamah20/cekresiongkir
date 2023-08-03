package com.checkapp.cekresiongkir.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.checkapp.cekresiongkir.R;

public class SpinnerKurirAdapter extends BaseAdapter {

    Context context;
    int flags[];
    public String namaKurir[];
    public String kodeKurir[];
    LayoutInflater inflter;

    public SpinnerKurirAdapter(Context context){
        this.context = context;
        this.flags = new int[]{R.drawable.icon_anteraja, R.drawable.icon_deliveree, R.drawable.icon_jt, R.drawable.icon_jne, R.drawable.icon_lalamove, R.drawable.icon_lion, R.drawable.icon_ninjaexpress, R.drawable.icon_paxel, R.drawable.icon_rpx, R.drawable.icon_sap, R.drawable.icon_sicepat, R.drawable.icon_tiki, R.drawable.icon_21expressdse, R.drawable.icon_first, R.drawable.icon_idl, R.drawable.icon_rex, R.drawable.icon_ide, R.drawable.icon_sentral, R.drawable.icon_jtl, R.drawable.icon_star};
        this.namaKurir = new String[]{"Anter Aja", "Deliveree", "JNT", "JNE Express", "Lala Move", "Lion", "Ninja Express", "Paxel", "RPX", "SAP", "SiCepat", "Tiki", "21 Express (DSE)", "First Logistics", "IDL Cargo", "Royal Express Indonesia", "ID Express", "Sentral Cargo", "JTL Express", "Star Cargo"};
        this.kodeKurir = new String[]{"anteraja", "deliveree", "jnt", "jne", "lalamove", "lion", "ninja", "paxel", "rpx", "sap", "sicepat", "tiki", "dse", "first", "idl", "rex", "ide", "sentral", "jtl", "star"};
        inflter =(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.kurir_spinner_item, viewGroup, false);
        ImageView icon = (ImageView) view.findViewById(R.id.imageview);
        TextView names = (TextView) view.findViewById(R.id.textView);
        TextView kodekurir = (TextView) view.findViewById(R.id.textkurirkode);
        icon.setImageResource(flags[i]);
        names.setText(namaKurir[i]);
        kodekurir.setText(kodeKurir[i]);
        kodekurir.setVisibility(View.GONE);
        return view;
    }


}
