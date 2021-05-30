package com.imah.smarttoko.customer.ui.dashboard;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imah.smarttoko.adapter.BarangAdapter;
import com.imah.smarttoko.customer.ui.DetailitemActivity;
import com.imah.smarttoko.database.AppDatabase;
import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
private FragmentDashboardBinding binding;

    private AppDatabase database;
    private BarangAdapter barangAdapter;
    private List<Barang> list = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

    binding = FragmentDashboardBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


        database = AppDatabase.getInstance(requireContext().getApplicationContext());
        list.clear();
        list.addAll(database.barangDao().getAll());
        barangAdapter = new BarangAdapter(requireContext().getApplicationContext(), list);
        barangAdapter.setDialog(new BarangAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                Log.d("tester", String.valueOf(list.get(position).id));
                Intent intent = new Intent(requireContext().getApplicationContext(), DetailitemActivity.class);
                intent.putExtra("id", String.valueOf(list.get(position).id));
                startActivity(intent);
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext().getApplicationContext(), RecyclerView.VERTICAL, false);
        binding.tvItemsell.setLayoutManager(layoutManager);
        binding.tvItemsell.setAdapter(barangAdapter);

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                list.clear();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getData(newText);
                return false;
            }
        });

        return root;
    }

    private void getData(String searchTerm){
        list.clear();
        if (!TextUtils.isEmpty(searchTerm)){
            String serchtext =searchTerm.substring(0,1).toUpperCase() +searchTerm.substring(1);
            list.addAll(database.barangDao().getnama(serchtext));

            barangAdapter = new BarangAdapter(requireContext().getApplicationContext(), list);
            barangAdapter.setDialog(new BarangAdapter.Dialog() {
                @Override
                public void onClick(int position) {
                    Log.d("tester", String.valueOf(list.get(position).id));
                    Intent intent = new Intent(requireContext().getApplicationContext(), DetailitemActivity.class);
                    intent.putExtra("id", String.valueOf(list.get(position).id));
                    startActivity(intent);
                }
            });


            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext().getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.tvItemsell.setLayoutManager(layoutManager);
            binding.tvItemsell.setAdapter(barangAdapter);

        }else {
            list.clear();
            list.addAll(database.barangDao().getAll());
            barangAdapter = new BarangAdapter(requireContext().getApplicationContext(), list);
            barangAdapter.setDialog(new BarangAdapter.Dialog() {
                @Override
                public void onClick(int position) {
                    Log.d("tester", String.valueOf(list.get(position).id));
                    Intent intent = new Intent(requireContext().getApplicationContext(), DetailitemActivity.class);
                    intent.putExtra("id", String.valueOf(list.get(position).id));
                    startActivity(intent);
                }
            });


            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext().getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.tvItemsell.setLayoutManager(layoutManager);
            binding.tvItemsell.setAdapter(barangAdapter);

        }


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}