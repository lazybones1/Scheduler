package com.mobileapp.scheduler.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

@Dao
public interface MemoDao {
    @Query("SELECT * FROM Memo")
    List<Memo> getAll();

    @Query("SELECT * FROM Memo Where memoDay = :sday")
    List<Memo> getDayEvents(String sday);

    @Query("SELECT * FROM Memo WHERE memo_name = :mName AND memoDay = :mday")
    Memo getMemoEvents(String mName, String mday);

    @Query("DELETE FROM Memo WHERE memo_name = :mName AND memoDay = :mday")
    void deleteMemoendar(String mName, String mday);

    @Insert
    void insert(Memo memo);
}
