package com.test.listfriendsapplication.model;


import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by User on 5/14/2018.
 */

public class User extends RealmObject {

    private Name name;
    private String email;
    private Date dateReg;
    private Picture picture;


    public User() {
    }


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }


    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

}
