package com.sharma.mvvmsample.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sharma.mvvmsample.BR;
import com.sharma.mvvmsample.R;
import com.sharma.mvvmsample.ViewModelProviderFactory;
import com.sharma.mvvmsample.data.model.api.UserInfo;
import com.sharma.mvvmsample.databinding.ActivityMainBinding;
import com.sharma.mvvmsample.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    private final List<UserInfo> userList = new ArrayList<>();
    private UserInfoAdapter userInfoAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return new ViewModelProvider(this, ViewModelProviderFactory.getVMInstance()).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);

        // initial setup
        init();

        // fetching user info
        fetchUserInfo();

        // Refresh the list items
        getViewDataBinding().swiperefresh.setOnRefreshListener(() -> {
            userList.clear();
            fetchUserInfo();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void init() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getViewDataBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            getViewDataBinding().recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        getViewDataBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());

        userInfoAdapter = new UserInfoAdapter(userList);
        userInfoAdapter.setHasStableIds(true);
        getViewDataBinding().recyclerView.setAdapter(userInfoAdapter);

        // User info observer call whenever data received from server
        getViewModel().getAllUserLiveData().observe(this, list -> {
            getViewDataBinding().swiperefresh.setRefreshing(false);
            int initialPos = userList.size();
            userList.addAll(list);
            if (userInfoAdapter != null) {
                // if the list size is 0 then notify the all list item otherwise notify the only inserted item
                if (initialPos == 0) {
                    userInfoAdapter.notifyDataSetChanged();
                } else {
                    userInfoAdapter.notifyItemRangeInserted(initialPos, list.size());
                }
            }
        });
    }

    private void fetchUserInfo() {
        getViewDataBinding().swiperefresh.setRefreshing(true);
        getViewModel().getAllUserInfo();
    }
}
