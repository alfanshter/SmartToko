package com.imah.smarttoko.admin.ui.laporan;

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

import com.imah.smarttoko.admin.AdminActivity;
import com.imah.smarttoko.auth.AuthActivity;
import com.imah.smarttoko.databinding.FragmentProfilBinding;
import com.imah.smarttoko.session.Preferences;

public class ProfilFragment extends Fragment {

    private ProfilViewModel profilViewModelViewModel;
private FragmentProfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        profilViewModelViewModel =
                new ViewModelProvider(this).get(ProfilViewModel.class);

    binding = FragmentProfilBinding.inflate(inflater, container, false);

    binding.btnLogout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Preferences.setIsLogin(getActivity(),"");
            Intent intent = new Intent(requireContext().getApplicationContext(), AuthActivity.class);
            startActivity(intent);
            getActivity().finish();

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