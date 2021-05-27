package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.entity.Calendar;

import java.util.List;

public class CalendarShowWantAsync extends AsyncTask<Calendar, Void, List<Calendar>> {
    private CalendarDao mCalendarDao;
    private String day;

    public CalendarShowWantAsync(CalendarDao calendarDao, String day){
        this.mCalendarDao = calendarDao;
        this.day = day;
    }

    @Override
    protected List<Calendar> doInBackground(Calendar... calendars) {
        return mCalendarDao.getDayEvents(this.day);
    }
}
