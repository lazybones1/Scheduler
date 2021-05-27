package com.mobileapp.scheduler.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Memo;

@Database(entities = {Memo.class}, version=1, exportSchema = false)
public abstract class MemoRoomDatabase extends RoomDatabase {
    public abstract MemoDao memoDao();

    private static MemoRoomDatabase INSTANCE;

    public static MemoRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (MemoRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MemoRoomDatabase.class, "memo_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
