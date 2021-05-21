package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.Cart;
import com.cognizant.truYum.model.MenuItem;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CartDaoCollectionImpl implements CartDao {
    private static HashMap<Long,Cart> userCarts;

    public CartDaoCollectionImpl() {
        if (userCarts==null){
            userCarts=new HashMap<>();
        }
    }

    @Override
    public void addCartItem(long userId, long menuItemId) {
        if(userCarts.containsKey(userId)){
            Cart obj=userCarts.get(userId);
            List<MenuItem> cartItems=obj.getMenuItemList();
            try {
                MenuItemDaoCollectionImpl menuObj=new MenuItemDaoCollectionImpl();
                cartItems.add(menuObj.getMenuItem(menuItemId));
//                obj.setMenuItemList(cartItems);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{
            List<MenuItem> arr=new ArrayList<>();
            try {
                MenuItemDaoCollectionImpl menuObj=new MenuItemDaoCollectionImpl();
                List<MenuItem> menuItem=menuObj.getMenuItemListCustomer().stream().filter(i->i.getId()==menuItemId).collect(Collectors.toList());
                arr.add(menuObj.getMenuItem(menuItemId));
                Cart obj=new Cart();
                obj.setMenuItemList(arr);
                userCarts.put(userId, obj);
            } catch (ParseException e) {
                e.printStackTrace();
            }



        }

    }

    @Override
    public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
        Cart obj=userCarts.get(userId);
        if (obj.getMenuItemList().size()==0){
            throw new CartEmptyException();
        }
        else{
            List<MenuItem> cartItems=obj.getMenuItemList();
            double totalPrice=cartItems.stream().mapToDouble(i->i.getPrice()).reduce(0,(i,j)->i+j);
            obj.setTotal(totalPrice);
            return cartItems;
        }
//        return obj.getMenuItemList();
    }

    @Override
    public void removeCartItem(long userId, long menuItemId) {
        Cart obj=userCarts.get(userId);
        List<MenuItem> cartItems=obj.getMenuItemList();
        MenuItem item=cartItems.stream().filter(i->i.getId()==menuItemId).findFirst().orElse(null);
        cartItems.remove(item);
//        obj.setMenuItemList(cartItems);
    }
}
