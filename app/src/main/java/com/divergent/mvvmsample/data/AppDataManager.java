package com.divergent.mvvmsample.data;

import android.app.Activity;
import android.content.Context;

import com.divergent.mvvmsample.data.local.db.AppDBHelper;
import com.divergent.mvvmsample.data.model.db.DaoSession;
import com.divergent.mvvmsample.data.remote.ApiHelper;
import com.divergent.mvvmsample.data.remote.AppApiHelper;
import com.divergent.mvvmsample.data.local.prefs.AppPreferencesHelper;
import com.divergent.mvvmsample.data.local.prefs.PreferencesHelper;
import com.divergent.mvvmsample.data.model.api.BlogWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;


/**
 * Created by hemant
 * Date: 10/4/18.
 */


public final class AppDataManager implements DataManager {

    private static AppDataManager instance;
    private final Gson mGson;
    private final ApiHelper mApiHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final AppDBHelper appDBHelper;


    private AppDataManager(Context context) {
        mPreferencesHelper = new AppPreferencesHelper(context);
        mApiHelper = AppApiHelper.getAppApiInstance();
        appDBHelper = AppDBHelper.getInstance(context);
        mGson = new GsonBuilder().create();
    }

    public synchronized static AppDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new AppDataManager(context);
        }
        return instance;
    }

    @Override
    public boolean isLoggedIn() {
        return mPreferencesHelper.isLoggedIn();
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        mPreferencesHelper.setLoggedIn(isLoggedIn);
    }

    @Override
    public void logout(Activity activity) {
        mPreferencesHelper.logout(activity);
    }

    @Override
    public Gson getGson() {
        return mGson;
    }

    @Override
    public DaoSession getDaoSession() {
        return appDBHelper.getDaoSession();
    }

    @Override
    public Call<BlogWrapper> getPopularBlog() {
        return mApiHelper.getPopularBlog();
    }
}
