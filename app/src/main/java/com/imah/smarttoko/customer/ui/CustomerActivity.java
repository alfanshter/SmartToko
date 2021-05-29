package com.imah.smarttoko.customer.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.imah.smarttoko.R;
import com.imah.smarttoko.admin.ui.additem.LaporanFragment;
import com.imah.smarttoko.admin.ui.home.HomeFragment;
import com.imah.smarttoko.admin.ui.laporan.ProfilFragment;
import com.imah.smarttoko.customer.ui.dashboard.DashboardFragment;
import com.imah.smarttoko.customer.ui.laporan_customer.LaporanCustomerFragment;
import com.imah.smarttoko.customer.ui.profil.ProfilCustomerFragment;

import org.jetbrains.annotations.NotNull;

public class CustomerActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        // kita set default nya Home Fragment
        loadFragment(new DashboardFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view_customer);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_customer, fragment)
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
                fragment = new DashboardFragment();
                break;
            case R.id.navigation_laporan:
                fragment = new LaporanCustomerFragment();
                break;
            case R.id.navigation_profil:
                fragment = new ProfilCustomerFragment();
                break;
        }
        return loadFragment(fragment);
    }

}