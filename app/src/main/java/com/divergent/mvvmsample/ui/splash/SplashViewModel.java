package com.divergent.mvvmsample.ui.splash;

import com.divergent.mvvmsample.data.DataManager;
import com.divergent.mvvmsample.ui.base.BaseViewModel;

/**
 * Created by Hemant Sharma on 15-01-20.
 * Divergent software labs pvt. ltd
 */
public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void decideNextActivity() {
        getNavigator().openLoginActivity();
    }
}
