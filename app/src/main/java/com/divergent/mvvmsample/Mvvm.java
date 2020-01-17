package com.divergent.mvvmsample;

import android.app.Application;

import com.divergent.mvvmsample.data.AppDataManager;

/**
 * Created by Hemant Sharma on 13-01-20.
 * Divergent software labs pvt. ltd
 */
public class Mvvm extends Application {
    private AppDataManager appInstance;
    private static Mvvm instance;

    public static synchronized Mvvm getInstance() {
        if (instance != null) {
            return instance;
        }
        return new Mvvm();
    }

    public AppDataManager getDataManager() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appInstance = AppDataManager.getInstance(this);
    }

}
