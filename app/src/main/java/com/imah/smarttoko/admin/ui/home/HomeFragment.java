package com.imah.smarttoko.admin.ui.home;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imah.smarttoko.MainActivity;
import com.imah.smarttoko.R;
import com.imah.smarttoko.admin.TambahActivity;
import com.imah.smarttoko.admin.adapter.BarangAdapter;
import com.imah.smarttoko.database.AppDatabase;
import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private AppDatabase database;
    private BarangAdapter barangAdapter;
    private List<Barang> list = new ArrayList<>();
    private AlertDialog.Builder dialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        database = AppDatabase.getInstance(requireContext().getApplicationContext());
        list.clear();
        list.addAll(database.barangDao().getAll());
        barangAdapter = new BarangAdapter(requireContext().getApplicationContext(), list);
        barangAdapter.setDialog(new BarangAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {String.format(getString(R.string.edit)), String.format(getString(R.string.hapus))};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Log.d("tester", String.valueOf(list.get(position).id));
                                Intent intent = new Intent(requireContext().getApplicationContext(), TambahActivity.class);
                                intent.putExtra("id", String.valueOf(list.get(position).id));
                                startActivity(intent);
                                break;
                            case 1:
                                //untuk menghapus data
                                Barang barang = list.get(position);
                                database.barangDao().delete(barang);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext().getApplicationContext(), RecyclerView.VERTICAL, false);
        binding.rvListitem.setLayoutManager(layoutManager);
        binding.rvListitem.setAdapter(barangAdapter);

        binding.btnAdditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext().getApplicationContext(), TambahActivity.class));
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.barangDao().getAll());
        barangAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}