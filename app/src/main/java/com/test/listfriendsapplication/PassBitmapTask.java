package com.test.listfriendsapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.test.listfriendsapplication.adapter.RecyclerAdapter;

public class PassBitmapTask extends AsyncTask {

    private Intent intent;
    private Activity activityContext;
    String key = " ";

    public PassBitmapTask(Intent intent) {
        this.intent = intent;
    }

    @Override
    protected Bitmap doInBackground(Object[] objects) {

        byte[] byteArray = intent.getByteArrayExtra(key);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return bitmap;
    }

}
