package com.divergent.mvvmsample.data.remote;

import com.divergent.mvvmsample.data.model.api.BlogWrapper;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by hemant
 * Date: 10/4/18.
 */

public interface ApiHelper {
    //@GET("feed.json")
    @GET("bins/oke2y")
    Call<BlogWrapper> getPopularBlog();
}
