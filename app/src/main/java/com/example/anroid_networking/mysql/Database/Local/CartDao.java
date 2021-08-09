package com.example.anroid_networking.mysql.Database.Local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.anroid_networking.mysql.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CartDao {
    @Query("SELECT * FROM Cart")
    Flowable<List<Cart>> getCartItems();

    @Query("SELECT * FROM Cart Where id=:cartItemId")
    Flowable<List<Cart>> getCartItemsById(int cartItemId);

    @Query("SELECT COUNT(*) from Cart")
    int countCartItems();

    @Query("DELETE FROM Cart")
    void emptyCart();

    @Insert
    void insertToCart(Cart...carts);

    @Update
    void updateCart(Cart...carts);

    @Delete
    void deleteCartItem(Cart cart);


    @Query("SELECT SUM(Price) from Cart")
    float sumPrice();
}
