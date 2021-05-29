package com.imah.smarttoko.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imah.smarttoko.R;
import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.database.entitas.Transaksi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewAdapter> {
    private List<Transaksi> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public TransaksiAdapter(Context context, List<Transaksi> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transaksi, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewAdapter holder, int position) {
        holder.namabarang.setText(String.format(context.getString(R.string.nama_barang)) + " : " + list.get(position).nama_barang);
        holder.kodebarang.setText(String.format(context.getString(R.string.kode_barang)) + " : " + list.get(position).kode_barang);
        holder.hargabarang.setText(String.format(context.getString(R.string.harga_barang)) + " : " + list.get(position).harga);
        holder.jumlahbarang.setText(String.format(context.getString(R.string.jumlah_barang)) + " : " + list.get(position).jumlah);
        holder.diskon.setText(String.format(context.getString(R.string.discount)) + " : " + list.get(position).diskon_barang + " %");
        holder.harga_total.setText(String.format(context.getString(R.string.total)) + " : " + list.get(position).harga_total);
        holder.waktu.setText(String.format(context.getString(R.string.waktu)) + " : " + list.get(position).waktu);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder {

        TextView namabarang, hargabarang, jumlahbarang, kodebarang,diskon,harga_total,waktu;

        public ViewAdapter(@NonNull @NotNull View itemView) {
            super(itemView);
            namabarang = itemView.findViewById(R.id.txt_namatransaksi);
            hargabarang = itemView.findViewById(R.id.txt_hargatransaksi);
            jumlahbarang = itemView.findViewById(R.id.txt_jumlahtransaksi);
            kodebarang = itemView.findViewById(R.id.txt_kodetransaksi);
            diskon = itemView.findViewById(R.id.txt_diskon);
            harga_total = itemView.findViewById(R.id.txt_hargatotal);
            waktu = itemView.findViewById(R.id.txt_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
