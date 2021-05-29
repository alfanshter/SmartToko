package com.imah.smarttoko.customer.ui.laporan_customer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imah.smarttoko.R;
import com.imah.smarttoko.adapter.BarangAdapter;
import com.imah.smarttoko.adapter.TransaksiAdapter;
import com.imah.smarttoko.admin.TambahActivity;
import com.imah.smarttoko.customer.ui.DetailitemActivity;
import com.imah.smarttoko.database.AppDatabase;
import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.database.entitas.Transaksi;
import com.imah.smarttoko.databinding.FragmentLaporancustomerBinding;

import java.util.ArrayList;
import java.util.List;

public class LaporanCustomerFragment extends Fragment {

    private LaporanCustomerViewModel homeViewModel;
private FragmentLaporancustomerBinding binding;

    private AppDatabase database;
    private TransaksiAdapter transaksiAdapter;
    private List<Transaksi> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentLaporancustomerBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        database = AppDatabase.getInstance(requireContext().getApplicationContext());
        list.clear();
        list.addAll(database.transaksiDao().getAllTransaksi());
        transaksiAdapter = new TransaksiAdapter(requireContext().getApplicationContext(), list);
        transaksiAdapter.setDialog(new TransaksiAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {String.format(getString(R.string.hapus))};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                //untuk menghapus data
                                Transaksi transaksi = list.get(position);
                                database.transaksiDao().delete(transaksi);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext().getApplicationContext(), RecyclerView.VERTICAL, false);
        binding.rvReportitem.setLayoutManager(layoutManager);
        binding.rvReportitem.setAdapter(transaksiAdapter);


        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}