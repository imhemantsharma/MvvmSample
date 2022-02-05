package com.sharma.mvvmsample.util;

/**
 * Created by hemant
 * Date: 10/4/18.
 */

public final class AppConstants {

    //Pagination Limit
    public static final int LIMIT = 20;

    public static final int REQUEST_TAKE_PHOTO = 51;
    public static final int REQUEST_GALLERY = 52;
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 53;

    // key for run time permissions
    public final static int REQUEST_CHECK_SETTINGS_GPS = 96;
    public static final int REQUEST_MULTIPLE_CAMERA_PERMISSIONS = 98;
    public final static int REQUEST_MULTIPLE_PERMISSIONS = 100;
    public final static int MY_PERMISSIONS_REQUEST_LOCATION = 101;
    public final static int MY_PERMISSIONS_REQUEST_CAMERA = 102;

    public static String CONTENT_TYPE_TEXT = "text/plain";
    public static String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    private AppConstants() {
        // This class is not publicly instantiable
    }
}
