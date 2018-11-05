package com.zyh.vote.model;

import java.sql.Timestamp;

public class Option extends BaseModel{

    private int id;

    private String option_name;

    private Question question_id;

    private Timestamp create_time;


    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public Option(){
        this.f_id = System.currentTimeMillis()+"";
        this.create_time = new Timestamp(System.currentTimeMillis());
    }

    public Option(String option_name, Question question_id) {
        this.option_name = option_name;
        this.question_id = question_id;
        this.f_id = System.currentTimeMillis()+"";
        this.create_time = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public Question getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Question question_id) {
        this.question_id = question_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
