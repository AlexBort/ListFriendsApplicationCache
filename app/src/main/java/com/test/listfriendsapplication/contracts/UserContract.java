package com.test.listfriendsapplication.contracts;

import android.graphics.Bitmap;

public interface UserContract {


    interface ImageInCache {
        void passBitmap(Bitmap bitmap);
    }

}
