package com.test.listfriendsapplication.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by User on 5/14/2018.
 */

public interface RandomUserAPI {

    String SERVICE_ENDPOINT = "https://randomuser.me/";

    @GET("api/")
    Observable<RandomProfile> getRandomUsers(@Query("results") Integer results);

    class Factory {
        public static RandomUserAPI create() {
            return ServiceFactory.createRetrofitService(RandomUserAPI.class, SERVICE_ENDPOINT);
        }
    }
}
