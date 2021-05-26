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

    public int getUid() {
        return uid;
    }

    public String getMemoName() {
        return memoName;
    }

    public String getMemoContent() {
        return memoContent;
    }
}

