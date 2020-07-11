package com.sharma.mvvmsample.util;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Hemant Sharma on 01-01-19.
 */
public final class CalenderUtils {

    public static final String TIMESTAMP_FORMAT = "dd/MM/yyyy";
    public static final String SERVER_TIMESTAMP_FORMAT = "yyyy-MM-dd";
    public static final String DB_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";

    private CalenderUtils() {
        // This class is not publicly instantiable
    }

    public static String getTimestamp(String format) {
        return new SimpleDateFormat(format, Locale.US).format(new Date());
    }

    public static String getTimestamp() {
        return String.valueOf(new Date().getTime());
    }

    public static String format12HourTime(String time, @NonNull String pFormat, @NonNull String dFormat) {
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat(pFormat, Locale.US);
            SimpleDateFormat displayFormat = new SimpleDateFormat(dFormat, Locale.US);
            Date dTime = parseFormat.parse(time);
            assert dTime != null;
            return displayFormat.format(dTime);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String formatDate(String date, @NonNull String pFormat, @NonNull String dFormat) {
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat(pFormat, Locale.US);
            SimpleDateFormat displayFormat = new SimpleDateFormat(dFormat, Locale.US);
            Date dTime = parseFormat.parse(date);
            assert dTime != null;
            return displayFormat.format(dTime);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Date getDateFormat(String date, @NonNull String format) {
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat(format, Locale.US);

            return parseFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static String getDateTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(new Date());
    }

    public static Long getTimerDifference(String timer, String myTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(CalenderUtils.DB_TIMESTAMP_FORMAT, Locale.US);

        try {
            Date startDate = formatter.parse(timer);

            Date endDate = formatter.parse(myTime);

            assert startDate != null;
            assert endDate != null;
            long diffInMilliSec = endDate.getTime() - startDate.getTime();

            return (diffInMilliSec / (1000 * 60)) % 60;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static String getCurrentDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return (day + "/" + month + "/" + year);

    }

    @NonNull
    public static String getCurrentTime() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return (hour + ":" + minute);
    }
}
