package com.zyh.vote.model;



public class VoteDetails extends BaseModel{

    private int id;

    private User user_id;

    private Option option_id;

    private boolean status;

    public int getId() {
        return id;
    }

    private String f_id;

    public VoteDetails(){
        this.f_id = System.currentTimeMillis()+"";
    }

    public VoteDetails(User user_id, Option option_id, boolean status) {
        this.user_id = user_id;
        this.option_id = option_id;
        this.status = status;
        this.f_id = System.currentTimeMillis()+"";
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Option getOption_id() {
        return option_id;
    }

    public void setOption_id(Option option_id) {
        this.option_id = option_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
