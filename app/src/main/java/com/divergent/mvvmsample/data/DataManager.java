package com.divergent.mvvmsample.data;

import com.divergent.mvvmsample.data.local.db.DBHelper;
import com.divergent.mvvmsample.data.remote.ApiHelper;
import com.divergent.mvvmsample.data.local.prefs.PreferencesHelper;
import com.google.gson.Gson;


/**
 * Created by hemant
 * Date: 10/4/18.
 */

public interface DataManager extends PreferencesHelper, ApiHelper, DBHelper {
    Gson getGson();
}
