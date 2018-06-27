package com.test.listfriendsapplication.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.test.listfriendsapplication.contracts.MainContract;
import com.test.listfriendsapplication.api.RandomProfile;
import com.test.listfriendsapplication.api.RandomUserAPI;
import com.test.listfriendsapplication.internet.NetworkStateChangeReceiver;
import com.test.listfriendsapplication.model.User;
import com.test.listfriendsapplication.repository.MainRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 5/15/2018.
 */

public class ListMainPresenter implements MainContract.MainPresenter {

    private final Context context;
    private List<User> users;
    private MainContract.MainView mainView;
    private static final String TAG = "ListMainPresenter";
    private MainContract.MainRepository repository;


    public ListMainPresenter(MainContract.MainView mainView, Context context) {
        this.mainView = mainView;
        repository = new MainRepository();
        this.context = context;
    }


    public void copyServerListToRealm() {

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
                        Realm realm = configRealm();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            //  @ParametersAreNonnullByDefault
                            public void execute(Realm realm) {
                                List<User> list = response.getResults();
                                for (int i = 0; i < list.size(); i++) {
                                    if (getRealmList().size() < 20) {
                                        sortListByName(list);
                                        realm.copyToRealm(list.get(i));
                                    }
                                }
                            }
                        });
                    }
                });
    }

    @Override
    public void presentUserList(View layoutResId) {
        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE,
                        false);
                // if (getRealmList() != null) {
                if (!isNetworkAvailable && !isRealmListExisted())
                    Snackbar.make(layoutResId,
                            "Увімкніть, будь ласка, інтернет для закачування даних у додаток", Snackbar.LENGTH_LONG).show();
                else if (!isRealmListExisted()) {
                    copyServerListToRealm();
                    getRealmList();
                } else if (isRealmListExisted()) {
                    List<User> realmList = getRealmList();
                    getLargeImages(realmList);
                }
            }
        }, intentFilter);
    }

    private boolean isRealmListExisted() {
        if (getRealmList() == null) return false;
        else if (getRealmList().size() < 20) return false;
        return true;
    }


    private RealmResults<User> getRealmList() {
        Realm realm = Realm.getDefaultInstance();
        mainView.showRealmList(realm.where(User.class).findAll());
        mainView.stopProgress();
        return realm.where(User.class).findAll();
    }

    public void refreshListUser() {

        if (isOnline(context)) {
            Realm realm = configRealm();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    getRealmList().deleteAllFromRealm();
                    copyServerListToRealm();
                    getRealmList();
                    //   realm.close();
                }
            });
        } else
            Toast.makeText(context, "Щоб оновити список, ввімкніть будь-ласка інтернет", Toast.LENGTH_SHORT).show();


    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void getLargeImages(List<User> list) {
//        Realm realm = Realm.getDefaultInstance();
//        List<User> list = realm.where(User.class).findAll();
        repository.saveLargeImages(list);
    }

    private Realm configRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        return Realm.getDefaultInstance();
    }

    private void sortListByName(List<User> list) {
        NameComparator comparator = new NameComparator();
        Collections.sort(list, comparator);
    }


    class NameComparator implements Comparator<User> {

        @Override
        public int compare(User user1, User user2) {
            return user1.getName().getFirst().compareTo(user2.getName().getFirst());
        }
    }

}
