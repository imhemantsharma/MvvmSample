package com.sharma.mvvmsample;

import android.app.Application;

import com.sharma.mvvmsample.data.AppDataManager;

/**
 * Created by Hemant Sharma on 01-01-19.
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
