package com.zyh.vote.dao.impl;



import com.zyh.vote.dao.ModelDao;
import com.zyh.vote.jdbc.JdbcUtils;
import com.zyh.vote.model.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelDaoIpml<T> implements ModelDao<T> {

    private JdbcUtils mJdbcUtils = new JdbcUtils();

    @Override
    public int insert(T model) {
        String tableName = model.getClass().getSimpleName().toLowerCase();
        Class<?> aClass = model.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        StringBuffer  fieldSql = new StringBuffer();
        fieldSql.append("(");
        StringBuffer  valuesSql = new StringBuffer();
        valuesSql.append("values(");
        List<Object> values = new ArrayList<>();
        for (Field field : declaredFields)
        {
              fieldSql.append(field.getName() + ",");
              valuesSql.append("?,");
            try {
                Method  method = aClass.getDeclaredMethod("get" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1),null);
                method.setAccessible(true);
                Object invoke = method.invoke(model, null);
                values.add(invoke);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fieldSql.deleteCharAt(fieldSql.length() - 1);
        fieldSql.append(")");
        String fieldSql1 = fieldSql.toString();
        valuesSql.deleteCharAt(valuesSql.length()-1);
        valuesSql.append(")");
        String valuesSql1 = valuesSql.toString();
        String sql = "insert into " + tableName + " " + fieldSql1 + " " + valuesSql1;
        System.out.println("insert sql = " + sql);
        System.out.println("insert values = " + values.toString());

        return mJdbcUtils.executeUpdate(sql,values);
    }

    @Override
    public int update(T model) {
        // update table_name set field1=? where field2=?;
        Class<?> aClass = model.getClass();
        String table_name = aClass.getSimpleName().toLowerCase();
        StringBuffer sqlSet = new StringBuffer();
        sqlSet.append("update " + table_name + " set ");
        List<Object> params = new ArrayList<>();
        String sql = "";
        try {
            Method getF_id = aClass.getDeclaredMethod("getF_id", null);
            String f_id = (String) getF_id.invoke(model, null);
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field:declaredFields){
                if (field.getName().equals("f_id") || field.getName().equals("id") || field.getName().equals("create_time")){
                    continue;
                }
                sqlSet.append( " " + field.getName()+"=?,");
                Method  method = aClass.getDeclaredMethod("get" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1),null);
                method.setAccessible(true);
                Object invoke = method.invoke(model, null);
                params.add(invoke);
            }
            sqlSet.deleteCharAt(sqlSet.length() - 1);
            sqlSet.append(" where f_id=" +f_id);
            sql = sqlSet.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sql = " + sql);
        System.out.println("params = " + params);
        return mJdbcUtils.executeUpdate(sql,params);
    }

    @Override
    public int delete(Class<T> clazz,String field, Object param) {

        String table_name = clazz.getSimpleName().toLowerCase();
        String sql = "delete from " + table_name + " where " + field + "=?";
        return mJdbcUtils.executeUpdate(sql,param);
    }

    @Override
    public List<T> findByProperty(Class<T> clazz,String filed, Object params) {
        String table_name = clazz.getSimpleName().toLowerCase();
        String sql = "select * from " + table_name + "  where  " + filed + "=?";
        System.out.println("sql = " + sql + "params = " + params);
        ResultSet resultSet = mJdbcUtils.executeQuery(sql, params);
        return  resultSetToList(clazz,resultSet);
    }


    private List<T> resultSetToList(Class<T> clazz,ResultSet resultSet){

        if (resultSet == null){
            mJdbcUtils.closeConnection();
            return null;
        }
        List<T>  data = new ArrayList<>();

        T obj = null;
        try {
            while (resultSet.next()){
                obj = clazz.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i =1; i < columnCount+1;i++){
                    String columnName = metaData.getColumnName(i);
                    Field mField = clazz.getDeclaredField(columnName);
                    Class<?> type = mField.getType();
                    String methodName ="set" + columnName.substring(0,1).toUpperCase() + columnName.substring(1);
                    Method method = clazz.getDeclaredMethod(methodName,type);
                    method.setAccessible(true);
                    String method1Name = "get"+ type.getSimpleName().substring(0,1).toUpperCase() + type.getSimpleName().substring(1);
                    Method method1 = resultSet.getClass().getMethod(method1Name, String.class);
                    Object invoke = method1.invoke(resultSet, columnName);
                    method.invoke(obj,invoke);
                }
                data.add(obj);
            }
           return  data;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            mJdbcUtils.closeConnection();
        }
        return  null;

    }

    @Override
    public List<T> findAll(Class<T> clazz) {

        String table_name = clazz.getSimpleName();
        String sql = "select * from " + table_name;
        ResultSet resultSet = mJdbcUtils.executeQuery(sql);
        return resultSetToList(clazz,resultSet);
    }

    @Override
    public List<T> findByProperty(Class<T> clazz,Map<String,Object> params) {
        String tableName = clazz.getSimpleName();
        StringBuffer sql = new StringBuffer();
        List<Object> values = new ArrayList<>();
        sql.append("select * from " + tableName + "  where ");
        for (Map.Entry<String,Object> entry : params.entrySet()){
            sql.append(entry.getKey() + "=? and ");
            values.add(entry.getValue());
        }
        sql.append( "1=1");
        String sql1 = sql.toString();
        System.out.println("sql = " + sql1);
        System.out.println("params = " + values.toString());
        ResultSet resultSet = mJdbcUtils.executeQuery(sql1, values);
        return resultSetToList(clazz,resultSet);
    }


    public static void main(String[] args){


        for (int i= 0; i < 10;i++){
            doInsert("yw" + i,"yw123","189" + i);
        }
        System.out.println("执行查询...");
        List<User> admins = doQuery("password", "yw123");
        System.out.println("执行更新...");
        for (User admin :admins){
            doUpdate(admin);
        }
        System.out.println("执行查询全部...");
        doFindAll();
        System.out.println("执行查询一个...");
        Map<String,Object> map = new HashMap<>();
        map.put("username","yw0");
        map.put("password","hy");
        doQuery(map);
        doDelete("password","hy");

    }


    public static void  doDelete(String field,Object value){

        ModelDaoIpml<User> modelDaoIpml = new ModelDaoIpml<>();
         modelDaoIpml.delete(User.class,field,value);

    }

    public static List<User>  doFindAll(){

        ModelDaoIpml<User> modelDaoIpml = new ModelDaoIpml<>();
        List<User> all = modelDaoIpml.findAll(User.class);
        for (User user : all){
            System.out.println(user.toString());
        }
        return all;

    }

    public static List<User>  doQuery(String field,String params){

        ModelDaoIpml<User> modelDaoIpml = new ModelDaoIpml<>();
        List<User> byProperty = modelDaoIpml.findByProperty(User.class,field, params);
        System.out.println("size = " + byProperty.size());
        for (User user : byProperty){
            System.out.println(user.toString());
        }
        return byProperty;

    }
    public static List<User>  doQuery(Map<String,Object> params){

        ModelDaoIpml<User> modelDaoIpml = new ModelDaoIpml<>();
        List<User> byProperty = modelDaoIpml.findByProperty(User.class,params);
        System.out.println("size = " + byProperty.size());
        for (User user : byProperty){
            System.out.println(user.toString());
        }
        return  byProperty;
    }


    public static void doUpdate(User admin){
        ModelDaoIpml<User> modelDaoIpml = new ModelDaoIpml<>();
        admin.setPassword("hy");
        modelDaoIpml.update(admin);
    }

    public static void  doInsert(String name,String password,String phone){

        ModelDaoIpml<User> modelDaoIpml = new ModelDaoIpml<>();
        User mUser = new User(name,password,phone);
        modelDaoIpml.insert(mUser);


    }
}
