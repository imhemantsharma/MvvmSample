package com.sharma.mvvmsample.ui.splash;

import com.sharma.mvvmsample.data.DataManager;
import com.sharma.mvvmsample.ui.base.BaseViewModel;

/**
 * Created by Hemant Sharma on 15-01-20.
 */
public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
    }

    void decideNextActivity() {
        getNavigator().openLoginActivity();
    }
}
