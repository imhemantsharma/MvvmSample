package com.sharma.mvvmsample.ui.main;

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
import com.sharma.mvvmsample.data.model.api.Blog;
import com.sharma.mvvmsample.databinding.ActivityMainBinding;
import com.sharma.mvvmsample.ui.base.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    public static Intent newIntent(Context context){
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
        return new ViewModelProvider(this, ViewModelProviderFactory.getInstance()).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPopularBlog();
        getViewDataBinding().swiperefresh.setOnRefreshListener(this::getPopularBlog);
    }

    public void getPopularBlog() {
        getViewDataBinding().swiperefresh.setRefreshing(true);
        getViewModel().getAllBlog().observe(this, blogList -> {
            getViewDataBinding().swiperefresh.setRefreshing(false);
            prepareRecyclerView(blogList);
        });
    }


    private void prepareRecyclerView(List<Blog> blogList) {
        BlogAdapter mBlogAdapter = new BlogAdapter(blogList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getViewDataBinding().blogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            getViewDataBinding().blogRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        getViewDataBinding().blogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getViewDataBinding().blogRecyclerView.setAdapter(mBlogAdapter);
        mBlogAdapter.notifyDataSetChanged();
    }

}
