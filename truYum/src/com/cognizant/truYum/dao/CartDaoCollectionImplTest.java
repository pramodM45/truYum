package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.MenuItem;

import java.util.List;

public class CartDaoCollectionImplTest {
    public static void main(String[] args) {
        CartDaoCollectionImplTest obj=new CartDaoCollectionImplTest();
        try {
            obj.testAddCartItem();
            obj.testRemoveCartItem();
            obj.testGetAllCartItems();
        } catch (CartEmptyException e) {
            System.out.println("cart for this user is empty");
        }

    }
    public void testAddCartItem() throws CartEmptyException {
        CartDaoCollectionImpl obj=new CartDaoCollectionImpl();
        obj.addCartItem(1,1);
        obj.addCartItem(1,2);
        List<MenuItem> cartItems=obj.getAllCartItems(1);
        cartItems.forEach(System.out::println);
    }
    public void testGetAllCartItems() throws CartEmptyException {
        CartDaoCollectionImpl obj=new CartDaoCollectionImpl();
        obj.getAllCartItems(1).forEach(System.out::println);
    }
    public void testRemoveCartItem() throws CartEmptyException {
        CartDaoCollectionImpl obj=new CartDaoCollectionImpl();
        obj.removeCartItem(1,1);
        obj.getAllCartItems(1).forEach(System.out::println);
    }
}
