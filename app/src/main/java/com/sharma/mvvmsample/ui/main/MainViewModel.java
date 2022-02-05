package com.sharma.mvvmsample.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.sharma.mvvmsample.data.DataManager;
import com.sharma.mvvmsample.data.model.api.UserInfo;
import com.sharma.mvvmsample.ui.base.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel<MainNavigator> {
    private final MutableLiveData<List<UserInfo>> mutableLiveData = new MutableLiveData<>();

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
    }

    // user info observer
    public MutableLiveData<List<UserInfo>> getAllUserLiveData() {
        return mutableLiveData;
    }

    // Fetching user info
    void getAllUserInfo() {
        getDataManager().getUsers().enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserInfo>> call, @NonNull Response<List<UserInfo>> response) {
                List<UserInfo> users = response.body();
                if (users != null) {
                    mutableLiveData.setValue(users);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserInfo>> call, @NonNull Throwable t) {
                getNavigator().handleApiFailure(t);
            }
        });
    }
}
