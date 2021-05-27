package com.mobileapp.scheduler.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.entity.Calendar;

@Database(entities = {Calendar.class}, version=1, exportSchema = false)
public abstract class CalendarRoomDatabase extends RoomDatabase {
    public abstract CalendarDao calendarDao();

    private static CalendarRoomDatabase INSTANCE;

    public static CalendarRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CalendarRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CalendarRoomDatabase.class, "calendar_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
