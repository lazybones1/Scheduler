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

    @ColumnInfo(name = "start_dat")
    public String startDay;

    @ColumnInfo(name = "start_time")
    public String startTime;

    @ColumnInfo(name = "end_dat")
    public String endDay;

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

    public String getEndDay() {
        return endDay;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCalendar_memo() {
        return calendar_memo;
    }
}

