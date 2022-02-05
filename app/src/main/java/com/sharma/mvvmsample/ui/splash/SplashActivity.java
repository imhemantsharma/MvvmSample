package com.sharma.mvvmsample.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sharma.mvvmsample.BR;
import com.sharma.mvvmsample.R;
import com.sharma.mvvmsample.ViewModelProviderFactory;
import com.sharma.mvvmsample.databinding.ActivitySplashBinding;
import com.sharma.mvvmsample.ui.base.BaseActivity;
import com.sharma.mvvmsample.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return new ViewModelProvider(this, ViewModelProviderFactory.getVMInstance()).get(SplashViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        getViewModel().decideNextActivity();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }
}
