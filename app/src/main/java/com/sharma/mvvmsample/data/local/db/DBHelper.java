package com.sharma.mvvmsample.data.local.db;


import com.sharma.mvvmsample.data.model.db.DaoSession;

/**
 * Created by Hemant Sharma on 08-10-19.
 */
public interface DBHelper {

    DaoSession getDaoSession();
}
