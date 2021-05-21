package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.MenuItem;

import java.util.List;

public class CartDaoSqlImplTest {
    public static void main(String[] args) {
        CartDaoSqlImpl obj=new CartDaoSqlImpl();
//        obj.addCartItem(1,1);
//        obj.addCartItem(1,2);
//        obj.addCartItem(1,4);
//        obj.addCartItem(2,5);
//        try {
//            List<MenuItem> arr=obj.getAllCartItems(2);
//            arr.forEach(System.out::println);
//        } catch (CartEmptyException e) {
//            e.printStackTrace();
//        }
        obj.removeCartItem(1,4);

    }
}
