package com.sharma.mvvmsample.data;

import com.google.gson.Gson;
import com.sharma.mvvmsample.data.local.db.DBHelper;
import com.sharma.mvvmsample.data.local.prefs.PreferencesHelper;
import com.sharma.mvvmsample.data.remote.ApiHelper;


/**
 * Created by hemant
 * Date: 10/4/18.
 */

public interface DataManager extends PreferencesHelper, ApiHelper, DBHelper {
    Gson getGson();
}
