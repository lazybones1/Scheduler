package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.entity.Calendar;

import java.util.List;

public class CalendarShowAsync extends AsyncTask<Calendar, Void, List<Calendar>> {
    private CalendarDao mCalendarDao;

    public CalendarShowAsync(CalendarDao calendarDao){
        this.mCalendarDao = calendarDao;
    }

    @Override
    protected List<Calendar> doInBackground(Calendar... calendars) {
        return mCalendarDao.getAll();
    }
}
