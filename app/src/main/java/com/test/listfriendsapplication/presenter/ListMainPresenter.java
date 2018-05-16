package com.test.listfriendsapplication.presenter;

import android.util.Log;

import com.test.listfriendsapplication.MainContract;
import com.test.listfriendsapplication.api.RandomProfile;
import com.test.listfriendsapplication.api.RandomUserAPI;
import com.test.listfriendsapplication.model.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 5/15/2018.
 */

public class ListMainPresenter implements MainContract.MainPresenter {


    List<User> users;
    MainContract.MainView mainView;

    public ListMainPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }


    private static final String TAG = "";

    public void createListUser() {
        mainView.startProgress();
        final RandomUserAPI randomUserAPI = RandomUserAPI.Factory.create();

        randomUserAPI.getRandomUsers(20)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RandomProfile>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: " + "completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(RandomProfile response) {
                        Log.i(TAG, "onNext: " + response.getResults().toString());

                        users = response.getResults();
                        NameComparator comparator = new NameComparator();
                        Collections.sort(users, comparator);
                        mainView.stopProgress();
                        mainView.showListUser(users);
                    }
                });
    }

    public void refreshListUser() {
        createListUser();
    }


    class NameComparator implements Comparator<User> {

        @Override
        public int compare(User user1, User user2) {
            return user1.getName().getFirst().compareTo(user2.getName().getFirst());
        }
    }

}
