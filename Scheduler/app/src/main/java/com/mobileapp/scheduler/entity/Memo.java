package com.mobileapp.scheduler.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "memo")
public class Memo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int uid;

    @ColumnInfo(name = "memo_name")
    public String memoName;

    @ColumnInfo(name = "memoContent")
    public String memoContent;

    @ColumnInfo(name = "memoDay")
    public String memoDay;

    public int getUid() {
        return uid;
    }

    public String getMemoName() {
        return memoName;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public String getMemoDay() {
        return memoDay;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setMemoName(String memoName) {
        this.memoName = memoName;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public void setMemoDay(String memoDay) {
        this.memoDay = memoDay;
    }
}

