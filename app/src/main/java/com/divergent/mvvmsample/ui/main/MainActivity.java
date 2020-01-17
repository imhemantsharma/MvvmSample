package com.divergent.mvvmsample.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.divergent.mvvmsample.BR;
import com.divergent.mvvmsample.R;
import com.divergent.mvvmsample.ViewModelProviderFactory;
import com.divergent.mvvmsample.data.model.api.Blog;
import com.divergent.mvvmsample.databinding.ActivityMainBinding;
import com.divergent.mvvmsample.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private MainViewModel mainViewModel;

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
        mainViewModel = ViewModelProviders.of(this, ViewModelProviderFactory.getInstance()).get(MainViewModel.class);
        return mainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPopularBlog();
        // lambda expression
        getViewDataBinding().swiperefresh.setOnRefreshListener(this::getPopularBlog);
    }

    public void getPopularBlog() {
        getViewDataBinding().swiperefresh.setRefreshing(true);
        mainViewModel.getAllBlog().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(@Nullable List<Blog> blogList) {
                getViewDataBinding().swiperefresh.setRefreshing(false);
                prepareRecyclerView(blogList);
            }
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
