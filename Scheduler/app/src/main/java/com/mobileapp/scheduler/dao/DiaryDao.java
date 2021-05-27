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

    @Query("SELECT * FROM Diary WHERE diary_name = :dName AND diary_day = :dday")
    Diary getDiaryEvents(String dName, String dday);

    @Query("DELETE FROM Diary Where diary_name = :dName AND diary_day = :dday")
    void deleteDiaryendar(String dName, String dday);

    @Insert
    void insert(Diary diary);
}
