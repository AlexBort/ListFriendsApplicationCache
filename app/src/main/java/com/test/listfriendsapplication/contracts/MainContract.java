package com.test.listfriendsapplication.contracts;

import android.content.Context;
import android.view.View;

import com.test.listfriendsapplication.model.User;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by User on 5/16/2018.
 */

public interface MainContract {


    interface MainView {

        void showListUser(List<User> users);

        void showRealmList(RealmResults<User> users);

        void startProgress();

        void stopProgress();

        void showLog(String result);
    }

    interface MainPresenter {
      //  void copyServerListToRealm();

        void presentUserList(View layoutId);

       // RealmResults<User> getRealmList();

        void refreshListUser();

        void getLargeImages(List<User> list);

    }

    interface MainRepository {
        void saveLargeImages(List<User> list);
    }


}
