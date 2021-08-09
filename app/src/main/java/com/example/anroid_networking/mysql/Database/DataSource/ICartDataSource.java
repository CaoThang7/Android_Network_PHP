package com.example.anroid_networking.mysql.Database.DataSource;

import com.example.anroid_networking.mysql.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public interface ICartDataSource {
    Flowable<List<Cart>> getCartItems();
    Flowable<List<Cart>> getCartItemsById(int cartItemId);
    int countCartItems();
    void emptyCart();
    void insertToCart(Cart...carts);
    void updateCart(Cart...carts);
    void deleteCartItem(Cart cart);
    float sumPrice();
}
