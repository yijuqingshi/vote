package com.zyh.vote.model;

import com.zyh.vote.enmu.QuestionType;

import java.sql.Timestamp;
import java.util.List;

public class Question {

    private int id;

    private String question_name;

    private QuestionType type;

    private int option_number;

    private VoteForm vote_form_id;

    private Timestamp create_time;

    private String f_id;

    private List<Option> options;


    public Question(){
        this.f_id = System.currentTimeMillis()+"";
        this.create_time = new Timestamp(System.currentTimeMillis());
    }

    public Question(String question_name, QuestionType type, int option_number, VoteForm vote_form_id, List<Option> options) {
        this.question_name = question_name;
        this.type = type;
        this.option_number = option_number;
        this.vote_form_id = vote_form_id;
        this.options = options;
        this.f_id = System.currentTimeMillis()+"";
        this.create_time = new Timestamp(System.currentTimeMillis());
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }


    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public int getOption_number() {
        return option_number;
    }

    public void setOption_number(int option_number) {
        this.option_number = option_number;
    }

    public VoteForm getVote_form_id() {
        return vote_form_id;
    }

    public void setVote_form_id(VoteForm vote_form_id) {
        this.vote_form_id = vote_form_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
