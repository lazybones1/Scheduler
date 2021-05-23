package com.mobileapp.scheduler.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mobileapp.scheduler.entity.Calendar;

import java.util.List;

@Dao
public interface CalendarDao {
    @Query("SELECT * FROM Calendar")
    List<Calendar> getAll();

    @Insert
    void insert(Calendar calendar);
}
