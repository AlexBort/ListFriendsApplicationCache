package com.test.listfriendsapplication.model;

import io.realm.RealmObject;

/**
 * Created by User on 5/14/2018.
 */

public class Picture extends RealmObject {

    public Picture() {
    }

    private String large;
    private String medium;
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
