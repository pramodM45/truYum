package com.cognizant.truYum.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionHandler {
    private static Connection con;
//    private static Properties props=new Properties();
    public static Connection getConnection() throws ClassNotFoundException,SQLException {
//        try {
////            FileInputStream fis = new FileInputStream("connection.properties");
////            props.load(fis);
////            Class.forName(props.getProperty("driver"));
//
//            // create the connection now
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/truyum","pramodm","pramodm45");
//        }
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/truyum","pramodm","pramodm45");

        return con;
    }

}
