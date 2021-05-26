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

    @Insert
    void insertAll(Barang... barangs);

    @Delete
    void delete(Barang barang);
}
