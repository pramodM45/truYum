package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.MenuItem;
import com.cognizant.truYum.util.DateUtil;

import java.text.ParseException;
import java.util.List;

public class MenuItemDaoSqlImplTest {
    public static void main(String[] args) throws ParseException {
        MenuItemDaoSqlImplTest obj=new MenuItemDaoSqlImplTest();
        obj.testModifyMenuItem();
    }
    public void testGetMenuItemListAdmin(){
        MenuItemDaoSqlImpl obj=new MenuItemDaoSqlImpl();
        List<MenuItem> arr=obj.getMenuItemListAdmin();
        arr.forEach(System.out::println);
    }
    public void testGetMenuItemListCustomer(){
        MenuItemDaoSqlImpl obj=new MenuItemDaoSqlImpl();
        List<MenuItem> arr=obj.getMenuItemListCustomer();
        arr.forEach(System.out::println);
    }
    public void testGetMenuItem(){
        MenuItemDaoSqlImpl obj=new MenuItemDaoSqlImpl();
        MenuItem obj1=obj.getMenuItem(2);
        System.out.println(obj1);
    }

    public void testModifyMenuItem() throws ParseException {
        MenuItemDaoSqlImpl obj=new MenuItemDaoSqlImpl();
        MenuItem obj1=new MenuItem(5,"brownie",30.0f,true, DateUtil.convertToDate("02/11/2021"),"Main Course",true);
        obj.modifyMenuItem(obj1);
    }

}
