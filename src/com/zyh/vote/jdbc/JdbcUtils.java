package com.zyh.vote.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcUtils {


    private static String url;

    private static String USERNAME;

    private static String PASSWORD;

    private static String DRIVER_CALSS;


    private Connection mConnection;

    static {
        url = ResourceBundle.getBundle("jdbc").getString("url");
        DRIVER_CALSS = ResourceBundle.getBundle("jdbc").getString("driverclass");
        PASSWORD = ResourceBundle.getBundle("jdbc").getString("password");
        USERNAME = ResourceBundle.getBundle("jdbc").getString("username");
        try {
            Class.forName(DRIVER_CALSS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }





    public Connection getConnection() {
        try {
            mConnection = DriverManager.getConnection(url,USERNAME,PASSWORD);
            return mConnection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public boolean closeConnection()
    {
         if (mConnection != null)
         {
             try {
                 mConnection.close();
                 return  true;
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
         return false;
    }
}
