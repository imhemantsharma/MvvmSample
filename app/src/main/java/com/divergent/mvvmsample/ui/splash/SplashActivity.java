package com.divergent.mvvmsample.ui.splash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.divergent.mvvmsample.BR;
import com.divergent.mvvmsample.R;
import com.divergent.mvvmsample.ViewModelProviderFactory;
import com.divergent.mvvmsample.databinding.ActivitySplashBinding;
import com.divergent.mvvmsample.ui.base.BaseActivity;
import com.divergent.mvvmsample.ui.base.BaseViewModel;
import com.divergent.mvvmsample.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    private SplashViewModel mSplashViewModel;

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
        mSplashViewModel = ViewModelProviders.of(this, ViewModelProviderFactory.getInstance()).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.decideNextActivity();
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
