package com.test.listfriendsapplication.model;

import io.realm.RealmObject;

/**
 * Created by User on 5/14/2018.
 */

public class Name extends RealmObject {


    public Name() {
    }

    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }


}
