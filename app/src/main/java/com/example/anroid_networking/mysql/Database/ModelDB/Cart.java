package com.example.anroid_networking.mysql.Database.ModelDB;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cart")
public class Cart {
    @Nullable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "link")
    public String link;


    @ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "capdo")
    public int capdo;

    @ColumnInfo(name = "loai")
    public int loai;

    @ColumnInfo(name = "toppingExtras")
    public String toppingExtras;





}
