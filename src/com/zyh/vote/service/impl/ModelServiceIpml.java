package com.zyh.vote.service.impl;

import com.zyh.vote.dao.ModelDao;
import com.zyh.vote.dao.impl.ModelDaoIpml;
import com.zyh.vote.service.ModelService;

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
        return modelDao.update(model);
    }

    @Override
    public int delete(Class<T> clazz,String field, Object param) {
       return modelDao.delete(clazz,field,param);
    }

    @Override
    public List<T> findByProperty(Class<T> clazz,Map<String, Object> params) {
        return modelDao.findByProperty(clazz,params);
    }

    @Override
    public List<T> findByProperty(Class<T> clazz,String filed, Object params) {
        return modelDao.findByProperty(clazz,filed,params);
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        return modelDao.findAll(clazz);
    }
}
