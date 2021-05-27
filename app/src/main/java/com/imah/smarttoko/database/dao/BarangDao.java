package com.imah.smarttoko.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.imah.smarttoko.database.entitas.Barang;

import java.util.List;

@Dao
public interface BarangDao {
    @Query("SELECT * FROM Barang")
    List<Barang> getAll();

    @Query("INSERT INTO Barang (kode_barang,nama_barang,harga_barang,jumlah_barang) VALUES(:kode_barang,:nama_barang,:harga_barang,:jumlah_barang)")
    void insertAll(String kode_barang,String nama_barang, Integer harga_barang, Integer jumlah_barang);

    @Delete
    void delete(Barang barang);
}
