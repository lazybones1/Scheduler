package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.entity.Calendar;

public class CalendarDeleteWantAsync extends AsyncTask<Calendar, Void, Void> {
    private CalendarDao mCalendarDao;
    private String calName;
    private String calDay;

    public CalendarDeleteWantAsync(CalendarDao calendarDao, String calName, String calDay){
        this.mCalendarDao = calendarDao;
        this.calName = calName;
        this.calDay = calDay;
    }

    @Override
    protected Void doInBackground(Calendar... calendars) {
        mCalendarDao.deleteCalendar(this.calName, this.calDay);
        return null;
    }
}
