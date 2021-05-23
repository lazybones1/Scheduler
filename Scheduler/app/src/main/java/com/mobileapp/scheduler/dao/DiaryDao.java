package com.mobileapp.scheduler.dao;

import androidx.room.Insert;
import androidx.room.Query;

import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Diary;

import java.util.List;

public interface DiaryDao {
    @Query("SELECT * FROM Diary")
    List<Diary> getAll();

    @Insert
    void insert(Diary diary);
}
