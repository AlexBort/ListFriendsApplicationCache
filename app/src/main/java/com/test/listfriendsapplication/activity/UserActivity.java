package com.test.listfriendsapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.test.listfriendsapplication.PassBitmapTask;
import com.test.listfriendsapplication.R;
import com.test.listfriendsapplication.adapter.RecyclerAdapter;
import com.test.listfriendsapplication.contracts.UserContract;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity /*implements UserContract.ImageInCache*/ {

    private static final String TAG = "UserActivity";
    @BindView(R.id.big_ava)
    ImageView imageView;
    @BindView(R.id.first_name)
    TextView nFirstView;
    @BindView(R.id.second_name)
    TextView nLastView;
    @BindView(R.id.mail)
    TextView mailView;
    private Intent intent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        // getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //   getWindow().setBackgroundDrawableResource(R.color.transparent);
        // toolbar.setVisibility(View.INVISIBLE);



        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        intent = getIntent();

        nFirstView.setText(getIntentText("first"));
        nLastView.setText(getIntentText("last"));
        mailView.setText(getIntentText("mail"));

        Picasso.get().load(getIntentText("ava")).into(imageView);


    }


    private String getIntentText(String key) {
        return intent.getStringExtra(key);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
