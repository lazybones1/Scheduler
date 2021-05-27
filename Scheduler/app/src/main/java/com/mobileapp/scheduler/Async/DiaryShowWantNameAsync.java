package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.DiaryDao;
import com.mobileapp.scheduler.entity.Diary;

public class DiaryShowWantNameAsync extends AsyncTask<Void, Void, Diary> {
    private DiaryDao mDiaryDao;
    private String day;
    private String name;

    public DiaryShowWantNameAsync(DiaryDao diaryDao, String day, String name){
        this.mDiaryDao = diaryDao;
        this.day = day;
        this.name = name;
    }

    @Override
    protected Diary doInBackground(Void... voids) {
        return mDiaryDao.getDiaryEvents(this.name, this.day);
    }
}
