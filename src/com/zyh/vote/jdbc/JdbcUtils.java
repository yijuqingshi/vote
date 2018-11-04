package com.zyh.vote.jdbc;




import java.sql.*;
import java.util.List;
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

    public int executeUpdate(String sql , List<Object> params)
    {
        Connection connection = getConnection();
        if (connection != null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                for (int i = 0;  i < params.size();i++){
                    preparedStatement.setObject(i+1,params.get(i));
                }
                int result = preparedStatement.executeUpdate();
                return  result;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                closeConnection();
            }
        }
        return -1;
    }


    public int executeUpdate(String sql , Object params)
    {
        Connection connection = getConnection();
        if (connection != null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1,params);
                int result = preparedStatement.executeUpdate();
                return  result;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                closeConnection();
            }
        }
        return -1;
    }




    public ResultSet executeQuery(String sql ,List<Object> params){
        Connection connection = getConnection();

        if (null != connection && null != params) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i+1, params.get(i));
                }
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
         return  null;
    }

    public ResultSet executeQuery(String sql){
        Connection connection = getConnection();
        if (null != connection) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }


    public ResultSet executeQuery(String sql ,Object params){
        Connection connection = getConnection();
        if (null != connection) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, params);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  null;
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


    public static void main(String[] args){

        JdbcUtils mJdbcUtils = new JdbcUtils();
        Connection connection = mJdbcUtils.getConnection();
        mJdbcUtils.closeConnection();
    }
}
