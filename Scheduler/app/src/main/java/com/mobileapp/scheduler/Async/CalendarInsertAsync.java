package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;
import android.util.Log;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.entity.Calendar;

public class CalendarInsertAsync extends AsyncTask<Calendar, Void, Void> {
    private CalendarDao mCalendarDao;

    public CalendarInsertAsync(CalendarDao calendarDao){
        this.mCalendarDao = calendarDao;
    }

    @Override
    protected Void doInBackground(Calendar... calendars) {
        mCalendarDao.insert(calendars[0]);
        return null;
    }
}
