package com.sharma.mvvmsample.ui.splash;

import com.sharma.mvvmsample.data.DataManager;
import com.sharma.mvvmsample.ui.base.BaseViewModel;

/**
 * Created by Hemant Sharma on 15-01-20.
 * Divergent software labs pvt. ltd
 */
public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
    }

    void decideNextActivity() {
        getNavigator().openLoginActivity();
    }
}
