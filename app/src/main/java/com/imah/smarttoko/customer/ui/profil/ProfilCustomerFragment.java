package com.imah.smarttoko.customer.ui.profil;

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

import com.imah.smarttoko.auth.AuthActivity;
import com.imah.smarttoko.databinding.FragmentProfilcustomerBinding;
import com.imah.smarttoko.session.Preferences;

public class ProfilCustomerFragment extends Fragment {

    private ProfilCustomerViewModel notificationsViewModel;
private FragmentProfilcustomerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(ProfilCustomerViewModel.class);

    binding = FragmentProfilcustomerBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.setIsLogin(getActivity(),"");
                Intent intent = new Intent(requireContext().getApplicationContext(), AuthActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}