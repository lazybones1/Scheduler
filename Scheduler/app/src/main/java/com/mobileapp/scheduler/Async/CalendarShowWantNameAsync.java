package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.entity.Calendar;

import java.util.List;

public class CalendarShowWantNameAsync extends AsyncTask<Void, Void, Calendar> {
    private CalendarDao mCalendarDao;
    private String day;
    private String name;

    public CalendarShowWantNameAsync(CalendarDao calendarDao, String day, String name){
        this.mCalendarDao = calendarDao;
        this.day = day;
        this.name = name;
    }

    @Override
    protected Calendar doInBackground(Void... voids) {
        return mCalendarDao.getCalEvents(this.name, this.day);
    }
}
