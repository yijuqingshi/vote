package com.zyh.vote.model;


import java.util.List;

public class VoteForm extends BaseModel{

    private int id;

    private int question_number;

    private Admin admin_id;

    private List<Question> questions;

    private String f_id;

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public VoteForm(){
        this.f_id = System.currentTimeMillis()+"";
    }

    public VoteForm(int question_number, Admin admin_id, List<Question> questions) {
        this.question_number = question_number;
        this.admin_id = admin_id;
        this.questions = questions;
        this.f_id = System.currentTimeMillis()+"";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public Admin getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Admin admin_id) {
        this.admin_id = admin_id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
