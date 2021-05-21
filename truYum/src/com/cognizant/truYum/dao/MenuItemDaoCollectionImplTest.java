package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.MenuItem;
import com.cognizant.truYum.util.DateUtil;

import java.text.ParseException;
import java.util.List;

public class MenuItemDaoCollectionImplTest {
    public static void main(String[] args) {
        MenuItemDaoCollectionImplTest obj=new MenuItemDaoCollectionImplTest();
        try {
            obj.testModifyMenuItem();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void testGetMenuItemListAdmin() throws ParseException{
        MenuItemDao obj=new MenuItemDaoCollectionImpl();
        List<MenuItem> List=obj.getMenuItemListAdmin();
        List.stream().forEach(System.out::println);

    }
    public void testGetMenuItemListCustomer() throws ParseException {
        MenuItemDao obj=new MenuItemDaoCollectionImpl();
        List<MenuItem> List=obj.getMenuItemListCustomer();
        List.stream().forEach(System.out::println);

    }
    public void testModifyMenuItem() throws ParseException {
        MenuItem obj=new MenuItem(5,"Chocolate Brownies",32.0f,true, DateUtil.convertToDate("02/11/2021"),"Dessert",false);
        MenuItemDao obj1=new MenuItemDaoCollectionImpl();
        obj1.modifyMenuItem(obj);
        System.out.println(obj1.getMenuItem(5));

    }
    public void testGetMenuItem(){

    }
}
