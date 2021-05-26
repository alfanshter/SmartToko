package com.imah.smarttoko.admin;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.imah.smarttoko.R;
import com.imah.smarttoko.admin.ui.additem.AdditemFragment;
import com.imah.smarttoko.admin.ui.home.HomeFragment;
import com.imah.smarttoko.admin.ui.laporan.LaporanFragment;
import com.imah.smarttoko.databinding.ActivityAdminBinding;

import org.jetbrains.annotations.NotNull;

public class AdminActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityAdminBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // kita set default nya Home Fragment
        loadFragment(new HomeFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_admin, fragment)
                    .commit();
            return true;
        }
        return false;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_item:
                fragment = new AdditemFragment();
                break;
            case R.id.navigation_laporan:
                fragment = new LaporanFragment();
                break;
        }
        return loadFragment(fragment);
    }
}