package com.imah.smarttoko.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Barang {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "kode_barang")
    public String kode_barang;
    @ColumnInfo(name = "nama_barang")
    public String nama_barang;
    @ColumnInfo(name = "harga_barang")
    public Integer harga;
    @ColumnInfo(name = "jumlah_barang")
    public Integer jumlah;
    @ColumnInfo(name = "diskon_barang")
    public Integer diskon_barang;


}
