package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.MenuItem;
import com.cognizant.truYum.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuItemDaoSqlImpl implements MenuItemDao{
    @Override
    public List<MenuItem> getMenuItemListAdmin() {
        List<MenuItem> arr=new ArrayList<>();
        try {
            Connection con=ConnectionHandler.getConnection();
            PreparedStatement pst=con.prepareStatement("select p.product_id,p.product_name,p.price,p.active,p.date_of_launch,pc.category_name,p.free_delievery from product p\n" +
                    "inner join product_category pc on p.category_id=pc.category_id");
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                String active=rs.getString("active");
                boolean active1=active.equalsIgnoreCase("yes");
                arr.add(new MenuItem((long)rs.getInt("product_id"),rs.getString("product_name"),
                                (float)rs.getInt("price"),active1, (Date)rs.getDate("date_of_launch"),rs.getString("category_name"),rs.getBoolean("free_delievery")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arr;
    }

    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        List<MenuItem> arr=new ArrayList<>();
        try {
            Connection con=ConnectionHandler.getConnection();
            PreparedStatement pst=con.prepareStatement("select p.product_id,p.product_name,p.price,p.active,p.date_of_launch,pc.category_name,p.free_delievery from product p inner join product_category pc on p.category_id=pc.category_id where p.active=? and p.date_of_launch<=?");
            pst.setString(1,"yes");
            Date date=new Date();
            java.sql.Date today=new java.sql.Date(date.getTime());
            pst.setDate(2,today);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                String active=rs.getString("active");
                boolean active1=active.equalsIgnoreCase("yes");
                arr.add(new MenuItem((long)rs.getInt("product_id"),rs.getString("product_name"),
                        (float)rs.getInt("price"),active1, (Date)rs.getDate("date_of_launch"),rs.getString("category_name"),rs.getBoolean("free_delievery")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;

    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        int category_id;
        if (menuItem.getCategory().equalsIgnoreCase("main course")){
            category_id=1;
        }
        else if (menuItem.getCategory().equalsIgnoreCase("starters")){
            category_id=2;
        }
        else{
            category_id=3;
        }
        try {
            Connection con=ConnectionHandler.getConnection();
            PreparedStatement pst=con.prepareStatement("update product set product_name=?,price=?,active=?,date_of_launch=?,category_id=?,free_delievery=? where product_id=?");
//            pst.setLong(1,menuItem.getId());
            pst.setString(1,menuItem.getName());
            pst.setInt(2, (int) menuItem.getPrice());
            pst.setBoolean(3,menuItem.isActive());
            pst.setDate(4,new java.sql.Date(menuItem.getDateOfLaunch().getTime()));
            pst.setInt(5,category_id);
            pst.setBoolean(6, menuItem.isFreeDelivery());
            pst.setInt(7, (int) menuItem.getId());
            int noOfRows= pst.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public MenuItem getMenuItem(long menuItemId) {
        MenuItem obj=null;
        try {
            Connection con=ConnectionHandler.getConnection();
            PreparedStatement pst=con.prepareStatement("select p.product_id,p.product_name,p.price,p.active,p.date_of_launch,pc.category_name,p.free_delievery from product p inner join product_category pc on p.category_id=pc.category_id where product_id=?");
            pst.setInt(1,(int)menuItemId);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                String active=rs.getString("active");
                boolean active1=active.equalsIgnoreCase("yes");
                obj=new MenuItem((long)rs.getInt("product_id"),rs.getString("product_name"),
                        (float)rs.getInt("price"),active1, (Date)rs.getDate("date_of_launch"),rs.getString("category_name"),rs.getBoolean("free_delievery"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;

    }
}
