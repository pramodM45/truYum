package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CartDaoSqlImpl implements CartDao{

    @Override
    public void addCartItem(long userId, long menuItemId) {
        try {
            Connection con=ConnectionHandler.getConnection();
            PreparedStatement st=con.prepareStatement("select exists(select * from cart where user_id=?)");
            st.setInt(1,(int) userId);
            ResultSet result=st.executeQuery();
            result.next();
            int bool=result.getInt(1);
            if(bool==0){
                PreparedStatement pst=con.prepareStatement("insert into cart values (?,?,?)");
                pst.setInt(1, (int) userId);
                pst.setInt(2, (int) userId);
                pst.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
                int rows=pst.executeUpdate();
            }

            PreparedStatement pst1=con.prepareStatement("insert into cart_items (cart_id,product_id) values (?,?)");
            pst1.setInt(1, (int) userId);
            pst1.setInt(2, (int) menuItemId);
            pst1.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
        List<MenuItem> arr=new ArrayList<>();
        try {
            Connection con=ConnectionHandler.getConnection();
            PreparedStatement pst=con.prepareStatement("select cart_id from cart where user_id=?");
            pst.setInt(1, (int) userId);
            int cart_id = 0;
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                cart_id=rs.getInt(1);
            }
            PreparedStatement pst1=con.prepareStatement("select product_id from cart_items where cart_id=?");
            pst1.setInt(1, cart_id);
            ResultSet rs1=pst1.executeQuery();
            while(rs1.next()){
                int menu_item_id=rs1.getInt(1);
                PreparedStatement pst2=con.prepareStatement("select p.product_id,p.product_name,p.price,p.active,p.date_of_launch,pc.category_name,p.free_delievery from product p inner join product_category pc on p.category_id=pc.category_id where product_id=?");
                pst2.setInt(1,menu_item_id);
                ResultSet rs3=pst2.executeQuery();
                if (!rs3.isBeforeFirst() ) {
                    throw new CartEmptyException();
                }
                else{
                    while(rs3.next()){

                        String active=rs3.getString("active");
                        boolean active1=active.equalsIgnoreCase("yes");
                        arr.add(new MenuItem((long)rs3.getInt("product_id"),rs3.getString("product_name"),
                                (float)rs3.getInt("price"),active1, (java.util.Date)rs3.getDate("date_of_launch"),rs3.getString("category_name"),rs3.getBoolean("free_delievery")));
                    }
                }

            }

            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arr;
    }

    @Override
    public void removeCartItem(long userId, long menuItemId) {
        try {
            Connection con=ConnectionHandler.getConnection();
            PreparedStatement pst=con.prepareStatement("select cart_id from cart where user_id=?");
            pst.setInt(1, (int) userId);
            int cart_id = 0;
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                cart_id=rs.getInt(1);
            }
            PreparedStatement pst2=con.prepareStatement("delete from cart_items where cart_id=? and product_id=?");
            pst2.setInt(1,cart_id);
            pst2.setInt(2, (int) menuItemId);
            pst2.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
