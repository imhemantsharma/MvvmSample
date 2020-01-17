package com.divergent.mvvmsample.data.local.prefs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.divergent.mvvmsample.ui.main.MainActivity;

/**
 * Created by hemant
 * Date: 10/4/18.
 */

public final class AppPreferencesHelper implements PreferencesHelper {

    private static final String APP_PREFERENCE = "AppPreference";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    // prefs object
    private final SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context) {
        mPrefs = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isLoggedIn() {
        return mPrefs.getBoolean(PREF_KEY_USER_LOGGED_IN_MODE, false);
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_LOGGED_IN_MODE, isLoggedIn).apply();
    }

    @Override
    public void logout(Activity activity) {
        setLoggedIn(false);
        Intent intent = MainActivity.newIntent(activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

}
