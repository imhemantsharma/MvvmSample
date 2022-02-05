package com.sharma.mvvmsample.data.remote;

import com.sharma.mvvmsample.data.model.api.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by hemant
 * Date: 10/4/18.
 */

public interface ApiHelper {

    @GET("users")
    Call<List<UserInfo>> getUsers();
}
