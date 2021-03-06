package com.sharma.mvvmsample.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.sharma.mvvmsample.data.DataManager;
import com.sharma.mvvmsample.data.model.api.Blog;
import com.sharma.mvvmsample.data.model.api.BlogWrapper;
import com.sharma.mvvmsample.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel<MainNavigator> {
    private ArrayList<Blog> movies = new ArrayList<>();
    private MutableLiveData<List<Blog>> mutableLiveData = new MutableLiveData<>();

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
    }

    MutableLiveData<List<Blog>> getAllBlog() {

        getDataManager().getPopularBlog().enqueue(new Callback<BlogWrapper>() {
            @Override
            public void onResponse(@NonNull Call<BlogWrapper> call, @NonNull Response<BlogWrapper> response) {
                BlogWrapper mBlogWrapper = response.body();
                if (mBlogWrapper != null && mBlogWrapper.getBlog() != null) {
                    movies = (ArrayList<Blog>) mBlogWrapper.getBlog();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<BlogWrapper> call, @NonNull Throwable t) {
                getNavigator().handleApiFailure(t);
            }
        });

        return mutableLiveData;
    }
}
