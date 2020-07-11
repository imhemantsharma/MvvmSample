package com.sharma.mvvmsample.data.local.prefs;


import android.app.Activity;

/**
 * Created by hemant
 * Date: 10/4/18.
 */

public interface PreferencesHelper {

    boolean isLoggedIn();

    void setLoggedIn(boolean isLoggedIn);

    void logout(Activity activity);
}
