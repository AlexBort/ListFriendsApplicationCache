package com.test.listfriendsapplication.activity;

import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.test.listfriendsapplication.contracts.MainContract;
import com.test.listfriendsapplication.R;
import com.test.listfriendsapplication.adapter.RecyclerAdapter;
import com.test.listfriendsapplication.model.User;
import com.test.listfriendsapplication.presenter.ListMainPresenter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private static final String TAG = "MainActivity";

    private RecyclerAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ListMainPresenter presenter;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.title_main);
        }

        presenter = new ListMainPresenter(this, getBaseContext());

        presenter.presentUserList(findViewById(R.id.relative));
        //   presenter.getLargeImages();
        //  Log.e(TAG, "onCreate: size" + String.valueOf(presenter.getRealmList().size()));
        refreshUserList();
    }


    private void refreshUserList() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshListUser();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                }, 1000);
            }
        });
    }

    @Override
    public void showListUser(List<User> users) {
        adapter = new RecyclerAdapter(MainActivity.this, users);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, mLinearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showRealmList(RealmResults<User> users) {
        adapter = new RecyclerAdapter(MainActivity.this, users);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, mLinearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLog(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        Log.e(TAG, "showLog: " + result);
    }
}
