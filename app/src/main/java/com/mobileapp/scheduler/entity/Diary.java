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
}

