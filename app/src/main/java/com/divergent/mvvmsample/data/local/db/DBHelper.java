package com.divergent.mvvmsample.data.local.db;


import com.divergent.mvvmsample.data.model.db.DaoSession;

/**
 * Created by Hemant Sharma on 08-10-19.
 * Divergent software labs pvt. ltd
 */
public interface DBHelper {

    DaoSession getDaoSession();
}
