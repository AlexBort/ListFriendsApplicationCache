package com.test.listfriendsapplication.api;

import com.test.listfriendsapplication.model.Inform;
import com.test.listfriendsapplication.model.User;

import java.util.List;

/**
 * Created by User on 5/14/2018.
 */

public class RandomProfile {

    private List<User> results;
    private Inform info;

    public RandomProfile() {
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Inform getInfo() {
        return info;
    }

    public void setInfo(Inform info) {
        this.info = info;
    }
}
