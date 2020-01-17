package com.divergent.mvvmsample.ui.main;

import com.divergent.mvvmsample.data.model.api.Blog;

import java.util.List;

/**
 * Created by Hemant Sharma on 15-01-20.
 * Divergent software labs pvt. ltd
 */
public interface MainNavigator {
    void prepareRecyclerView(List<Blog> blogList);
}
