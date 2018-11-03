package com.zyh.vote.service;

import com.zyh.vote.model.Question;

import java.util.List;

public interface QuestionService {

    int insert(Question question);

    int update(Question question);

    int delete(String field,Object param);

    List<Question> findAll();

    List<Question> findByPorperty(String field,Object params);


}
