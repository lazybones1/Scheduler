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

    @Query("SELECT * FROM Calendar Where start_day = :sday")
    List<Calendar> getDayEvents(String sday);

    @Query("SELECT * FROM Calendar WHERE calendar_name = :cName AND start_day = :cday")
    Calendar getCalEvents(String cName, String cday);

    @Query("DELETE FROM Calendar Where calendar_name = :calName AND start_day = :cday")
    void deleteCalendar(String calName, String cday);

    @Insert
    void insert(Calendar calendar);
}
