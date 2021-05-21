package com.cognizant.truYum.asyncpractise;
import com.cognizant.truYum.dao.ConnectionHandler;
import com.cognizant.truYum.model.MenuItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.sql.Connection;
public class practice1 {
    public static  MenuItem  funcAsync(int id){
        MenuItem obj1=null;
        try {
            Connection con= ConnectionHandler.getConnection();
            PreparedStatement pst=con.prepareStatement("select p.product_id,p.product_name,p.price,p.active,p.date_of_launch,pc.category_name,p.free_delievery from product p inner join product_category pc on p.category_id=pc.category_id where product_id=?");
            pst.setInt(1,id);
            ResultSet rs= pst.executeQuery();
//            System.out.println("heyhey");
            System.out.println(Thread.currentThread());
            while(rs.next()){
                String active=rs.getString("active");
                boolean active1=active.equalsIgnoreCase("yes");
                obj1=new MenuItem((long)rs.getInt("product_id"),rs.getString("product_name"),
                        (float)rs.getInt("price"),active1, (Date)rs.getDate("date_of_launch"),rs.getString("category_name"),rs.getBoolean("free_delievery"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj1;

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        ExecutorService obj=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().availableProcessors());
        Future<MenuItem> future=obj.submit(()->funcAsync(1));
        Future<MenuItem> future1=obj.submit(()->funcAsync(2));
        MenuItem obj2=future.get();
        MenuItem obj3=future1.get();
//        MenuItem menuitem1=funcAsync(1);
//        MenuItem menuitem2=funcAsync(2);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        obj.shutdown();
//        System.out.println("hey hey");
    }

}
