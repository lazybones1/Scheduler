package com.mobileapp.scheduler.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

@Dao
public interface MemoDao {
    @Query("SELECT * FROM Memo")
    List<Memo> getAll();

    @Query("SELECT * FROM Memo Where memoDay = :sday")
    List<Memo> getDayEvents(String sday);

    @Insert
    void insert(Memo memo);
}
