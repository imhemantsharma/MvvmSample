package com.sharma.mvvmsample;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sharma.mvvmsample.data.DataManager;
import com.sharma.mvvmsample.ui.home.HomeViewModel;
import com.sharma.mvvmsample.ui.main.MainViewModel;

/**
 * Created by Hemant Sharma on 01-01-19.
 */
public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private static ViewModelProviderFactory instance;
    private final DataManager dataManager;

    private ViewModelProviderFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public static ViewModelProviderFactory getVMInstance() {
        if (instance == null) {
            instance = new ViewModelProviderFactory(Mvvm.getInstance().getDataManager());
        }
        return instance;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(dataManager);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            //noinspection unchecked
            return (T) new HomeViewModel(dataManager);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}