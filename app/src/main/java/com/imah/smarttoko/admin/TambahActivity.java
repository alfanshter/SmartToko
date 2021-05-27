package com.imah.smarttoko.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import com.imah.smarttoko.R;
import com.imah.smarttoko.database.AppDatabase;
import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.databinding.ActivityTambahBinding;

public class TambahActivity extends AppCompatActivity {
    //database
    private AppDatabase database;
    private ActivityTambahBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tambah);
        database = AppDatabase.getInstance(getApplicationContext());

        binding.btnInsertitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int harga_barang = Integer.parseInt(binding.edtHargabarang.getText().toString());
                int jumlah_barang = Integer.parseInt(binding.edtJumlahbarang.getText().toString());
                String kode_barang = binding.edtKodebarang.getText().toString();

                //split dulu integer dan string
                String[] part = kode_barang.split("(?<=\\D)(?=\\d)");
                Integer diskon = Integer.valueOf(part[1]);

                database.barangDao().insertAll(binding.edtKodebarang.getText().toString(), binding.edtNamabarang.getText().toString(),
                        harga_barang, jumlah_barang, diskon);
                finish();
            }
        });

    }
}