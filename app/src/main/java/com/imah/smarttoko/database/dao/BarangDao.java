package com.imah.smarttoko.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.imah.smarttoko.database.entitas.Barang;
import com.imah.smarttoko.database.entitas.Transaksi;

import java.sql.Timestamp;
import java.util.List;

@Dao
public interface BarangDao {
    @Query("SELECT * FROM Barang")
    List<Barang> getAll();

    @Query("INSERT INTO Barang (kode_barang,nama_barang,harga_barang,jumlah_barang,diskon_barang) VALUES(:kode_barang,:nama_barang,:harga_barang,:jumlah_barang,:diskon_barang)")
    void insertAll(String kode_barang,String nama_barang, Integer harga_barang, Integer jumlah_barang, Integer diskon_barang);

    @Query("UPDATE Barang SET  jumlah_barang=:jumlah_barang,kode_barang=:kode_barang,diskon_barang=:diskon_barang WHERE id=:id")
    void update(int id, Integer jumlah_barang, String kode_barang,Integer diskon_barang);

    @Query("SELECT * FROM Barang WHERE id=:id")
    Barang get(int id);

    @Delete
    void delete(Barang barang);


}
