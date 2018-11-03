package com.zyh.vote.service;


import java.util.List;
import java.util.Map;

public interface ModelService<T> {

    int insert(T model);

    int update(T model);

    int delete(String field,Object param);

    List<T> findByProperty(Map<String,Object> params);

    List<T> findByProperty(String filed,Object params);

    List<T> findAll();
}
