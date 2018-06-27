package com.test.listfriendsapplication.api;

import com.test.listfriendsapplication.model.Inform;
import com.test.listfriendsapplication.model.User;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by User on 5/14/2018.
 */

public class RandomProfile {

    private List<User> results;
    private Inform info;
    private RealmResults<User> realmResults;

    public RandomProfile() {
    }

    public RealmResults<User> getRealmResults() {
        return realmResults;
    }

    public void setRealmResults(RealmResults<User> realmResults) {
        this.realmResults = realmResults;
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
