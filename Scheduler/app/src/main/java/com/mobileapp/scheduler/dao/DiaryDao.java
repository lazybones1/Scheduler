package com.mobileapp.scheduler.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;
@Dao
public interface DiaryDao {
    @Query("SELECT * FROM Diary")
    List<Diary> getAll();

    @Query("SELECT * FROM Diary Where diary_day = :sday")
    List<Diary> getDayEvents(String sday);

    @Insert
    void insert(Diary diary);
}
