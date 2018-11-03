package com.zyh.vote.service;

import com.zyh.vote.dao.ModelDao;
import com.zyh.vote.dao.ModelDaoIpml;

import java.util.List;
import java.util.Map;

public class ModelServiceIpml<T> implements ModelService<T> {

    private ModelDao<T> modelDao = new ModelDaoIpml<>();

    @Override
    public int insert(T model) {
        return modelDao.insert(model);
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
    public List<T> findByProperty(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<T> findByProperty(String filed, Object params) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }
}
