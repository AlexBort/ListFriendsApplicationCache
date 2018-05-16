package com.test.listfriendsapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.test.listfriendsapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        if (toolbar != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Профиль");

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
