package com.mobileapp.scheduler.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calendar")
public class Calendar {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int uid;

    @ColumnInfo(name = "calendar_name")
    public String calendarName;

    @ColumnInfo(name = "start_day")
    public String startDay;

    @ColumnInfo(name = "start_time")
    public String startTime;

    @ColumnInfo(name = "end_time")
    public String endTime;

    @ColumnInfo(name = "calendar_memo")
    public String calendar_memo;

    public int getUid() {
        return uid;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCalendar_memo() {
        return calendar_memo;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setCalendar_memo(String calendar_memo) {
        this.calendar_memo = calendar_memo;
    }
}

