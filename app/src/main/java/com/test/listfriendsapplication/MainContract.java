package com.test.listfriendsapplication;

import android.content.Context;

import com.test.listfriendsapplication.model.User;

import java.util.List;

/**
 * Created by User on 5/16/2018.
 */

public interface MainContract {


    interface MainView {

        void showListUser(List<User> users);

        void startProgress();

        void stopProgress();
    }

    interface MainPresenter {
        void createListUser();

        void refreshListUser();

    }


}
