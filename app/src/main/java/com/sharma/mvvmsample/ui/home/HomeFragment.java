package com.sharma.mvvmsample.ui.home;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sharma.mvvmsample.BR;
import com.sharma.mvvmsample.R;
import com.sharma.mvvmsample.ViewModelProviderFactory;
import com.sharma.mvvmsample.data.model.api.UserInfo;
import com.sharma.mvvmsample.databinding.FragmentHomeBinding;
import com.sharma.mvvmsample.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private final List<UserInfo> userList = new ArrayList<>();
    private UserInfoAdapter userInfoAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return new ViewModelProvider(this, ViewModelProviderFactory.getVMInstance()).get(HomeViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().setNavigator(this);

        // initial setup
        setupUI();

        // fetching user info
        fetchUserInfo();

        // Refresh the list items
        getViewDataBinding().swipeRefresh.setOnRefreshListener(() -> {
            userList.clear();
            fetchUserInfo();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setupUI() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getViewDataBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            getViewDataBinding().recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        getViewDataBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());

        userInfoAdapter = new UserInfoAdapter(userList);
        userInfoAdapter.setHasStableIds(true);
        getViewDataBinding().recyclerView.setAdapter(userInfoAdapter);

        // User info observer call whenever data received from server
        getViewModel().getAllUserLiveData().observe(getViewLifecycleOwner(), list -> {
            getViewDataBinding().swipeRefresh.setRefreshing(false);
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
        getViewDataBinding().swipeRefresh.setRefreshing(true);
        getViewModel().getAllUserInfo();
    }
}