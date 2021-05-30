package com.imah.smarttoko.customer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.imah.smarttoko.R;
import com.imah.smarttoko.database.AppDatabase;
import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.database.entitas.Transaksi;
import com.imah.smarttoko.databinding.ActivityDetailitemBinding;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailitemActivity extends AppCompatActivity {
    private String id;
    private Integer jumlah_counter = 0;
    int hargatotal = 0;
    int diskon = 0;
    int jumlah_diskon = 0;
    int jumlah_barang = 0;
    private Boolean isedit = false;
    private ActivityDetailitemBinding binding;
    private AppDatabase database;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
    String format = simpleDateFormat.format(new Date());
    String ts =  format.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailitem);
        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        if (id != null) {
            if (Integer.parseInt(id) > 0) {
                Barang barang = database.barangDao().get(Integer.parseInt(id));
                database.barangDao().get(Integer.parseInt(id));
                binding.namaBarang.setText(String.format(getString(R.string.nama_barang)) + " : " + barang.nama_barang);
                binding.hargaBarang.setText(String.format(getString(R.string.harga_barang)) + " : " + barang.harga);
                binding.diskonBarang.setText(String.format(getString(R.string.discount)) + " : " + barang.diskon_barang);
                binding.jumlahBarang.setText(String.format(getString(R.string.jumlah_barang)) + " : " + barang.jumlah);
                binding.btnDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (jumlah_counter > 0) {
                            jumlah_counter -= 1;
                            binding.txtCounter.setText(jumlah_counter.toString());
                            diskon = (barang.harga * barang.diskon_barang) / 100;
                            jumlah_diskon = jumlah_counter * diskon;
                            hargatotal = (jumlah_counter * barang.harga);
                            binding.hargatotal.setText(String.valueOf(hargatotal));
                            binding.txtDiscount.setText(String.format(getString(R.string.discount)) + " : " + jumlah_diskon);

                        }
                    }
                });

                binding.btnUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (jumlah_counter >= 0) {
                            jumlah_counter += 1;
                            binding.txtCounter.setText(jumlah_counter.toString());
                            diskon = (barang.harga * barang.diskon_barang) / 100;
                            jumlah_diskon = jumlah_counter * diskon;
                            hargatotal = (jumlah_counter * barang.harga) ;
                            binding.hargatotal.setText(String.valueOf(hargatotal));
                            binding.txtDiscount.setText(String.format(getString(R.string.discount)) + " : " + jumlah_diskon);


                        }
                    }
                });

                binding.proses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        diskon = (barang.harga * barang.diskon_barang * jumlah_counter) / 100;
                        hargatotal = (jumlah_counter * barang.harga) - diskon;
                        jumlah_barang = barang.jumlah - jumlah_counter;
                        Barang barang = database.barangDao().get(Integer.parseInt(id));
                        if (Integer.parseInt(binding.txtCounter.getText().toString()) > barang.jumlah ){
                            Snackbar.make(findViewById(R.id.detailitem), R.string.item_limit, Snackbar.LENGTH_LONG).show();
                        }
                        else if (Integer.parseInt(binding.txtCounter.getText().toString()) == 0){
                            Snackbar.make(findViewById(R.id.detailitem), R.string.kolom_kosong, Snackbar.LENGTH_LONG).show();

                        }
                        else {
                            database.transaksiDao().insertTransaksi(barang.id, barang.kode_barang, barang.nama_barang,
                                    barang.harga, Integer.parseInt(binding.txtCounter.getText().toString()), barang.diskon_barang, hargatotal, ts);
                            database.riwayatDao().insertRiwayat(barang.id, barang.kode_barang, barang.nama_barang,
                                    barang.harga, Integer.parseInt(binding.txtCounter.getText().toString()), barang.diskon_barang, hargatotal, ts);
                            database.barangDao().updatejumlah(jumlah_barang,barang.id);
                            Toast.makeText(getBaseContext(), "Transaksi berhasil", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });


            }
        }

    }
}