package com.imah.smarttoko.admin.ui.additem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imah.smarttoko.R;
import com.imah.smarttoko.adapter.RiwayatAdapter;
import com.imah.smarttoko.adapter.TransaksiAdapter;
import com.imah.smarttoko.database.AppDatabase;
import com.imah.smarttoko.database.entitas.Riwayat;
import com.imah.smarttoko.database.entitas.Transaksi;
import com.imah.smarttoko.databinding.FragmentLaporanBinding;

import java.util.ArrayList;
import java.util.List;

public class LaporanFragment extends Fragment {

    private LaporanViewModel dashboardViewModel;
private FragmentLaporanBinding binding;

    private AppDatabase database;
    private RiwayatAdapter riwayatAdapter;
    private List<Riwayat> list = new ArrayList<>();
    private AlertDialog.Builder dialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(LaporanViewModel.class);

    binding = FragmentLaporanBinding.inflate(inflater, container, false);


        database = AppDatabase.getInstance(requireContext().getApplicationContext());
        list.clear();
        list.addAll(database.riwayatDao().getAllRiwayat());
        riwayatAdapter = new RiwayatAdapter(requireContext().getApplicationContext(), list);
        riwayatAdapter.setDialog(new RiwayatAdapter.Dialog() {
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
                                Riwayat riwayat = list.get(position);
                                database.riwayatDao().delete(riwayat);
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
        binding.rvReportitem.setAdapter(riwayatAdapter  );


        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.riwayatDao().getAllRiwayat());
        riwayatAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}