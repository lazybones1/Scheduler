package com.mobileapp.scheduler.dao;

import androidx.room.Insert;
import androidx.room.Query;

import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

public interface MemoDao {
    @Query("SELECT * FROM Memo")
    List<Memo> getAll();

    @Insert
    void insert(Memo memo);
}
