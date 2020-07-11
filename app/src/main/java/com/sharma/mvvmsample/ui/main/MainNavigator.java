package com.sharma.mvvmsample.ui.main;

import com.sharma.mvvmsample.data.model.api.Blog;
import com.sharma.mvvmsample.ui.base.BaseNavigator;

import java.util.List;

/**
 * Created by Hemant Sharma on 15-01-20.
 * Divergent software labs pvt. ltd
 */
public interface MainNavigator extends BaseNavigator {
    void prepareRecyclerView(List<Blog> blogList);
}
