package com.imah.smarttoko.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity
public class Transaksi {
    @PrimaryKey
    public int id_transaksi;

    @ColumnInfo(name = "id_barang")
    public String id_barang;
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
    @ColumnInfo(name = "harga_total")
    public Integer harga_total;
    @ColumnInfo(name = "waktu")
    public String waktu;


}
