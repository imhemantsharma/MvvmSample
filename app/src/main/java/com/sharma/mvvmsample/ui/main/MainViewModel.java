package com.sharma.mvvmsample.ui.main;


import com.sharma.mvvmsample.data.DataManager;
import com.sharma.mvvmsample.ui.base.BaseNavigator;
import com.sharma.mvvmsample.ui.base.BaseViewModel;

public class MainViewModel extends BaseViewModel<BaseNavigator> {

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
    }
}
