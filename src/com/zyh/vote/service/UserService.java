package com.zyh.vote.service;

import com.zyh.vote.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    int insert(User user);

    int update(User user);

    int delete(String field,Object param);

    List<User> findByProperty(Map<String,Object> params);

    List<User> findByProperty(String filed,Object params);

    List<User> findAll();


}
