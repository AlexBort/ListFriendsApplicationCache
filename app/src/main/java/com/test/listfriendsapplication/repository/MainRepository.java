package com.test.listfriendsapplication.repository;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.test.listfriendsapplication.contracts.MainContract;
import com.test.listfriendsapplication.model.User;

import java.io.IOException;
import java.util.List;

public class MainRepository implements MainContract.MainRepository {

    private static final String TAG = "MainRepository";

    @Override
    public void saveLargeImages(List<User> list) {

        for (int i = 0; i < list.size(); i++) {

            Picasso.get().load(list.get(i).getPicture().getLarge()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });

        }
    }
}
