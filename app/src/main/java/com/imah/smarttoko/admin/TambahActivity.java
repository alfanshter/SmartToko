package com.imah.smarttoko.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.imah.smarttoko.R;
import com.imah.smarttoko.database.AppDatabase;
import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.databinding.ActivityTambahBinding;

public class TambahActivity extends AppCompatActivity {
    //database
    private AppDatabase database;
    private ActivityTambahBinding binding;
    private String id;
    private Boolean isedit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tambah);
        database = AppDatabase.getInstance(getApplicationContext());


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        if (id != null) {
            if (Integer.parseInt(id) > 0) {
                isedit = true;
                Barang barang = database.barangDao().get(Integer.parseInt(id));
                binding.edtKodebarang.setText(barang.kode_barang);
                binding.edtJumlahbarang.setText(barang.jumlah.toString());
            } else {
                isedit = false;
            }

        }


        binding.btnInsertitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //split dulu integer dan string
                if (!TextUtils.isEmpty(binding.edtJumlahbarang.getText().toString()) && !TextUtils.isEmpty(binding.edtKodebarang.getText().toString())) {
                    int jumlah_barang = Integer.parseInt(binding.edtJumlahbarang.getText().toString());
                    String kode_barang = binding.edtKodebarang.getText().toString();

                    String[] part = kode_barang.split("(?<=\\D)(?=\\d)");
                    Integer diskon = Integer.valueOf(part[1]);

                    if (isedit) {
                        binding.edtKodebarang.setVisibility(View.VISIBLE);
                        database.barangDao().update(Integer.parseInt(id), jumlah_barang, binding.edtKodebarang.getText().toString(), diskon);
                        finish();
                    } else {
                        if (part[0].equals("AND")) {
                            database.barangDao().insertAll("AND" + diskon, "Android", 1000000, jumlah_barang, diskon);
                            finish();
                        } else if (part[0].equals("IOS")) {
                            database.barangDao().insertAll("IOS" + diskon, "Apple", 2000000, jumlah_barang, diskon);
                            finish();
                        } else if (part[0].equals("BLB")) {
                            database.barangDao().insertAll("BLB" + diskon, "Blackberry", 1750000, jumlah_barang, diskon);
                            finish();
                        } else if (part[0].equals("WNP")) {
                            database.barangDao().insertAll("WNP" + diskon, "Windows Phone", 2500000, jumlah_barang, diskon);
                            finish();
                        } else {
                            Snackbar.make(findViewById(R.id.rv_tambah), R.string.barang_tidakada, Snackbar.LENGTH_LONG).show();
                        }

                    }

                }else {
                    Snackbar.make(findViewById(R.id.rv_tambah), R.string.kolom_kosong, Snackbar.LENGTH_LONG).show();

                }

            }
        });

    }
}