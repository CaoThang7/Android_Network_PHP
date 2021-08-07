package com.example.anroid_networking.mysql.Database.Local;

import com.example.anroid_networking.mysql.Database.DataSource.ICartDataSource;
import com.example.anroid_networking.mysql.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public class CartDataSource implements ICartDataSource {
    private CartDao cartDao;
    private static CartDataSource instance;

    public CartDataSource(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public static CartDataSource getInstance(CartDao cartDao)
    {
        if(instance == null)
            instance=new CartDataSource(cartDao);
        return instance;
    }


    @Override
    public Flowable<List<Cart>> getCartItems() {
        return cartDao.getCartItems();
    }

    @Override
    public Flowable<List<Cart>> getCartItemsById(int cartItemId) {
        return cartDao.getCartItemsById(cartItemId);
    }

    @Override
    public int countCartItems() {
        return cartDao.countCartItems();
    }

    @Override
    public void emptyCart() {
       cartDao.emptyCart();
    }

    @Override
    public void insertToCart(Cart... carts) {
      cartDao.insertToCart(carts);
    }

    @Override
    public void updateCart(Cart... carts) {
      cartDao.updateCart(carts);
    }

    @Override
    public void deleteCartItem(Cart cart) {
      cartDao.deleteCartItem(cart);
    }
}
