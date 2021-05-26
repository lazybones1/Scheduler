package com.mobileapp.scheduler.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mobileapp.scheduler.dao.DiaryDao;
import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;

@Database(entities = {Diary.class}, version=1)
public abstract class DiaryRoomDatabase extends RoomDatabase {
    public abstract DiaryDao diaryDao();

    private static DiaryRoomDatabase INSTANCE;

    public static DiaryRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DiaryRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DiaryRoomDatabase.class, "diary_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
