package com.mobileapp.scheduler.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary")
public class Diary {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int uid;

    @ColumnInfo(name = "diary_name")
    public String diaryName;

    @ColumnInfo(name = "diary_weather")
    public String diary_weather;

    @ColumnInfo(name = "diary_memo")
    public String diary_memo;

    @ColumnInfo(name = "diary_day")
    public String diary_day;

    public int getUid() {
        return uid;
    }

    public String getDiaryName() {
        return diaryName;
    }

    public String getDiary_memo() {
        return diary_memo;
    }

    public String getDiary_weather() {
        return diary_weather;
    }

    public String getDiary_day() {
        return diary_day;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setDiaryName(String diaryName) {
        this.diaryName = diaryName;
    }

    public void setDiary_weather(String diary_weather) {
        this.diary_weather = diary_weather;
    }

    public void setDiary_memo(String diary_memo) {
        this.diary_memo = diary_memo;
    }

    public void setDiary_day(String diary_day) {
        this.diary_day = diary_day;
    }
}

