package com.sharma.mvvmsample.data.remote;

import com.sharma.mvvmsample.data.model.api.UserInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hemant
 * Date: 10/4/18.
 */

public final class AppApiHelper implements ApiHelper {

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();
    private static ApiHelper apiInterface = null;
    private static AppApiHelper apiHelper;

    public synchronized static AppApiHelper getAppApiInstance() {
        if (apiHelper == null) {
            apiHelper = new AppApiHelper();
        }
        return apiHelper;
    }

    private static ApiHelper getApiInterface() {
        if (apiInterface == null) {
            apiInterface = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Webservices.BASE_URL)
                    .client(okHttpClient)
                    .build().create(ApiHelper.class);
        }

        return apiInterface;
    }

    @Override
    public Call<List<UserInfo>> getUsers() {
        return getApiInterface().getUsers();
    }
}
