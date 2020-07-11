package com.sharma.mvvmsample.data.local.db;

import android.content.Context;

import com.sharma.mvvmsample.data.model.db.DaoMaster;
import com.sharma.mvvmsample.data.model.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Hemant Sharma on 08-10-19.
 * Divergent software labs pvt. ltd
 */
public class AppDBHelper implements DBHelper {
    private static AppDBHelper ourInstance;
    private DaoSession daoSession;

    private AppDBHelper(Context context) {
        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "MVVM");
        Database db = helper.getWritableDb();

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");
        daoSession = new DaoMaster(db).newSession();
    }

    public static AppDBHelper getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppDBHelper(context);
        }

        return ourInstance;
    }

    @Override
    public DaoSession getDaoSession() {
        return daoSession;
    }

}
