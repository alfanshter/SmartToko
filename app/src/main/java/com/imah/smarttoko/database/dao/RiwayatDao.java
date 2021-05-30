package com.imah.smarttoko.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.imah.smarttoko.database.entitas.Riwayat;
import com.imah.smarttoko.database.entitas.Transaksi;

import java.util.List;

@Dao
public interface RiwayatDao {

    //Table Transaksi
    @Query("SELECT * FROM Riwayat")
    List<Riwayat> getAllRiwayat();

    @Query("INSERT INTO Riwayat (id_barang,kode_barang,nama_barang,harga_barang,jumlah_barang,diskon_barang,harga_total,waktu) VALUES (:id_barang,:kode_barang,:nama_barang,:harga_barang,:jumlah_barang,:diskon_barang,:harga_total,:waktu)")
    void insertRiwayat(int id_barang, String  kode_barang, String nama_barang, Integer harga_barang, Integer jumlah_barang, Integer diskon_barang,Integer harga_total, String waktu);

    @Delete
    void delete(Riwayat riwayat);


}
