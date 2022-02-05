package com.sharma.mvvmsample.ui.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import okhttp3.ResponseBody;

/**
 * Created by Hemant Sharma on 23-02-20.
 */
public interface BaseNavigator {
    Context getContext();

    boolean hasPermission(String permission);

    void hideKeyboard();

    boolean isNetworkConnected();

    void openActivityOnTokenExpire();

    void requestPermissionsSafely(String[] permissions, int requestCode);

    void hideLoading();

    void showLoading();

    void replaceFragment(@NonNull Fragment fragmentHolder, int layoutId);

    void addFragment(@NonNull Fragment fragment, int layoutId, boolean addToBackStack);

    void showToast(String msg);

    void showToast(@StringRes int msg);

    void handleApiFailure(@NonNull Throwable t);

    void handleApiError(ResponseBody response);
}
