package com.sharma.mvvmsample.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.sharma.mvvmsample.BR;
import com.sharma.mvvmsample.R;
import com.sharma.mvvmsample.ViewModelProviderFactory;
import com.sharma.mvvmsample.databinding.ActivityMainBinding;
import com.sharma.mvvmsample.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

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
    }
}
