package com.example.anroid_networking.mysql.Database.Local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.anroid_networking.mysql.Database.ModelDB.Cart;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {
   public abstract CartDao cartDao();
   private static CartDatabase instance;

   public static CartDatabase getInstance(Context context){
       if((instance == null))
           instance= Room.databaseBuilder(context,CartDatabase.class,"mysqlexample")
                   .allowMainThreadQueries()
                   .build();
       return instance;
   }
}
