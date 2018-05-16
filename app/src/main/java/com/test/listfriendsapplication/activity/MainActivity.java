package com.test.listfriendsapplication.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.test.listfriendsapplication.MainContract;
import com.test.listfriendsapplication.R;
import com.test.listfriendsapplication.adapter.RecyclerAdapter;
import com.test.listfriendsapplication.model.User;
import com.test.listfriendsapplication.presenter.ListMainPresenter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new ListMainPresenter(this);
        presenter.createListUser();
        refreshListUser();
    }


    private void refreshListUser() {
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
    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
