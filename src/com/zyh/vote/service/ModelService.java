package com.zyh.vote.service;


import java.util.List;
import java.util.Map;

public interface ModelService<T> {

    int insert(T model);

    int update(T model);

    int delete(Class<T> clazz,String field,Object param);

    List<T> findByProperty(Class<T> clazz,Map<String,Object> params);

    List<T> findByProperty(Class<T> clazz,String filed,Object params);

    List<T> findAll(Class<T> clazz);
}
