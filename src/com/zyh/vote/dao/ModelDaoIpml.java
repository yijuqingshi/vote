package com.zyh.vote.dao;



import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelDaoIpml<T> implements ModelDao<T> {


    @Override
    public int insert(T model) {
        String tableName = model.getClass().toString().toLowerCase();
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

        return 0;
    }

    @Override
    public int update(T model) {
        return 0;
    }

    @Override
    public int delete(String field, Object param) {
        return 0;
    }

    @Override
    public List<T> findByProperty(String filed, Object params) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findByProperty(Map params) {
        return null;
    }
}
