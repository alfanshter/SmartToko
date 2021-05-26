package com.imah.smarttoko.admin.ui.home;

import android.content.Intent;
import android.os.Bundle;
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

import com.imah.smarttoko.admin.TambahActivity;
import com.imah.smarttoko.admin.adapter.BarangAdapter;
import com.imah.smarttoko.database.AppDatabase;
import com.imah.smarttoko.databinding.FragmentHomeBinding;

import java.util.Objects;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private AppDatabase database;
    private BarangAdapter barangAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        database = AppDatabase.getInstance(requireContext().getApplicationContext());
        barangAdapter = new BarangAdapter(requireContext().getApplicationContext(), database.barangDao().getAll());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext().getApplicationContext(),RecyclerView.VERTICAL,false);
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}