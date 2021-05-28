package com.imah.smarttoko.admin.ui.additem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imah.smarttoko.databinding.FragmentLaporanBinding;

public class LaporanFragment extends Fragment {

    private LaporanViewModel dashboardViewModel;
private FragmentLaporanBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(LaporanViewModel.class);

    binding = FragmentLaporanBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}