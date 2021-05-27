package com.imah.smarttoko.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imah.smarttoko.R;
import com.imah.smarttoko.database.entitas.Barang;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewAdapter> {
    private List<Barang> list;
    private Context context;

    public BarangAdapter(Context context, List<Barang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewAdapter holder, int position) {
        holder.namabarang.setText(String.format(context.getString(R.string.nama_barang)) + " : " + list.get(position).nama_barang);
        holder.kodebarang.setText(String.format(context.getString(R.string.kode_barang)) + " : " + list.get(position).kode_barang);
        holder.hargabarang.setText(String.format(context.getString(R.string.harga_barang)) + " : " + list.get(position).harga);
        holder.jumlahbarang.setText(String.format(context.getString(R.string.jumlah_barang)) + " : " + list.get(position).jumlah);
        holder.diskon.setText(String.format(context.getString(R.string.discount)) + " : " + list.get(position).diskon_barang +" %");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder {

        TextView namabarang, hargabarang, jumlahbarang, kodebarang,diskon;

        public ViewAdapter(@NonNull @NotNull View itemView) {
            super(itemView);
            namabarang = itemView.findViewById(R.id.txt_namabarang);
            hargabarang = itemView.findViewById(R.id.txt_hargabarang);
            jumlahbarang = itemView.findViewById(R.id.txt_jumlahbarang);
            kodebarang = itemView.findViewById(R.id.txt_kodebarang);
            diskon = itemView.findViewById(R.id.txt_diskon);
        }
    }
}
