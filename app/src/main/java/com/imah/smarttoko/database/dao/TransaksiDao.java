package com.imah.smarttoko.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.database.entitas.Transaksi;

import java.sql.Timestamp;
import java.util.List;

@Dao
public interface TransaksiDao {

    //Table Transaksi
    @Query("SELECT * FROM Transaksi")
    List<Transaksi> getAllTransaksi();

    @Query("INSERT INTO Transaksi (id_barang,kode_barang,nama_barang,harga_barang,jumlah_barang,diskon_barang,harga_total,waktu) VALUES (:id_barang,:kode_barang,:nama_barang,:harga_barang,:jumlah_barang,:diskon_barang,:harga_total,:waktu)")
    void insertTransaksi(int id_barang, String  kode_barang, String nama_barang, Integer harga_barang, Integer jumlah_barang, Integer diskon_barang,Integer harga_total, String waktu);

    @Delete
    void delete(Transaksi transaksi);


}
